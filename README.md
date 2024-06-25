# CanaryTripPlanner Final Project
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/1ac975de-824c-4512-89e6-d7abc07d9ca2)


### University
University of Las Palmas de Gran Canaria

### Faculty
[Faculty Of Computer Science](https://www.eii.ulpgc.es/es)

### University Degree
[Data Science and Engineering](https://www.eii.ulpgc.es/es/formacion/Grado-en-Ciencia-e-Ingenieria-de-Datos)

### Subject
Data Science Application Development

### Academic Year
2023

## Project Overview

The CanaryTripPlanner project is designed to provide personalized travel recommendations based on weather conditions and hotel availability. It utilizes a modular architecture to ensure flexibility, scalability, and ease of maintenance. The key functionalities include:

- **Weather Data Analysis**: Integration with the OpenWeather API to retrieve and analyze weather forecasts.
- **Hotel Data Management**: Collection and processing of hotel availability and pricing data.
- **Vacation Recommendations**: Providing tailored travel suggestions based on user preferences and current data.
- **Publisher/Subscriber Model**: Utilizing ActiveMQ for event-driven communication between components.

## Design

The project follows the principles of **Clean Architecture**, ensuring a separation of concerns and promoting a flexible, maintainable codebase. This architecture places the business logic and application model at the core of the application, independent of data access and other infrastructure concerns. Dependencies flow inwards, with the core components being unaffected by changes in the outer layers.

### Design Patterns Implemented:

- **Clean Architecture**: Centralizes the business logic and domain models, with all dependencies directed towards the core. This allows for easy modifications and enhancements, such as changing the message broker.
  
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/445095c0-887b-4abb-ba40-2b2619900dd1)

- **Repository Pattern**: Used in the Business Unit for efficient data handling and CRUD operations. This pattern abstracts the data layer, making the code more testable and maintainable.

### Principles Applied:

- **Single Responsibility Principle**: Each class has a single responsibility, ensuring that it has only one reason to change.
- **Interface Segregation Principle**: Large interfaces are divided into smaller, more specific ones.
- **Dependency Inversion Principle**: High-level modules do not depend on low-level modules; both depend on abstractions. This promotes flexibility and ease of maintenance.

## Execution Requirements

### Arguments and Environment Variables

To run the project, specific arguments and environment variables need to be set:
- **Program Arguments**: 
  - predictionProvider must be run with a custom run configuration apiKey, defined in the project.
- **Program Arguments**: 
  - `rootDirectory`: The root directory for storing and accessing data.
  Example:
  ```sh
  java -cp target/your-artifact.jar org.dacd.luis.eventstore.Main /path/to/rootDirectory
  ```

- **Environment Variables**:
  - `ACTIVE_MQ_URL`: URL for the ActiveMQ broker (default: `tcp://localhost:61616`).
  - `WEATHER_API_KEY`: API key for accessing the OpenWeather API.
  
### Example Environment Setup

```sh
export ACTIVE_MQ_URL=tcp://localhost:61616
export WEATHER_API_KEY=your_openweather_api_key
```

Ensure these environment variables are set before running the application to enable proper configuration and connectivity to required services.

# Diagrams
## predictionProvider

### application
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/91025edd-4b96-4391-9b9e-518bf1943442)

### model
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/c6d681f7-bc22-428e-9ec7-f2e3fbfbf86e)



## datalakeBuilder

### application
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/944b43cf-f7de-40ec-9b18-e33c6e00d593)


## businessUnit

### application
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/7cf9993a-55dc-4413-a355-5762754a7c91)

### model
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/7a5786e4-dc68-4e28-9cfc-05fc7a0711ce)

### service
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/d18bd549-b6bb-49e6-8fc9-8e6fd8bfb076)


## hotelProvider

### application
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/adfd1121-28c4-4226-96aa-b9bb3441726c)

### model
![image](https://github.com/luis-guillen/Final-Project/assets/129759843/e7707a01-c637-41ca-99d9-56de8e5a21f3)


