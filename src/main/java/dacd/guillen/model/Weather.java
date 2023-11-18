package dacd.guillen.model;
import java.time.Instant;

public class Weather {
    private double temp;
    private double rain;
    private double humidity;
    private double cloudiness;
    private double windSpeed;
    private Location location;
    private Instant instant;
    private String weatherDescription;
    private String weatherIcon;

    public Weather(double temp, double rainProbability, double humidity, double cloudiness, double windSpeed, Location location, Instant instant, String weatherDescription, String weatherIcon) {
        this.temp = temp;
        this.rain = rainProbability;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.location = location;
        this.instant = instant;
        this.weatherDescription = weatherDescription;
        this.weatherIcon = weatherIcon;
    }

    public double getTemp() {
        return temp;
    }

    public double getRainProbability() {
        return rain;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Location getLocation() {
        return location;
    }

    public Instant getInstant() {
        return instant;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }
    public String getWeatherIcon() {
        return weatherIcon;
    }
}
