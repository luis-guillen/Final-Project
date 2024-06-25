package org.dacd.luis.predictionprovider.application;

import org.dacd.luis.predictionprovider.model.Location;
import org.dacd.luis.predictionprovider.model.Weather;

import java.util.List;

public interface WeatherProvider {
    List<Weather> getWeatherData(Location location);
}
