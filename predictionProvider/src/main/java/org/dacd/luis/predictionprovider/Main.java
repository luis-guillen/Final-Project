package org.dacd.luis.predictionprovider;

import org.dacd.luis.predictionprovider.application.LocationSupplier;
import org.dacd.luis.predictionprovider.application.WeatherPeriodicTask;
import org.dacd.luis.predictionprovider.model.Location;
import org.dacd.luis.predictionprovider.application.OpenWeatherMapProvider;

import java.util.ArrayList;
import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("No se ha proporcionado la clave API. Uso: java Main <apiKey>");
            System.exit(1);
        }
        String apiKey = args[0];
        ArrayList<Location> locations = LocationSupplier.initializeIslands();
        OpenWeatherMapProvider weatherOpenWeatherApiQuery = new OpenWeatherMapProvider(apiKey);
        Timer timer = new Timer();
        WeatherPeriodicTask updaterTask = new WeatherPeriodicTask(weatherOpenWeatherApiQuery, locations);
        timer.scheduleAtFixedRate(updaterTask, 0, 6 * 60 * 60 * 1000);
    }
}
