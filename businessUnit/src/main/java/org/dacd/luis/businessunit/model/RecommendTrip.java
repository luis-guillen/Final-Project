package org.dacd.luis.businessunit.model;

import java.util.List;

public class RecommendTrip {
    private List<Hotel> hotels;
    private List<Weather> allWeather;

    public RecommendTrip(List<Hotel> hotels, List<Weather> allWeather) {
        this.hotels = hotels;
        this.allWeather = allWeather;
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Weather> getAllWeather() {
        return allWeather;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void setAllWeather(List<Weather> allWeather) {
        this.allWeather = allWeather;
    }
}
