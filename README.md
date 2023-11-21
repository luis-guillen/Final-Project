# Practica1_DACD

### Author: Luis Guillén Servera

![image](https://github.com/luis-guillen/Practica1lgv4/assets/129759843/fc1b179f-4c35-4d2e-8ff7-68fe1e162938)


 ![Badge Terminado](https://img.shields.io/badge/STATUS-Terminado-green)

 
 ## Subject : Application development for Data Science


## 🔖API key:


Place your OpenWeatherMap API key in the WeatherMapProvider class. 


Find the apiKey variable and replace it with your own key.


### 📚Dependencies:
#### Make sure you have the necessary dependencies. These are the ones used.
##### -Gson 2.10.1
##### -Sqlite-JDBC 3.42.0.0

![image](https://github.com/luis-guillen/Practica1lgv4/assets/129759843/a1d7bef5-84de-4b32-ae3a-9636b06aa896)


## 📘Description:
This Java-based application is designed to collect and store weather data for the Canary Islands. Utilizing the OpenWeatherMap API, it retrieves weather forecasts, including temperature, humidity, cloudiness, wind speed, and more, specifically for the eight major islands. The application schedules regular data updates every six hours and stores this information in a SQLite database, creating separate tables for each island. The project effectively combines HTTP requests, JSON parsing, and database management, providing a robust solution for weather data collection and storage. The main components include OpenWeatherMapProvider for API interaction, SqliteWeatherStore for database operations, and WeatherController for orchestrating the overall data flow.

## :hammer:Core functionalities

- `Weather Data Collection`: It collects detailed weather data from the OpenWeatherMap API. This includes metrics like temperature, humidity, cloudiness, and wind speed.-
-  `Regular Data Updates`:  The application is designed to fetch weather data at regular intervals - every six hours. This ensures the data is up-to-date and reflects the latest weather conditions.-
-  `Island-Specific Data Handling`: It specifically targets the eight major islands of the Canary Islands, gathering and processing weather data individually for each island.-
-  `Data Storage in SQLite Database`: All collected weather data is stored in a SQLite database. This persistent storage allows for data retrieval and analysis over time.
-  `Dynamic Table Creation`: For each of the Canary Islands, the application dynamically creates a separate table in the SQLite database. This helps in organizing the data effectively and allows for easy retrieval of island-specific weather information.
-  `Error Handling and Logging`: The application includes basic error handling, particularly for scenarios like failed API calls or database operation issues, and logs these events for debugging and maintenance.
-  `Scheduled Task Execution`: Utilizes a scheduled executor service to automate the data collection process, adhering to the specified interval of six hours.

## 📑 Resources for development
https://chat.openai.com


https://openweathermap.org/api


https://www.aluracursos.com/blog/como-escribir-un-readme-increible-en-tu-github


https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?pli=1


https://staruml.io/

## 💻 How to run
### Just run the Main.java file and watch as the databases are generated.



## Diagram

![image](https://github.com/luis-guillen/Practica1lgv4/assets/129759843/7f3a0809-50cd-47cb-9224-d5d0b2a4cb0e)

