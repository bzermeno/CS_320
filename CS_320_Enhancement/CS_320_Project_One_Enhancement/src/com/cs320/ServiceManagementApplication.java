/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026 
 *  Course ID: CS-320
 *  Description: Main Spring Boot application class that bootstraps the REST API.
 *  This class serves as the entry point for the RESTful API layer, exposing
 *  Contact, Task, and Appointment services through HTTP endpoints.
 */
package com.cs320;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot application entry point for the Service Management REST API.
 * Enables auto-configuration and component scanning for REST controllers.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cs320", "ContactService", "TaskService", "AppointmentService"})
public class ServiceManagementApplication {
    
    /**
     * Main method to launch the Spring Boot application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceManagementApplication.class, args);
    }
}
