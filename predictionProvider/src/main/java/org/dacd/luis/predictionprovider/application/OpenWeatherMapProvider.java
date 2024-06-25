package org.dacd.luis.predictionprovider.application;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.dacd.luis.predictionprovider.model.Location;
import org.dacd.luis.predictionprovider.model.Weather;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OpenWeatherMapProvider implements WeatherProvider {

    private final String apiKey;
    private final Gson gson = new Gson();

    public OpenWeatherMapProvider(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public List<Weather> getWeatherData(Location location) {
        String url = buildApiUrl(location);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String responseBody = obtainResponseBody(response);
                return parseWeatherData(responseBody, location);
            } catch (ParseException | IOException e) {
                throw new RuntimeException("Error processing weather data", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to the weather service", e);
        }
    }

    private String buildApiUrl(Location location) {
        return String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&units=metric&appid=%s",
                location.getLat(), location.getLongitude(), apiKey);
    }

    private String obtainResponseBody(CloseableHttpResponse response) throws IOException, ParseException {
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    private List<Weather> parseWeatherData(String responseBody, Location location) {
        JsonObject weatherResponse = gson.fromJson(responseBody, JsonObject.class);
        JsonArray list = weatherResponse.getAsJsonArray("list");
        List<Weather> weatherList = new ArrayList<>();
        list.forEach(jsonElement -> {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            processWeather(jsonObject, location).ifPresent(weatherList::add);
        });
        return weatherList;
    }

    private Optional<Weather> processWeather(JsonObject jsonObject, Location location) {
        long predictionTime = jsonObject.get("dt").getAsLong();
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(predictionTime), ZoneId.systemDefault());
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = dateTime.format(timeFormatter);

        if ("12:00:00".equals(formattedTime)) {
            Instant instant = Instant.now();
            double humidity = getValueFromJson(jsonObject, "main", "humidity").orElseThrow(() -> new IllegalArgumentException("Missing humidity value"));
            double temperature = getValueFromJson(jsonObject, "main", "temp").orElseThrow(() -> new IllegalArgumentException("Missing temperature value"));
            double precipitation = getValueFromJson(jsonObject, "rain", "3h").orElse(0.0);
            double clouds = getValueFromJson(jsonObject, "clouds", "all").orElseThrow(() -> new IllegalArgumentException("Missing clouds value"));
            double windSpeed = getValueFromJson(jsonObject, "wind", "speed").orElseThrow(() -> new IllegalArgumentException("Missing wind speed value"));

            Weather weather = new Weather(humidity, temperature, precipitation, clouds, windSpeed, location, dateTime.toInstant(ZoneId.systemDefault().getRules().getOffset(dateTime)));
            return Optional.of(weather);
        }

        return Optional.empty();
    }

    private Optional<Double> getValueFromJson(JsonObject jsonObject, String objectKey, String valueKey) {
        return Optional.ofNullable(jsonObject.getAsJsonObject(objectKey))
                .map(obj -> obj.get(valueKey))
                .map(jsonElement -> jsonElement.getAsDouble());
    }
}
