package org.dacd.luis.businessunit.application;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.dacd.luis.businessunit.model.Weather;
import org.dacd.luis.businessunit.service.WeatherRepository;
import org.dacd.luis.businessunit.service.EventStore;

public class EventStoreWeatherSqliteManager implements EventStore {
    private final WeatherRepository sqliteRepository;

    public EventStoreWeatherSqliteManager(WeatherRepository sqliteRepository) {
        this.sqliteRepository = sqliteRepository;
    }

    @Override
    public void storeEvent(String json, String topicName) {
        System.out.println(json);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double precipitation = jsonObject.get("precipitation").getAsDouble();
        double clouds = jsonObject.get("clouds").getAsDouble();
        double temperature = jsonObject.get("temperature").getAsDouble();
        double windSpeed = jsonObject.get("windSpeed").getAsDouble();
        String location = jsonObject.get("location").getAsJsonObject().get("name").getAsString();
        String date = jsonObject.get("predictionTime").getAsString();
        sqliteRepository.saveWeather(new Weather(precipitation,date, clouds, temperature, windSpeed, location));
    }
}
