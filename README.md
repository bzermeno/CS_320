# CS_320

## Project One: Service Management System

Project One is a Java-based application demonstrating software testing and quality assurance principles. The project implements three independent service modules, each with comprehensive unit testing and input validation:

- **ContactService**: Manages contact information with fields for contact ID (max 10 chars), first name (max 10 chars), last name (max 10 chars), phone number (exactly 10 digits), and address (max 30 chars). Supports adding, deleting, and updating contacts with unique IDs.

- **TaskService**: Manages tasks with fields for task ID (max 10 chars), name (max 20 chars), and description (max 50 chars). Provides functionality to create, delete, and update tasks.

- **AppointmentService**: Manages appointments with fields for appointment ID (max 10 chars), appointment date (must be in the future), and description (max 50 chars). Enables scheduling and managing appointments with validation.

Each service implements CRUD operations using HashMap for in-memory storage, ensures data integrity through validation, and includes comprehensive JUnit tests to verify functionality and edge cases.

## Project One Enhancement: RESTful API Layer

The enhancement adds a complete RESTful API layer to the Service Management System using Spring Boot, enabling HTTP-based interactions with the services. This architectural improvement transforms the application from a library-based system to a fully functional web service.

### Key Features

- **Spring Boot Framework**: Modern, production-ready REST API implementation
- **RESTful Endpoints**: Complete CRUD operations via HTTP for all three services
- **JSON Support**: Request and response handling with JSON format
- **Input Validation**: Maintains all existing validation rules at the API layer
- **Error Handling**: Centralized exception handling with meaningful error messages
- **Integration Testing**: Comprehensive test coverage for API endpoints
- **Documentation**: Complete API documentation with examples

### Quick Start

```bash
cd ProjectOne

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# The API will be available at http://localhost:8080
```

### API Examples

**Create a contact:**
```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{"contactID":"001","firstName":"John","lastName":"Doe","phone":"1234567890","address":"123 Main St"}'
```

**Update a task:**
```bash
curl -X PUT http://localhost:8080/api/tasks/task1/name \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Task"}'
```

**Get an appointment:**
```bash
curl http://localhost:8080/api/appointments/appt1
```

For complete API documentation, see [ProjectOne/API_DOCUMENTATION.md](ProjectOne/API_DOCUMENTATION.md).

### Testing

The project includes 48 tests with 100% pass rate:
- 43 unit tests for entities and services
- 5 integration tests for REST API endpoints

```bash
mvn test
```

---

To ensure that my code, program, or software is both functional and secure, I would follow a comprehensive testing and security strategy. Functionality begins with unit testing, where individual components are tested in isolation. Integration testing follows, verifying that different parts of the program work together correctly. End-to-end testing simulates real-world usage to ensure the complete system performs as expected. Incorporating debugging tools and logging helps trace and fix issues effectively, while peer code reviews enhance quality by catching errors and suggesting improvements. Using version control systems like Git wold allow me to tracking changes and maintaining a stable codebase.

Security involves several best practices. I would start by validating all user inputs to prevent injection attacks, such as SQL injection or cross-site scripting (XSS). I would also implement robust authentication and authorization, using secure protocols like OAuth2 or JWT, and ensure that sensitive data is encrypted both in transit (via TLS/SSL) and at rest. Lastly, I would perform both static and dynamic security testing, and consider penetration testing when appropriate.

Understanding and incorporating user needs into a program begins with effective requirement gathering. This can involve user interviews, surveys, and behavioral observation. I have previously had success writing user stories in the format: “As a [type of user], I want to [perform some action] so that [benefit or goal].” Once you have collected requirements, analyze and prioritize them, distinguishing between functional (what the system should do) and non-functional (how well it should do it) needs. Documenting user stories with flowcharts, mockups, or wireframes provides a visual understanding of user expectations. An iterative or agile approach allows for continuous user feedback and adjustments throughout development.

Designing software starts with defining the problem clearly—understanding the goals, domain, and constraints. The next step is modeling the solution by breaking the system into logical components such as modules, classes, or services. Visual tools like UML diagrams (use case, class, sequence, and activity diagrams) and ERDs for database design help in this phase. Selecting an appropriate architectural style—be it monolithic, microservices, or MVC—is essential for ensuring scalability and maintainability. Clearly defined interfaces and APIs enable smooth communication between system components, especially in distributed or web-based applications. Applying core design principles such as SOLID, DRY (Don’t Repeat Yourself), KISS (Keep It Simple, Stupid), and YAGNI (You Aren’t Gonna Need It) ensures a clean, efficient design. Starting with a prototype or minimum viable product (MVP) helps validate assumptions early. Finally, incorporate testability and maintainability into your design to ensure long-term success and adaptability.
