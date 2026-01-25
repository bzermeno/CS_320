# REST API Documentation

## Overview

The CS-320 Project One Enhancement adds a RESTful API layer to the Service Management System using Spring Boot. The API provides endpoints for managing Contacts, Tasks, and Appointments.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Building the Project

```bash
cd ProjectOne
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

The API server will start on port 8080.

### Running Tests

```bash
# Run all tests (unit + integration)
mvn test

# Run only integration tests
mvn test -Dtest=*ControllerTest
```

## API Endpoints

### Contact Management

#### Create Contact
- **Endpoint:** `POST /api/contacts`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "contactID": "12345",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "1234567890",
  "address": "123 Main St"
}
```
- **Success Response:** `201 Created`
```json
{
  "message": "Contact created successfully",
  "contactID": "12345"
}
```
- **Validation Rules:**
  - contactID: Required, max 10 characters
  - firstName: Required, max 10 characters
  - lastName: Required, max 10 characters
  - phone: Required, exactly 10 digits
  - address: Required, max 30 characters

#### Update Contact First Name
- **Endpoint:** `PUT /api/contacts/{contactID}/firstName`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "firstName": "Jane"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "First name updated successfully"
}
```

#### Update Contact Last Name
- **Endpoint:** `PUT /api/contacts/{contactID}/lastName`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "lastName": "Smith"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "Last name updated successfully"
}
```

#### Update Contact Phone
- **Endpoint:** `PUT /api/contacts/{contactID}/phone`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "phone": "9876543210"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "Phone updated successfully"
}
```

#### Update Contact Address
- **Endpoint:** `PUT /api/contacts/{contactID}/address`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "address": "456 Oak Ave"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "Address updated successfully"
}
```

#### Delete Contact
- **Endpoint:** `DELETE /api/contacts/{contactID}`
- **Success Response:** `200 OK`
```json
{
  "message": "Contact deleted successfully"
}
```

### Task Management

#### Create Task
- **Endpoint:** `POST /api/tasks`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "taskID": "task123",
  "name": "Complete Project",
  "description": "Finish the REST API implementation"
}
```
- **Success Response:** `201 Created`
```json
{
  "message": "Task created successfully",
  "taskID": "task123"
}
```
- **Validation Rules:**
  - taskID: Required, max 10 characters
  - name: Required, max 20 characters
  - description: Required, max 50 characters

#### Update Task Name
- **Endpoint:** `PUT /api/tasks/{taskID}/name`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "name": "Updated Task Name"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "Task name updated successfully"
}
```

#### Update Task Description
- **Endpoint:** `PUT /api/tasks/{taskID}/description`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "description": "Updated task description"
}
```
- **Success Response:** `200 OK`
```json
{
  "message": "Task description updated successfully"
}
```

#### Delete Task
- **Endpoint:** `DELETE /api/tasks/{taskID}`
- **Success Response:** `200 OK`
```json
{
  "message": "Task deleted successfully"
}
```

### Appointment Management

#### Create Appointment
- **Endpoint:** `POST /api/appointments`
- **Content-Type:** `application/json`
- **Request Body:**
```json
{
  "appointmentID": "appt456",
  "appointmentDate": "2026-02-15T10:00:00.000+00:00",
  "description": "Annual checkup"
}
```
- **Success Response:** `201 Created`
```json
{
  "message": "Appointment created successfully",
  "appointmentID": "appt456"
}
```
- **Validation Rules:**
  - appointmentID: Required, max 10 characters
  - appointmentDate: Required, must be in the future
  - description: Required, max 50 characters

#### Get Appointment
- **Endpoint:** `GET /api/appointments/{appointmentID}`
- **Success Response:** `200 OK`
```json
{
  "appointmentID": "appt456",
  "appointmentDate": "2026-02-15T10:00:00.000+00:00",
  "description": "Annual checkup"
}
```

#### Delete Appointment
- **Endpoint:** `DELETE /api/appointments/{appointmentID}`
- **Success Response:** `200 OK`
```json
{
  "message": "Appointment deleted successfully"
}
```

## Error Responses

All endpoints return error responses in the following format:

```json
{
  "error": "Error message describing what went wrong"
}
```

Common HTTP status codes:
- `400 Bad Request`: Invalid input or validation failure
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Unexpected server error

## Example Usage with cURL

### Create a Contact
```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "contactID": "001",
    "firstName": "Alice",
    "lastName": "Johnson",
    "phone": "5551234567",
    "address": "789 Elm Street"
  }'
```

### Update Contact Phone
```bash
curl -X PUT http://localhost:8080/api/contacts/001/phone \
  -H "Content-Type: application/json" \
  -d '{"phone": "5559876543"}'
```

### Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "taskID": "t001",
    "name": "Write Tests",
    "description": "Write unit tests for all services"
  }'
```

### Delete a Task
```bash
curl -X DELETE http://localhost:8080/api/tasks/t001
```

### Create an Appointment
```bash
curl -X POST http://localhost:8080/api/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "appointmentID": "a001",
    "appointmentDate": "2026-03-20T14:30:00.000+00:00",
    "description": "Dentist appointment"
  }'
```

### Get an Appointment
```bash
curl http://localhost:8080/api/appointments/a001
```

## Architecture

The REST API layer follows a layered architecture:

```
┌─────────────────────────────────────┐
│      REST API Layer                 │
│  (Controllers + DTOs)               │
├─────────────────────────────────────┤
│      Service Layer                  │
│  (Business Logic)                   │
├─────────────────────────────────────┤
│      Repository Layer               │
│  (Data Access)                      │
├─────────────────────────────────────┤
│      Entity Layer                   │
│  (Domain Models)                    │
└─────────────────────────────────────┘
```

### Components

- **Controllers**: Handle HTTP requests and responses
  - `ContactController.java`
  - `TaskController.java`
  - `AppointmentController.java`

- **DTOs (Data Transfer Objects)**: Transfer data between client and server
  - `ContactDTO.java`
  - `TaskDTO.java`
  - `AppointmentDTO.java`

- **Exception Handlers**: Centralized error handling
  - `GlobalExceptionHandler.java`

- **Services**: Business logic (existing)
  - `ContactService.java`
  - `TaskService.java`
  - `AppointmentService.java`

## Testing

The project includes comprehensive test coverage:

### Unit Tests (43 tests)
- Contact entity tests
- Task entity tests
- Appointment entity tests
- Service layer tests

### Integration Tests (5 tests)
- REST API endpoint tests
- Request/response validation
- Error handling tests

Run all tests:
```bash
mvn test
```

Expected output:
```
Tests run: 48, Failures: 0, Errors: 0, Skipped: 0
```

## Future Enhancements

Potential improvements for the API:

1. Add GET endpoints to list all contacts/tasks/appointments
2. Add pagination and filtering support
3. Implement search functionality
4. Add authentication and authorization
5. Add API versioning
6. Add Swagger/OpenAPI documentation
7. Add rate limiting
8. Implement caching
9. Add logging and monitoring
10. Deploy to cloud platform
