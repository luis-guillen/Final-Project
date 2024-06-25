package org.dacd.luis.businessunit.service;

import org.dacd.luis.businessunit.model.Weather;

import java.sql.Date;
import java.util.List;

public interface WeatherRepository {

    void saveWeather(Weather weather);
    List<Weather> getAllWeather();
    List<Weather> getWeatherByDate(Date date,String location);
    List<Weather> getWeatherByLocation(String location);
    List<Weather> getWeatherByTemperature(String temperature);
}
