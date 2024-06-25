package org.dacd.luis.sensorprovider.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.dacd.luis.sensorprovider.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.time.Instant;

public class XoteloPublisher implements HotelPublisher {

    private static final Logger logger = LoggerFactory.getLogger(XoteloPublisher.class);
    private static final String url = "tcp://localhost:61616";
    private Connection connection;

    @Override
    public void start() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            logger.error("Failed to start JMS connection", e);
            throw new RuntimeException("Failed to start JMS connection", e);
        }
    }

    @Override
    public void publish(Hotel event) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                .create();
        String jsonEvent = gson.toJson(event);
        Session session = null;
        MessageProducer producer = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("sensor.Hotel");
            producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(jsonEvent);
            producer.send(message);
            logger.info("Published message: '{}'", message.getText());
        } catch (JMSException e) {
            logger.error("Failed to publish message", e);
            throw new RuntimeException("Failed to publish message", e);
        } finally {
            if (producer != null) {
                try {
                    producer.close();
                } catch (JMSException e) {
                    logger.error("Failed to close MessageProducer", e);
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    logger.error("Failed to close Session", e);
                }
            }
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            logger.error("Failed to close JMS connection", e);
            throw new RuntimeException("Failed to close JMS connection", e);
        }
    }

    // Inner class for InstantTypeAdapter
    private static class InstantTypeAdapter implements com.google.gson.JsonSerializer<Instant>, com.google.gson.JsonDeserializer<Instant> {
        @Override
        public com.google.gson.JsonElement serialize(Instant instant, java.lang.reflect.Type srcType,
                                                     com.google.gson.JsonSerializationContext context) {
            return new com.google.gson.JsonPrimitive(instant.toString());
        }

        @Override
        public Instant deserialize(com.google.gson.JsonElement json, java.lang.reflect.Type typeOfT,
                                   com.google.gson.JsonDeserializationContext context) throws com.google.gson.JsonParseException {
            return Instant.parse(json.getAsString());
        }
    }
}
