package org.dacd.luis.predictionprovider.application;

import org.dacd.luis.predictionprovider.model.Location;
import org.dacd.luis.predictionprovider.model.Weather;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WeatherApplication {
    private final OpenWeatherMapProvider openWeatherMapProvider;
    private final ArrayList<Location> locations;
    private final Publisher publisher;

    public WeatherApplication(OpenWeatherMapProvider openWeatherMapProvider, ArrayList<Location> locations, Publisher publisher) {
        this.openWeatherMapProvider = openWeatherMapProvider;
        this.locations = locations;
        this.publisher = publisher;
    }

    public void execute() {
        for (Location location : locations) {
            List<Weather> weatherList = openWeatherMapProvider.getWeatherData(location);
            Instant.now();
            for (Weather weather : weatherList) {
                Weather event = new Weather(weather.getHumidity(), weather.getTemperature(),
                        weather.getPrecipitation(), weather.getClouds(), weather.getWindSpeed(), weather.getLocation(), weather.getPredictionTime());
                publisher.publish(event);
            }
        }
    }
}
