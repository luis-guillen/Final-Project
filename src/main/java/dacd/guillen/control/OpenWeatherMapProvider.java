package dacd.guillen.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dacd.guillen.model.Location;
import dacd.guillen.model.Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherMapProvider implements WeatherProvider {
    private static final String TEMPLATE_URL = "https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&appid=%s";
    private final String apiKey;

    public OpenWeatherMapProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public List<Weather> getWeather() {
        List<Location> canaryIslands = getCanaryIslands();
        List<Weather> weatherList = new ArrayList<>();

        for (Location island : canaryIslands) {
            String url = String.format(TEMPLATE_URL, island.getLat(), island.getLon(), apiKey);
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JsonArray forecastList = JsonParser.parseString(response.toString()).getAsJsonObject().getAsJsonArray("list");

                for (JsonElement element : forecastList) {
                    JsonObject forecast = element.getAsJsonObject();
                    Instant instant = Instant.ofEpochSecond(forecast.getAsJsonPrimitive("dt").getAsLong());

                    if (instant.atZone(ZoneId.of("UTC")).toLocalTime().equals(LocalTime.of(12, 0, 0))) {
                        JsonObject main = forecast.getAsJsonObject("main");
                        JsonObject weather = forecast.getAsJsonArray("weather").get(0).getAsJsonObject();
                        JsonObject wind = forecast.getAsJsonObject("wind");

                        Weather weatherData = new Weather(
                                main.getAsJsonPrimitive("temp").getAsDouble(),
                                0,  // Placeholder for rain probability
                                main.getAsJsonPrimitive("humidity").getAsDouble(),
                                forecast.getAsJsonObject("clouds").getAsJsonPrimitive("all").getAsDouble(),
                                wind.getAsJsonPrimitive("speed").getAsDouble(),
                                island,
                                instant,
                                weather.getAsJsonPrimitive("description").getAsString(),
                                weather.getAsJsonPrimitive("icon").getAsString()
                        );

                        weatherList.add(weatherData);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return weatherList;
    }

    private List<Location> getCanaryIslands() {
        List<Location> canaryIslands = new ArrayList<>();
        canaryIslands.add(new Location(28.1248, -15.4300, "Las Palmas"));
        canaryIslands.add(new Location(28.4636, -16.2518, "Santa Cruz de Tenerife"));
        canaryIslands.add(new Location(28.9637, -13.5477, "Arrecife"));
        canaryIslands.add(new Location(28.5004, -13.8627, "Puerto del Rosario"));
        canaryIslands.add(new Location(28.0936, -17.1114, "San Sebastián de la Gomera"));
        canaryIslands.add(new Location(27.8074, -17.8947, "Valverde"));
        canaryIslands.add(new Location(28.6835, -17.7644, "Santa Cruz de la Palma"));
        canaryIslands.add(new Location(29.2350, -13.4957, "Órzola"));

        return canaryIslands;
    }
}
