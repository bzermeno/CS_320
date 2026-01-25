# Building and Testing Guide

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- JUnit 5 (will be downloaded automatically by the build script)

## Project Structure

The project follows a **layered architecture** with clear separation of concerns:

```
src/
├── ContactService/
│   ├── Contact.java           (Entity/Model)
│   ├── ContactDAO.java         (Data Access Layer)
│   ├── ContactService.java     (Business Logic Layer)
│   ├── ContactController.java  (Controller Layer)
│   └── *Test.java             (Unit Tests)
├── TaskService/
│   ├── Task.java              (Entity/Model)
│   ├── TaskDAO.java            (Data Access Layer)
│   ├── TaskService.java        (Business Logic Layer)
│   ├── TaskController.java     (Controller Layer)
│   └── *Test.java             (Unit Tests)
└── AppointmentService/
    ├── Appointment.java        (Entity/Model)
    ├── AppointmentDAO.java     (Data Access Layer)
    ├── AppointmentService.java (Business Logic Layer)
    ├── AppointmentController.java (Controller Layer)
    └── *Test.java             (Unit Tests)
```

## Building the Project

### Download JUnit Dependencies

First, download the required JUnit libraries:

```bash
mkdir -p lib
cd lib

# Download JUnit Platform Console Standalone (includes all dependencies)
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

cd ..
```

### Compile the Source Code

```bash
# Compile all main source files (non-test)
javac -d bin \
  src/ContactService/Contact.java \
  src/ContactService/ContactDAO.java \
  src/ContactService/ContactService.java \
  src/ContactService/ContactController.java \
  src/TaskService/Task.java \
  src/TaskService/TaskDAO.java \
  src/TaskService/TaskService.java \
  src/TaskService/TaskController.java \
  src/AppointmentService/Appointment.java \
  src/AppointmentService/AppointmentDAO.java \
  src/AppointmentService/AppointmentService.java \
  src/AppointmentService/AppointmentController.java
```

### Compile Test Files

```bash
# Compile test files
javac -cp "lib/*:bin" -d bin \
  src/ContactService/ContactTest.java \
  src/ContactService/ContactServiceTest.java \
  src/TaskService/TaskTest.java \
  src/TaskService/TaskServiceTest.java \
  src/AppointmentService/AppointmentTest.java \
  src/AppointmentService/AppointmentServiceTest.java
```

## Running Tests

### Run All Tests

```bash
# Run all tests
java -jar lib/junit-platform-console-standalone-1.9.3.jar -cp bin --scan-classpath
```

### Run Specific Test Class

```bash
# Run ContactServiceTest
java -jar lib/junit-platform-console-standalone-1.9.3.jar -cp bin --select-class=ContactService.ContactServiceTest

# Run TaskServiceTest
java -jar lib/junit-platform-console-standalone-1.9.3.jar -cp bin --select-class=TaskService.TaskServiceTest

# Run AppointmentServiceTest
java -jar lib/junit-platform-console-standalone-1.9.3.jar -cp bin --select-class=AppointmentService.AppointmentServiceTest
```

## Architecture Overview

For a detailed explanation of the layered architecture, please refer to [ARCHITECTURE.md](../ARCHITECTURE.md) in the root directory.

### Quick Summary

1. **Controller Layer**: Handles user input/output and delegates to Service layer
   - `ContactController`, `TaskController`, `AppointmentController`

2. **Service Layer**: Contains business logic and coordinates between Controller and DAO
   - `ContactService`, `TaskService`, `AppointmentService`

3. **DAO Layer**: Manages data persistence and provides CRUD operations
   - `ContactDAO`, `TaskDAO`, `AppointmentDAO`

4. **Model/Entity Layer**: Defines data structures with validation
   - `Contact`, `Task`, `Appointment`

## Testing Strategy

The project includes comprehensive unit tests for each layer:

- **Entity Tests**: Validate field constraints and validation logic
- **Service Tests**: Verify business logic and integration with DAO
- **Controller Tests**: (Can be added) Test input/output handling

## Known Issues

- One test in `ContactServiceTest.testCreateContactWithInvalidFields()` expects `IllegalArgumentException` but the `Contact` class throws `NullPointerException` for null values. This is a pre-existing inconsistency in the test expectations.

## Clean Build

To perform a clean build:

```bash
# Remove compiled files
rm -rf bin/*

# Remove downloaded libraries
rm -rf lib/*

# Then follow the build steps above
```
