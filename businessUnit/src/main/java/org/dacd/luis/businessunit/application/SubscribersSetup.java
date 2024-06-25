package org.dacd.luis.businessunit.application;

import org.dacd.luis.businessunit.service.EventStore;
import org.dacd.luis.businessunit.service.Subscriber;

public class SubscribersSetup {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String CLIENT_ID = "Luis_Guillen";
    private static final String PREDICTION_WEATHER_TOPIC = "prediction.Weather";
    private static final String HOTEL_TOPIC = "sensor.Hotel";
    private static final String SUBSCRIBER_ID = "Luis_Guillen";

    public static void initializeSubscribers() {
        EventStore eventStoreWeatherManager = new EventStoreWeatherSqliteManager(new WeatherSqliteRepository());
        Subscriber subscriberWeather = new SubscriberActiveMQ(BROKER_URL, CLIENT_ID + "_Weather", SUBSCRIBER_ID + "_Weather", eventStoreWeatherManager);
        subscriberWeather.start();
        subscriberWeather.subscribe(PREDICTION_WEATHER_TOPIC);
        EventStore eventStoreHotelManager = new EventStoreHotelSqliteManager(new HotelSqliteRepository());
        Subscriber subscriberHotel = new SubscriberActiveMQ(BROKER_URL, CLIENT_ID + "_Hotel", SUBSCRIBER_ID + "_Hotel", eventStoreHotelManager);
        subscriberHotel.start();
        subscriberHotel.subscribe(HOTEL_TOPIC);
    }
}
