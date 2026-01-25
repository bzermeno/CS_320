# RESTful API Documentation

## Overview

This document describes the RESTful API layer for the CS_320 Service Management System. The API provides HTTP endpoints for managing Contacts, Tasks, and Appointments using standard REST conventions and JSON data format.

## Base URL

```
http://localhost:8080/api
```

## API Endpoints

### Contact Service

#### Create Contact

**Endpoint:** `POST /api/contacts`

**Request Body:**
```json
{
  "contactID": "123",
  "firstName": "John",
  "lastName": "Doe",
  "phone": "1234567890",
  "address": "123 Main St"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Contact created successfully with ID: 123",
  "contactID": "123"
}
```

**Error Response (400 Bad Request):**
```json
{
  "message": "Error creating contact: [error details]"
}
```

**Field Constraints:**
- `contactID`: max 10 characters, not null, must be unique
- `firstName`: max 10 characters, not null
- `lastName`: max 10 characters, not null
- `phone`: exactly 10 digits, not null
- `address`: max 30 characters, not null

#### Delete Contact

**Endpoint:** `DELETE /api/contacts/{contactID}`

**Success Response (200 OK):**
```json
{
  "message": "Contact deleted successfully: 123"
}
```

**Error Response (404 Not Found):**
```json
{
  "message": "Error: Contact ID not found"
}
```

#### Update Contact First Name

**Endpoint:** `PUT /api/contacts/{contactID}/firstName`

**Request Body:**
```json
{
  "firstName": "Jane"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Contact first name updated successfully"
}
```

#### Update Contact Last Name

**Endpoint:** `PUT /api/contacts/{contactID}/lastName`

**Request Body:**
```json
{
  "lastName": "Smith"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Contact last name updated successfully"
}
```

#### Update Contact Phone

**Endpoint:** `PUT /api/contacts/{contactID}/phone`

**Request Body:**
```json
{
  "phone": "9876543210"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Contact phone updated successfully"
}
```

#### Update Contact Address

**Endpoint:** `PUT /api/contacts/{contactID}/address`

**Request Body:**
```json
{
  "address": "456 Oak Ave"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Contact address updated successfully"
}
```

---

### Task Service

#### Create Task

**Endpoint:** `POST /api/tasks`

**Request Body:**
```json
{
  "taskID": "123",
  "name": "Complete Project",
  "description": "Finish the CS 320 project by deadline"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Task created successfully with ID: 123",
  "taskID": "123"
}
```

**Field Constraints:**
- `taskID`: max 10 characters, not null, must be unique
- `name`: max 20 characters, not null
- `description`: max 50 characters, not null

#### Delete Task

**Endpoint:** `DELETE /api/tasks/{taskID}`

**Success Response (200 OK):**
```json
{
  "message": "Task deleted successfully: 123"
}
```

#### Update Task Name

**Endpoint:** `PUT /api/tasks/{taskID}/name`

**Request Body:**
```json
{
  "name": "New Task Name"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Task name updated successfully"
}
```

#### Update Task Description

**Endpoint:** `PUT /api/tasks/{taskID}/description`

**Request Body:**
```json
{
  "description": "Updated task description"
}
```

**Success Response (200 OK):**
```json
{
  "message": "Task description updated successfully"
}
```

---

### Appointment Service

#### Create Appointment

**Endpoint:** `POST /api/appointments`

**Request Body:**
```json
{
  "appointmentID": "123",
  "date": "2026-12-31",
  "description": "Annual checkup"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Appointment created successfully with ID: 123",
  "appointmentID": "123"
}
```

**Field Constraints:**
- `appointmentID`: max 10 characters, not null, must be unique
- `date`: must be in the future, format: YYYY-MM-DD
- `description`: max 50 characters, not null

#### Delete Appointment

**Endpoint:** `DELETE /api/appointments/{appointmentID}`

**Success Response (200 OK):**
```json
{
  "message": "Appointment deleted successfully: 123"
}
```

---

## HTTP Status Codes

- **200 OK**: Request successful
- **201 Created**: Resource created successfully
- **400 Bad Request**: Invalid input or validation error
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected server error

## Error Handling

All endpoints return consistent error responses with a `message` field describing the error:

```json
{
  "message": "Error description here"
}
```

## Running the API

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Navigate to project directory
cd CS_320_Enhancement/CS_320_Project_One_Enhancement

# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

### Testing with cURL

#### Create a Contact
```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "contactID": "123",
    "firstName": "John",
    "lastName": "Doe",
    "phone": "1234567890",
    "address": "123 Main St"
  }'
```

#### Update Contact Phone
```bash
curl -X PUT http://localhost:8080/api/contacts/123/phone \
  -H "Content-Type: application/json" \
  -d '{"phone": "9876543210"}'
```

#### Delete Contact
```bash
curl -X DELETE http://localhost:8080/api/contacts/123
```

#### Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "taskID": "456",
    "name": "Complete Project",
    "description": "Finish the CS 320 project"
  }'
```

#### Create an Appointment
```bash
curl -X POST http://localhost:8080/api/appointments \
  -H "Content-Type: application/json" \
  -d '{
    "appointmentID": "789",
    "date": "2026-12-31",
    "description": "Annual checkup"
  }'
```

## Architecture

The RESTful API layer follows the existing layered architecture:

```
REST Controllers (API Layer)
    ↓
Controllers (Controller Layer)
    ↓
Services (Business Logic Layer)
    ↓
DAOs (Data Access Layer)
    ↓
Entities (Model Layer)
```

### Components

1. **REST Controllers** (`com.cs320.api` package):
   - `ContactRestController`: HTTP endpoints for contact operations
   - `TaskRestController`: HTTP endpoints for task operations
   - `AppointmentRestController`: HTTP endpoints for appointment operations

2. **Spring Boot Application**:
   - `ServiceManagementApplication`: Main entry point that bootstraps the REST API

3. **Existing Layers** (reused without modification):
   - Controllers: Input validation and delegation
   - Services: Business logic
   - DAOs: Data persistence
   - Entities: Data models

## Benefits

1. **Standard REST Conventions**: Uses HTTP methods (GET, POST, PUT, DELETE) appropriately
2. **JSON Format**: Industry-standard data interchange format
3. **Stateless**: Each request is independent, enabling scalability
4. **Minimal Changes**: Leverages existing architecture without modifying core business logic
5. **Extensible**: Easy to add authentication, versioning, or additional endpoints

## Future Enhancements

- Add GET endpoints to retrieve individual or all resources
- Implement authentication/authorization (JWT, OAuth2)
- Add API versioning (e.g., `/api/v1/contacts`)
- Implement pagination for list operations
- Add Swagger/OpenAPI documentation
- Implement rate limiting
- Add request/response logging
