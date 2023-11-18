package dacd.guillen.control;
import dacd.guillen.model.Weather;

import java.util.List;

public interface WeatherProvider {
    List<Weather> getWeather();
}
