package org.dacd.luis.predictionprovider.application;

import com.google.gson.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.dacd.luis.predictionprovider.model.Weather;

import javax.jms.*;
import java.lang.reflect.Type;
import java.time.Instant;

public class PublisherActiveMQ implements Publisher {
    private static final String url = "tcp://localhost:61616";
    private Connection connection;

    @Override
    public void start() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void publish(Weather event) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
                .create();
        String jsonEvent = gson.toJson(event);
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("prediction.Weather");
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(jsonEvent);
            producer.send(message);
            System.out.println("JCG printing@@ '" + message.getText() + "'");
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private static class InstantTypeAdapter implements JsonSerializer<Instant>, JsonDeserializer<Instant> {
        @Override
        public JsonElement serialize(Instant instant, Type srcType,
                                     JsonSerializationContext context) {
            return new JsonPrimitive(instant.toString());
        }

        @Override
        public Instant deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {
            return Instant.parse(json.getAsString());
        }
    }
}
