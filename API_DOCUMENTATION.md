# RESTful API Documentation

This document describes the RESTful API endpoints available in the CS320 Service Management System.

## Base URL
```
http://localhost:8080/api
```

## Common HTTP Status Codes

- `200 OK` - Successful GET request
- `201 Created` - Successful POST request (resource created)
- `204 No Content` - Successful DELETE request
- `400 Bad Request` - Invalid data or validation error
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Unexpected server error

## Contact API

### Get All Contacts
- **URL**: `/api/contacts`
- **Method**: `GET`
- **Success Response**: `200 OK`
- **Response Body**: Array of Contact objects

Example:
```bash
curl http://localhost:8080/api/contacts
```

### Get Contact by ID
- **URL**: `/api/contacts/{id}`
- **Method**: `GET`
- **URL Parameters**: `id=[string]` - Contact ID
- **Success Response**: `200 OK`
- **Error Response**: `404 Not Found`

Example:
```bash
curl http://localhost:8080/api/contacts/C1
```

### Create Contact
- **URL**: `/api/contacts`
- **Method**: `POST`
- **Headers**: `Content-Type: application/json`
- **Request Body**:
```json
{
  "contactID": "string (max 10 chars)",
  "firstName": "string (max 10 chars)",
  "lastName": "string (max 10 chars)",
  "phone": "string (exactly 10 digits)",
  "address": "string (max 30 chars)"
}
```
- **Success Response**: `201 Created`
- **Error Response**: `400 Bad Request`

Example:
```bash
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{"contactID":"C1","firstName":"John","lastName":"Doe","phone":"1234567890","address":"123 Main St"}'
```

### Update Contact
- **URL**: `/api/contacts/{id}`
- **Method**: `PUT`
- **URL Parameters**: `id=[string]` - Contact ID
- **Headers**: `Content-Type: application/json`
- **Request Body** (all fields optional):
```json
{
  "firstName": "string (max 10 chars)",
  "lastName": "string (max 10 chars)",
  "phone": "string (exactly 10 digits)",
  "address": "string (max 30 chars)"
}
```
- **Success Response**: `200 OK`
- **Error Response**: `404 Not Found` or `400 Bad Request`

Example:
```bash
curl -X PUT http://localhost:8080/api/contacts/C1 \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane"}'
```

### Delete Contact
- **URL**: `/api/contacts/{id}`
- **Method**: `DELETE`
- **URL Parameters**: `id=[string]` - Contact ID
- **Success Response**: `204 No Content`
- **Error Response**: `404 Not Found`

Example:
```bash
curl -X DELETE http://localhost:8080/api/contacts/C1
```

## Task API

### Get All Tasks
- **URL**: `/api/tasks`
- **Method**: `GET`
- **Success Response**: `200 OK`
- **Response Body**: Array of Task objects

### Get Task by ID
- **URL**: `/api/tasks/{id}`
- **Method**: `GET`
- **URL Parameters**: `id=[string]` - Task ID
- **Success Response**: `200 OK`
- **Error Response**: `404 Not Found`

### Create Task
- **URL**: `/api/tasks`
- **Method**: `POST`
- **Headers**: `Content-Type: application/json`
- **Request Body**:
```json
{
  "taskID": "string (max 10 chars)",
  "name": "string (max 20 chars)",
  "description": "string (max 50 chars)"
}
```
- **Success Response**: `201 Created`
- **Error Response**: `400 Bad Request`

Example:
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"taskID":"T1","name":"Design API","description":"Design the RESTful API endpoints"}'
```

### Update Task
- **URL**: `/api/tasks/{id}`
- **Method**: `PUT`
- **URL Parameters**: `id=[string]` - Task ID
- **Headers**: `Content-Type: application/json`
- **Request Body** (all fields optional):
```json
{
  "name": "string (max 20 chars)",
  "description": "string (max 50 chars)"
}
```
- **Success Response**: `200 OK`
- **Error Response**: `404 Not Found` or `400 Bad Request`

### Delete Task
- **URL**: `/api/tasks/{id}`
- **Method**: `DELETE`
- **URL Parameters**: `id=[string]` - Task ID
- **Success Response**: `204 No Content`
- **Error Response**: `404 Not Found`

## Appointment API

### Get All Appointments
- **URL**: `/api/appointments`
- **Method**: `GET`
- **Success Response**: `200 OK`
- **Response Body**: Array of Appointment objects

### Get Appointment by ID
- **URL**: `/api/appointments/{id}`
- **Method**: `GET`
- **URL Parameters**: `id=[string]` - Appointment ID
- **Success Response**: `200 OK`
- **Error Response**: `404 Not Found`

### Create Appointment
- **URL**: `/api/appointments`
- **Method**: `POST`
- **Headers**: `Content-Type: application/json`
- **Request Body**:
```json
{
  "appointmentID": "string (max 10 chars)",
  "appointmentDate": "number (Unix timestamp in milliseconds, must be in future)",
  "description": "string (max 50 chars)"
}
```
- **Success Response**: `201 Created`
- **Error Response**: `400 Bad Request`

Example:
```bash
# Create appointment for tomorrow
FUTURE_DATE=$(($(date +%s)*1000 + 86400000))
curl -X POST http://localhost:8080/api/appointments \
  -H "Content-Type: application/json" \
  -d "{\"appointmentID\":\"A1\",\"appointmentDate\":${FUTURE_DATE},\"description\":\"Doctor checkup\"}"
```

### Delete Appointment
- **URL**: `/api/appointments/{id}`
- **Method**: `DELETE`
- **URL Parameters**: `id=[string]` - Appointment ID
- **Success Response**: `204 No Content`
- **Error Response**: `404 Not Found`

## Error Response Format

All error responses follow a consistent JSON format:

```json
{
  "timestamp": "2026-01-25T05:00:00.000Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid phone number",
  "path": "/api/contacts"
}
```

## Running the Application

### Build the Application
```bash
cd ProjectOne
mvn clean package
```

### Run the Application
```bash
java -jar target/cs320-project-1.0.0.jar
```

The application will start on port 8080. You can access the API at `http://localhost:8080/api`.

### Run Tests
```bash
mvn test
```

## Architecture

The application follows a layered architecture:

1. **Controller Layer** (`api.controller` package) - REST endpoints
2. **Service Layer** (Service classes) - Business logic
3. **Repository Layer** (Repository classes) - Data access
4. **Entity Layer** (Entity classes) - Domain models

All layers use Spring's dependency injection for proper singleton management and testability.
