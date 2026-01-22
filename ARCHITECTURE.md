# Layered Architecture Documentation

## Overview
This project implements a layered architecture pattern for ContactService, TaskService, and AppointmentService. The architecture separates concerns into distinct layers, making the code more maintainable, testable, and scalable.

## Architecture Layers

### 1. Entity Layer (Domain Models)
- **Contact.java** - Contact entity with validation
- **Task.java** - Task entity with validation
- **Appointment.java** - Appointment entity with validation

These classes represent the core business domain objects with their fields, validation rules, getters, and setters.

### 2. Data Access Layer (Repository Pattern)
- **ContactRepository.java** - Handles data storage operations for Contact entities
- **TaskRepository.java** - Handles data storage operations for Task entities
- **AppointmentRepository.java** - Handles data storage operations for Appointment entities

The repository classes provide a clean abstraction over data storage operations:
- `save(entity)` - Stores an entity
- `findById(id)` - Retrieves an entity by ID
- `existsById(id)` - Checks if an entity exists
- `deleteById(id)` - Removes an entity

### 3. Service Layer (Business Logic)
- **ContactService.java** - Business logic for contact management
- **TaskService.java** - Business logic for task management
- **AppointmentService.java** - Business logic for appointment management

The service classes handle business rules and coordinate with the repository layer:
- Input validation
- Business rule enforcement (e.g., unique IDs)
- Orchestration of repository operations

## Benefits of Layered Architecture

1. **Separation of Concerns**: Each layer has a specific responsibility
2. **Maintainability**: Changes to one layer don't affect others
3. **Testability**: Each layer can be tested independently
4. **Flexibility**: Easy to swap implementations (e.g., change from HashMap to database)
5. **Code Reusability**: Repository methods can be reused across services
6. **Dependency Injection**: Services can be constructed with custom repositories for testing

## Testing
All existing tests (21 tests total) pass successfully with the new architecture:
- ContactServiceTest: 9 tests ✔
- TaskServiceTest: 7 tests ✔
- AppointmentServiceTest: 5 tests ✔

The layered architecture maintains backward compatibility with all existing functionality.
