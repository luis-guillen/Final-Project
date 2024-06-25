package org.dacd.luis.businessunit.application;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.dacd.luis.businessunit.model.Hotel;
import org.dacd.luis.businessunit.service.EventStore;
import org.dacd.luis.businessunit.service.HotelRepository;

public class EventStoreHotelSqliteManager implements EventStore {
    private final HotelRepository sqliteRepository;

    public EventStoreHotelSqliteManager(HotelRepository sqliteRepository) {
        this.sqliteRepository = sqliteRepository;
    }

    @Override
    public void storeEvent(String json, String topicName) {
        System.out.println(json);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String name = jsonObject.get("name").getAsString();
        String checkIn = jsonObject.get("checkIn").getAsString();
        String checkOut = jsonObject.get("checkOut").getAsString();
        JsonArray list = jsonObject.get("rates").getAsJsonArray();
        String location = jsonObject.get("location").getAsJsonObject().get("name").getAsString();
        list.forEach(jsonElement -> {
            JsonObject rate = jsonElement.getAsJsonObject();
            double price = rate.get("price").getAsDouble();
            String platformName = rate.get("platformName").getAsString();
            sqliteRepository.saveHotel(new Hotel(name,location, price, platformName,checkIn,checkOut));
        });
    }
}
