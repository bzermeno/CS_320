# Layered Architecture Documentation

## Overview

This project implements a **three-tier layered architecture** comprising Controller, Service, and Data Access Object (DAO) layers. This architecture adheres to software engineering best practices including modularity, maintainability, scalability, and separation of concerns.

## Architecture Layers

### 1. Controller Layer

**Purpose**: Handles incoming requests and user interactions. Acts as the entry point for all operations.

**Responsibilities**:
- Validate user input
- Handle HTTP requests or user interactions (CLI, GUI, API)
- Format and return responses to the user
- Delegate business logic processing to the Service layer
- Catch and handle exceptions, converting them to user-friendly messages

**Key Characteristics**:
- No business logic
- No direct database/storage access
- Focuses on input/output operations
- Returns formatted success/error messages

**Implementation**:
- `ContactController.java` - Handles contact-related requests
- `TaskController.java` - Handles task-related requests
- `AppointmentController.java` - Handles appointment-related requests

**Example Flow**:
```
User Request → Controller validates input → Controller calls Service → Controller returns response
```

### 2. Service Layer

**Purpose**: Contains the core business logic and rules of the application.

**Responsibilities**:
- Implement business rules and validation
- Coordinate operations between Controller and DAO layers
- Perform data transformations and computations
- Ensure consistent application logic across different interfaces
- Orchestrate complex operations involving multiple entities

**Key Characteristics**:
- No direct user interaction handling
- No knowledge of data storage implementation details
- Focuses on "what" needs to be done, not "how" data is stored
- Delegates data persistence to DAO layer

**Implementation**:
- `ContactService.java` - Business logic for contact management
- `TaskService.java` - Business logic for task management
- `AppointmentService.java` - Business logic for appointment management

**Example Flow**:
```
Controller → Service validates business rules → Service calls DAO → Service returns result
```

### 3. Data Access Object (DAO) Layer

**Purpose**: Manages all data persistence and retrieval operations.

**Responsibilities**:
- Encapsulate data storage implementation (currently HashMap, could be database)
- Provide CRUD operations (Create, Read, Update, Delete)
- Handle data integrity at the storage level
- Abstract storage mechanism from business logic

**Key Characteristics**:
- No business logic
- Provides consistent interface for data operations
- Can be easily replaced with different storage implementations (e.g., SQL, NoSQL, file system)
- Returns Optional<T> for find operations to handle missing data gracefully

**Implementation**:
- `ContactDAO.java` - Data persistence for contacts
- `TaskDAO.java` - Data persistence for tasks
- `AppointmentDAO.java` - Data persistence for appointments

**Standard Methods**:
- `save(Entity entity)` - Create new entity
- `update(Entity entity)` - Update existing entity
- `delete(String id)` - Delete entity by ID
- `findById(String id)` - Retrieve entity by ID (returns Optional)
- `exists(String id)` - Check if entity exists

**Example Flow**:
```
Service → DAO performs storage operation → DAO returns result
```

### 4. Model/Entity Layer

**Purpose**: Defines data structures and domain objects.

**Responsibilities**:
- Define entity structure with fields
- Enforce field-level validation
- Provide getters and setters
- Encapsulate entity state

**Implementation**:
- `Contact.java` - Contact entity with validation
- `Task.java` - Task entity with validation
- `Appointment.java` - Appointment entity with validation

## Benefits of Layered Architecture

### 1. Separation of Concerns
Each layer has a specific responsibility, making the code easier to understand and maintain.

### 2. Modularity
Components are loosely coupled, allowing individual layers to be modified without affecting others.

### 3. Testability
Each layer can be tested independently:
- Controllers can be tested with mock services
- Services can be tested with mock DAOs
- DAOs can be tested with in-memory storage

### 4. Maintainability
Changes are localized to specific layers:
- Change storage from HashMap to SQL? Only modify DAO layer
- Add REST API? Create new controllers without touching services
- Update business rules? Only modify service layer

### 5. Scalability
- Layers can be deployed independently in distributed systems
- Easy to add caching, logging, or monitoring at specific layers
- Can scale horizontally by adding more instances of specific layers

### 6. Flexibility
- Different user interfaces (CLI, GUI, REST API) can share the same service and DAO layers
- Storage implementation can be changed without affecting business logic
- Business rules can evolve without changing data access or presentation logic

## Data Flow Example

Here's a complete flow for creating a contact:

```
1. User calls: contactController.createContact("123", "John", "Doe", "1234567890", "123 Main St")
   ↓
2. Controller validates input and creates Contact entity
   ↓
3. Controller calls: contactService.addContact(contact)
   ↓
4. Service validates business rules (contact not null)
   ↓
5. Service calls: contactDAO.save(contact)
   ↓
6. DAO checks uniqueness and stores in HashMap
   ↓
7. DAO returns success to Service
   ↓
8. Service returns success to Controller
   ↓
9. Controller returns: "Contact created successfully with ID: 123"
```

## Dependency Injection

The architecture supports dependency injection, allowing layers to receive their dependencies through constructors:

```java
// Manual dependency injection
ContactDAO dao = new ContactDAO();
ContactService service = new ContactService(dao);
ContactController controller = new ContactController(service);

// Or use default constructors for convenience
ContactController controller = new ContactController(); // Creates its own dependencies
```

This design enables:
- Easy mocking for unit tests
- Flexibility in wiring components
- Better control over object lifecycle

## Testing Strategy

### Controller Tests
- Test input validation
- Test response formatting
- Mock service layer
- Verify correct service method calls

### Service Tests
- Test business logic
- Test business rule enforcement
- Mock DAO layer
- Verify correct DAO method calls

### DAO Tests
- Test CRUD operations
- Test data integrity
- Use real or in-memory storage
- Verify correct storage behavior

### Integration Tests
- Test complete flow through all layers
- Verify layers work together correctly
- Test with real components (no mocks)

## Best Practices Implemented

1. **Single Responsibility Principle**: Each layer has one clear responsibility
2. **Dependency Inversion Principle**: Layers depend on abstractions (interfaces implied by method contracts)
3. **Open/Closed Principle**: Easy to extend functionality without modifying existing code
4. **Don't Repeat Yourself (DRY)**: Common operations encapsulated in respective layers
5. **Keep It Simple (KISS)**: Clear, straightforward implementation
6. **Consistent Error Handling**: Exceptions propagate appropriately through layers

## Future Enhancements

This architecture is designed to support future enhancements:

1. **Add REST API**: Create REST controllers that use existing services
2. **Add Database**: Replace DAO implementations with JPA/Hibernate
3. **Add Caching**: Implement caching in service or DAO layer
4. **Add Logging**: Add cross-cutting concerns via AOP or decorators
5. **Add Authentication**: Add security layer above controllers
6. **Add Validation Framework**: Integrate Bean Validation (JSR 380)
7. **Add Transaction Management**: Implement transactions in service layer

## Conclusion

This layered architecture provides a solid foundation for building maintainable, scalable, and testable software systems. Each layer is clearly defined with specific responsibilities, enabling the system to evolve and grow without becoming tangled or brittle.
