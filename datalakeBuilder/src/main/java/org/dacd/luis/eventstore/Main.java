package org.dacd.luis.eventstore;

import org.dacd.luis.eventstore.application.EventStoreManager;
import org.dacd.luis.eventstore.application.Subscriber;
import org.dacd.luis.eventstore.application.SubscriberActiveMQ;

public class Main {
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "Luis_Guillen";
    private static final String PREDICTION_WEATHER_TOPIC = "prediction.Weather";
    private static final String HOTEL_TOPIC = "sensor.Hotel";
    private static final String SUBSCRIBER_ID = "Luis_Guillen";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Se requiere un argumento para el directorio raíz.");
            System.exit(1);
        }

        String rootDirectory = args[0];
        EventStoreManager eventStoreManager = new EventStoreManager();

        Subscriber subscriberWeather = new SubscriberActiveMQ(BROKER_URL, CLIENT_ID + "_Weather", SUBSCRIBER_ID + "_Weather", eventStoreManager, rootDirectory);
        subscriberWeather.start();
        subscriberWeather.subscribe(PREDICTION_WEATHER_TOPIC);

        Subscriber subscriberHotel = new SubscriberActiveMQ(BROKER_URL, CLIENT_ID + "_Hotel", SUBSCRIBER_ID + "_Hotel", eventStoreManager, rootDirectory);
        subscriberHotel.start();
        subscriberHotel.subscribe(HOTEL_TOPIC);
    }
}
