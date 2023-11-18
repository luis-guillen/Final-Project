package dacd.guillen.control;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        WeatherController weatherController = new WeatherController();
        weatherController.startWeatherUpdateScheduler();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:weather_data.db");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table';")) {
            System.out.println("Tables in the database:");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
