# Java Lab Exercises

This repository contains solutions for laboratory exercises related to Java programming and backend system development. The project progresses from language basics to complex microservice architectures using Spring Boot.

## 📂 Project Structure

### [Lab 1: Java Basics & Color Manipulation](./Lab1)
Focuses on basic data types and bitwise operations.
- **Functionality:** Converting color records between different formats (HEX, RGB, HSB, HSL, CMYK).
- **Key Classes:** `Color.java`, `Main.java`, JUnit tests.

### [Lab 2: IoT Simulation & MQTT](./Lab2)
Introduction to communication protocols and sensor simulation.
- **Functionality:** Simulation of a smart water meter (`WaterFlowMeter`) that generates consumption, pressure, and temperature data and sends it via the MQTT protocol.
- **Technologies:** Eclipse Paho MQTT client.

### [Lab 3: JSON Configuration](./Lab3)
Upgrading the IoT simulation with dynamic configuration.
- **Functionality:** Loading sensor parameters from an external `config.json` file instead of hardcoding values.
- **Technologies:** Google Gson library for serialization/deserialization.

### [Lab 4: Spring Boot REST API & Docker](./Lab4)
Building a backend application for library management.
- **Functionality:** CRUD operations on the `Book` entity (title, author, year).
- **Technologies:** Spring Boot, Spring Data JPA, PostgreSQL.
- **Deployment:** Configured `Dockerfile` and `docker-compose.yml` for running the application and database.

### [Lab 5: Advanced REST, Pagination & Exceptions](./Lab5)
Improving the API from Lab 4.
- **Functionality:**
    - Implementation of `GlobalExceptionHandler` for error handling (e.g., `BookNotFoundException`).
    - Added support for **pagination**, **sorting**, and **filtering** of book search results.

### [Lab 6: Entity Relationships & Business Logic](./Lab6)
Expanding the data model and complex operations.
- **New Entities:** `Member`, `Reservation`, `Notification`.
- **Logic:**
    - Linking members and books through reservations (`@OneToMany`, `@ManyToOne`).
    - Automatic creation of notifications when a reservation is fulfilled.

---