package org.dacd.luis.businessunit.application;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.dacd.luis.businessunit.service.EventStore;
import org.dacd.luis.businessunit.service.Subscriber;

import javax.jms.*;

public class SubscriberActiveMQ implements Subscriber {
    private final String brokerUrl;
    private final String clientId;
    private final String subscriberId;
    private final EventStore eventStore;
    private Connection connection;

    public SubscriberActiveMQ(String brokerUrl, String clientId, String subscriberId, EventStore eventStore) {
        this.brokerUrl = brokerUrl;
        this.clientId = clientId;
        this.subscriberId = subscriberId;
        this.eventStore = eventStore;
    }

    @Override
    public void start() {
        try {
            createAndStartConnection();
        } catch (JMSException e) {
            handleError("Error establishing JMS connection: " + e.getMessage());
        }
    }

    private void createAndStartConnection() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.brokerUrl);
        connection = connectionFactory.createConnection();
        connection.setClientID(this.clientId);
        connection.start();
    }

    @Override
    public void subscribe(String topicName) {
        try {
            Session session = createSession();
            Topic destination = createTopic(session, topicName);
            MessageConsumer consumer = createSubscriber(session, destination);
            setMessageListener(consumer, topicName);
            System.out.println("Subscribed to topic: " + topicName);
        } catch (JMSException e) {
            handleError("Error during subscription: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            handleError("Error closing JMS connection: " + e.getMessage());
        }
    }

    private Session createSession() throws JMSException {
        return this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private Topic createTopic(Session session, String topicName) throws JMSException {
        return session.createTopic(topicName);
    }

    private MessageConsumer createSubscriber(Session session, Topic destination) throws JMSException {
        return session.createConsumer(destination);
    }

    private void setMessageListener(MessageConsumer consumer, String topicName) throws JMSException {
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    handleIncomingMessage(message, topicName);
                } catch (JMSException e) {
                    handleError("Error processing JMS message: " + e.getMessage());
                }
            }
        });
    }

    private void handleIncomingMessage(Message message, String topicName) throws JMSException {
        if (message instanceof TextMessage) {
            String text = ((TextMessage) message).getText();
            eventStore.storeEvent(text, topicName);
        } else {
            System.err.println("Unrecognized message: " + message.getClass().getName());
        }
    }

    private static void handleError(String errorMessage) {
        System.err.println(errorMessage);
    }
}
