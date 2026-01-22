# Layered Architecture — CS_320 Project One Enhancement

Overview
- Layers: Controller (API), Service (business logic), Repository/DAO (data access).
- Goal: separation of concerns, testability, and easy substitution of persistence.

Packages
- com.bzermeno.cs320.projectoneenhancement.controller
- com.bzermeno.cs320.projectoneenhancement.service
- com.bzermeno.cs320.projectoneenhancement.repository
- com.bzermeno.cs320.projectoneenhancement.model
- com.bzermeno.cs320.projectoneenhancement.dto
- com.bzermeno.cs320.projectoneenhancement.mapper

Responsibilities
- Controller: receive HTTP requests, validate simple DTO-level constraints, map to service calls, return HTTP responses and status codes.
- Service: implement business rules, orchestrate transactions, perform validation that involves business invariants, convert between domain models and DTOs.
- Repository/DAO: CRUD and persistence concerns. Provide interfaces so implementations (in-memory, JDBC, JPA) can be swapped for testing or runtime.

Error handling
- Map domain exceptions to HTTP 4xx.
- Map infrastructure exceptions to HTTP 5xx.
- Use a centralized @ControllerAdvice for consistent error responses.

Dependency injection & testing
- Use Spring's DI annotations (@Service, @Repository) and constructor injection.
- Unit test services by mocking repositories (Mockito).
- Integration tests can use an in-memory repository or Testcontainers for DB-backed tests.

Recommended next steps
- Integrate these classes into an existing Spring Boot application (add Spring Boot starter if not present).
- Add unit tests for the service layer and controller integration tests.
- Replace the in-memory repository with a JPA repository or JDBC implementation for persistent storage.
