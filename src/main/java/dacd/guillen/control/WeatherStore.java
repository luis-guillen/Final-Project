package dacd.guillen.control;

import dacd.guillen.model.Weather;

import java.sql.Connection;
import java.sql.SQLException;

public interface WeatherStore {
    Connection getConnection() throws SQLException;

    void createTableIfNotExists(Connection connection, String tableName) throws SQLException;

    void saveWeather(Connection connection, String tableName, Weather weather) throws SQLException;
}