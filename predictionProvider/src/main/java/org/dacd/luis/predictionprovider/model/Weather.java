package org.dacd.luis.predictionprovider.model;

import java.time.Instant;

public class Weather {
    private final double humidity;
    private final double temperature;
    private final double precipitation;
    private final double clouds;
    private final double windSpeed;
    private final Location location;
    private final Instant predictionTime;

    public Weather(double humidity, double temperature, double precipitation, double clouds, double windSpeed, Location location, Instant predictionTime) {
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
