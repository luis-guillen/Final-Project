package dacd.guillen.control;
import dacd.guillen.model.WeatherData;

import java.util.List;

public interface WeatherProvider {
    List<WeatherData> getWeather();
}
