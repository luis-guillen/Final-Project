package dacd.guillen.control;

import dacd.guillen.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteWeatherStore implements WeatherStore {
    private static final String DATABASE_URL = "jdbc:sqlite:weather_data.db";
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    @Override
    public void createTableIfNotExists(Connection connection, String tableName) throws SQLException {
        tableName = tableName.replaceAll("[^a-zA-Z0-9]", "_");

        String createTableQuery = "CREATE TABLE IF NOT EXISTS \"" + tableName + "\" ("
                + "instant TEXT PRIMARY KEY,"
                + "temperature REAL,"
                + "rain_probability REAL,"
                + "humidity REAL,"
                + "clouds REAL,"
                + "wind_speed REAL);";

        try (PreparedStatement createTableStatement = connection.prepareStatement(createTableQuery)) {
            createTableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveWeather(Connection connection, String tableName, Weather weather) throws SQLException {
        tableName = tableName.replaceAll("[^a-zA-Z0-9]", "_");

        String insertDataQuery = "INSERT OR REPLACE INTO \"" + tableName + "\" (instant, temperature, rain_probability, humidity, clouds, wind_speed) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement insertDataStatement = connection.prepareStatement(insertDataQuery)) {
            insertDataStatement.setString(1, weather.getInstant().toString());
            insertDataStatement.setDouble(2, weather.getTemp());
            insertDataStatement.setDouble(3, weather.getRainProbability());
            insertDataStatement.setDouble(4, weather.getHumidity());
            insertDataStatement.setDouble(5, weather.getCloudiness());
            insertDataStatement.setDouble(6, weather.getWindSpeed());
            insertDataStatement.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}