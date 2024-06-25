package org.dacd.luis.predictionprovider.application;

import org.dacd.luis.predictionprovider.model.Location;

import java.util.ArrayList;
import java.util.TimerTask;

public class WeatherPeriodicTask extends TimerTask {
    private final OpenWeatherMapProvider weatherProvider;
    private final ArrayList<Location> locations;

    public WeatherPeriodicTask(OpenWeatherMapProvider weatherProvider, ArrayList<Location> locations) {
        this.weatherProvider = weatherProvider;
        this.locations = locations;
    }

    @Override
    public void run() {
        PublisherActiveMQ publisherActiveMQ = new PublisherActiveMQ();
        publisherActiveMQ.start();
        WeatherApplication weatherApplication = new WeatherApplication(weatherProvider, locations, publisherActiveMQ);
        weatherApplication.execute();
        publisherActiveMQ.close();
    }
}
