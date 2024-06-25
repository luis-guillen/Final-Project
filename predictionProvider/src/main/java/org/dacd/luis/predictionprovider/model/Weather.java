package org.dacd.luis.predictionprovider.model;

import java.time.Instant;

public class Weather {
    private Instant ts;
    private String ss;
    private double humidity;
    private double temperature;
    private double precipitation;
    private double clouds;
    private double windSpeed;
    private Location location;
    private Instant predictionTime;

    public Weather(Instant ts, String ss, double humidity, double temperature, double precipitation, double clouds, double windSpeed, Location location, Instant predictionTime) {
        this.ts = ts;
        this.ss = ss;
        this.humidity = humidity;
        this.temperature = temperature;
        this.precipitation = precipitation;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.location = location;
        this.predictionTime = predictionTime;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getClouds() {
        return clouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Location getLocation() {
        return location;
    }

    public Instant getPredictionTime() {
        return predictionTime;
    }

    public double getTemperature() {
        return temperature;
    }

}
