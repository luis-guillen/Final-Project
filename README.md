Claro, aquí tienes el README adaptado con el nombre "CanaryTripPlanner":

# CanaryTripPlanner Final Project

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
  
  ![Clean_Architecture](/images/CleanArchitecture.png)

- **Repository Pattern**: Used in the Business Unit for efficient data handling and CRUD operations. This pattern abstracts the data layer, making the code more testable and maintainable.

### Principles Applied:

- **Single Responsibility Principle**: Each class has a single responsibility, ensuring that it has only one reason to change.
- **Interface Segregation Principle**: Large interfaces are divided into smaller, more specific ones.
- **Dependency Inversion Principle**: High-level modules do not depend on low-level modules; both depend on abstractions. This promotes flexibility and ease of maintenance.

## Execution Requirements

### Arguments and Environment Variables

To run the project, specific arguments and environment variables need to be set:

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

This documentation should provide a clear and concise overview of the project, its design, and execution requirements.
```
