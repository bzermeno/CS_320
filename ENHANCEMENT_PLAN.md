# Project Enhancement Plan: Service Management System

**Author:** Beau Zermeno  
**Course:** CS-320  
**Date:** January 7, 2026  
**Category:** Software Enhancement - Expanded Complexity

## 1. Executive Summary

This document outlines a comprehensive enhancement plan for the existing Service Management System. The enhancement transforms the current in-memory service modules (ContactService, TaskService, AppointmentService) into a complete, production-ready application with unified management, persistent storage, and an interactive command-line interface.

**Scale of Enhancement:** This enhancement is equivalent in scope to porting the application to a new language, as it involves:
- Architectural redesign with new unified service layer
- Complete persistence layer implementation
- User interface development
- Extensive testing infrastructure updates

## 2. Current System Analysis

### 2.1 Existing Components
The current system consists of three independent service modules:

1. **ContactService**: Manages contact information (ID, name, phone, address)
2. **TaskService**: Manages tasks (ID, name, description)
3. **AppointmentService**: Manages appointments (ID, date, description)

### 2.2 Current Limitations
- **No Persistence**: Data is lost when application terminates
- **No Unified Interface**: Services operate independently
- **No User Interface**: Requires programmatic access only
- **Limited Integration**: No cross-service operations
- **No Data Export/Import**: Cannot share data between sessions

## 3. Proposed Enhancements

### 3.1 Enhancement Goals
1. **Unified Service Management**: Create a ServiceManager to coordinate all services
2. **Persistent Storage**: Implement file-based persistence for all data
3. **Command-Line Interface**: Provide interactive CLI for end users
4. **Data Portability**: Enable export/import functionality
5. **Enhanced Error Handling**: Improve user feedback and error recovery

### 3.2 New Components

#### 3.2.1 ServiceManager Class
Central coordinator for all services with the following responsibilities:
- Initialize and manage all three service instances
- Provide unified API for service operations
- Handle cross-service coordination
- Manage data persistence triggers

#### 3.2.2 DataPersistence Module
File-based storage system with:
- JSON serialization/deserialization
- Automatic save on modifications
- Load on startup
- Data backup functionality

#### 3.2.3 CommandLineInterface (CLI)
Interactive user interface providing:
- Menu-driven navigation
- CRUD operations for all services
- Data import/export
- User-friendly error messages

## 4. Technical Design

### 4.1 Architecture Diagram
```
┌─────────────────────────────────────────────────┐
│          CommandLineInterface (CLI)              │
│  - displayMenu()                                 │
│  - handleUserInput()                             │
│  - displayResults()                              │
└────────────────┬────────────────────────────────┘
                 │
                 ▼
┌─────────────────────────────────────────────────┐
│           ServiceManager                         │
│  - contactService: ContactService                │
│  - taskService: TaskService                      │
│  - appointmentService: AppointmentService        │
│  - dataPersistence: DataPersistence              │
│                                                   │
│  + getAllContacts()                              │
│  + getAllTasks()                                 │
│  + getAllAppointments()                          │
│  + saveAllData()                                 │
│  + loadAllData()                                 │
└────┬─────────┬──────────┬────────────────┬──────┘
     │         │          │                │
     ▼         ▼          ▼                ▼
┌──────────┐ ┌──────────┐ ┌──────────┐  ┌──────────────┐
│ Contact  │ │   Task   │ │Appointment│  │    Data      │
│ Service  │ │ Service  │ │  Service  │  │ Persistence  │
└──────────┘ └──────────┘ └──────────┘  └──────────────┘
```

### 4.2 Pseudocode

#### 4.2.1 ServiceManager Pseudocode
```
CLASS ServiceManager:
    PRIVATE contactService
    PRIVATE taskService
    PRIVATE appointmentService
    PRIVATE dataPersistence
    
    CONSTRUCTOR():
        contactService = NEW ContactService()
        taskService = NEW TaskService()
        appointmentService = NEW AppointmentService()
        dataPersistence = NEW DataPersistence()
        CALL loadAllData()
    
    METHOD addContact(contact):
        TRY:
            contactService.addContact(contact)
            dataPersistence.saveContacts(contactService.getAllContacts())
            RETURN success
        CATCH exception:
            RETURN error
    
    METHOD addTask(task):
        TRY:
            taskService.addTask(task)
            dataPersistence.saveTasks(taskService.getAllTasks())
            RETURN success
        CATCH exception:
            RETURN error
    
    METHOD addAppointment(appointment):
        TRY:
            appointmentService.addAppointment(appointment)
            dataPersistence.saveAppointments(appointmentService.getAllAppointments())
            RETURN success
        CATCH exception:
            RETURN error
    
    METHOD loadAllData():
        contacts = dataPersistence.loadContacts()
        FOR EACH contact IN contacts:
            contactService.addContact(contact)
        
        tasks = dataPersistence.loadTasks()
        FOR EACH task IN tasks:
            taskService.addTask(task)
        
        appointments = dataPersistence.loadAppointments()
        FOR EACH appointment IN appointments:
            appointmentService.addAppointment(appointment)
    
    METHOD getAllContacts():
        RETURN contactService.getAllContacts()
    
    METHOD getAllTasks():
        RETURN taskService.getAllTasks()
    
    METHOD getAllAppointments():
        RETURN appointmentService.getAllAppointments()
```

#### 4.2.2 DataPersistence Pseudocode
```
CLASS DataPersistence:
    PRIVATE CONSTANT CONTACTS_FILE = "contacts.json"
    PRIVATE CONSTANT TASKS_FILE = "tasks.json"
    PRIVATE CONSTANT APPOINTMENTS_FILE = "appointments.json"
    
    METHOD saveContacts(contacts):
        TRY:
            jsonData = convertToJSON(contacts)
            writeToFile(CONTACTS_FILE, jsonData)
            RETURN true
        CATCH IOException:
            LOG error
            RETURN false
    
    METHOD loadContacts():
        TRY:
            IF fileExists(CONTACTS_FILE):
                jsonData = readFromFile(CONTACTS_FILE)
                contacts = parseJSON(jsonData)
                RETURN contacts
            ELSE:
                RETURN empty list
        CATCH IOException:
            LOG error
            RETURN empty list
    
    METHOD saveTasks(tasks):
        TRY:
            jsonData = convertToJSON(tasks)
            writeToFile(TASKS_FILE, jsonData)
            RETURN true
        CATCH IOException:
            LOG error
            RETURN false
    
    METHOD loadTasks():
        TRY:
            IF fileExists(TASKS_FILE):
                jsonData = readFromFile(TASKS_FILE)
                tasks = parseJSON(jsonData)
                RETURN tasks
            ELSE:
                RETURN empty list
        CATCH IOException:
            LOG error
            RETURN empty list
    
    METHOD saveAppointments(appointments):
        TRY:
            jsonData = convertToJSON(appointments)
            writeToFile(APPOINTMENTS_FILE, jsonData)
            RETURN true
        CATCH IOException:
            LOG error
            RETURN false
    
    METHOD loadAppointments():
        TRY:
            IF fileExists(APPOINTMENTS_FILE):
                jsonData = readFromFile(APPOINTMENTS_FILE)
                appointments = parseJSON(jsonData)
                RETURN appointments
            ELSE:
                RETURN empty list
        CATCH IOException:
            LOG error
            RETURN empty list
```

#### 4.2.3 CommandLineInterface Pseudocode
```
CLASS CommandLineInterface:
    PRIVATE serviceManager
    PRIVATE scanner
    
    CONSTRUCTOR():
        serviceManager = NEW ServiceManager()
        scanner = NEW Scanner(System.in)
    
    METHOD run():
        DISPLAY welcome message
        WHILE true:
            displayMainMenu()
            choice = getUserInput()
            
            SWITCH choice:
                CASE 1:
                    handleContactMenu()
                CASE 2:
                    handleTaskMenu()
                CASE 3:
                    handleAppointmentMenu()
                CASE 4:
                    displayAllData()
                CASE 5:
                    EXIT program
                DEFAULT:
                    DISPLAY "Invalid choice"
    
    METHOD handleContactMenu():
        DISPLAY contact operations menu
        choice = getUserInput()
        
        SWITCH choice:
            CASE 1: // Add contact
                INPUT contactID, firstName, lastName, phone, address
                contact = NEW Contact(contactID, firstName, lastName, phone, address)
                result = serviceManager.addContact(contact)
                DISPLAY result message
            
            CASE 2: // Delete contact
                INPUT contactID
                result = serviceManager.deleteContact(contactID)
                DISPLAY result message
            
            CASE 3: // Update contact
                INPUT contactID and field to update
                result = serviceManager.updateContact(contactID, field, newValue)
                DISPLAY result message
            
            CASE 4: // List all contacts
                contacts = serviceManager.getAllContacts()
                FOR EACH contact IN contacts:
                    DISPLAY contact details
    
    METHOD handleTaskMenu():
        // Similar structure to handleContactMenu()
    
    METHOD handleAppointmentMenu():
        // Similar structure to handleContactMenu()
    
    METHOD displayAllData():
        DISPLAY "=== All Contacts ==="
        FOR EACH contact IN serviceManager.getAllContacts():
            DISPLAY contact
        
        DISPLAY "=== All Tasks ==="
        FOR EACH task IN serviceManager.getAllTasks():
            DISPLAY task
        
        DISPLAY "=== All Appointments ==="
        FOR EACH appointment IN serviceManager.getAllAppointments():
            DISPLAY appointment
```

### 4.3 Flowcharts

#### 4.3.1 Main Application Flow
```
                    START
                      │
                      ▼
              ┌───────────────┐
              │ Initialize    │
              │ ServiceManager│
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Load Data     │
              │ from Files    │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Display       │
              │ Main Menu     │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Get User      │
              │ Choice        │
              └───────┬───────┘
                      │
        ┌─────────────┼─────────────┬─────────────┐
        │             │             │             │
        ▼             ▼             ▼             ▼
    ┌────────┐   ┌────────┐   ┌────────┐   ┌────────┐
    │Contact │   │  Task  │   │Appoint.│   │  Exit  │
    │ Menu   │   │  Menu  │   │  Menu  │   │  App   │
    └───┬────┘   └───┬────┘   └───┬────┘   └───┬────┘
        │            │            │            │
        └────────────┴────────────┴────────────┘
                      │
                      ▼
              ┌───────────────┐
              │ Perform       │
              │ Operation     │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Auto-Save     │
              │ Data          │
              └───────┬───────┘
                      │
                      ▼
              Loop back to Main Menu
                   or EXIT
```

#### 4.3.2 Add Contact Flow
```
                    START
                      │
                      ▼
              ┌───────────────┐
              │ Prompt for    │
              │ Contact ID    │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Validate ID   │
              │ (≤10 chars)   │
              └───────┬───────┘
                      │
                ┌─────┴─────┐
                │           │
            Invalid      Valid
                │           │
                ▼           ▼
          ┌─────────┐  ┌───────────┐
          │ Display │  │ Prompt    │
          │ Error   │  │ First Name│
          └────┬────┘  └─────┬─────┘
               │             │
               │             ▼
               │      ┌───────────┐
               │      │ Validate  │
               │      │ (≤10 chars)│
               │      └─────┬─────┘
               │            │
               │      ┌─────┴─────┐
               │      │           │
               │   Invalid      Valid
               │      │           │
               │      ▼           ▼
               │  ┌─────────┐  Continue
               │  │ Display │  for all
               │  │ Error   │  fields...
               │  └────┬────┘
               │       │
               └───────┤
                       │
                       ▼
              ┌───────────────┐
              │ Create        │
              │ Contact Object│
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Add to        │
              │ ContactService│
              └───────┬───────┘
                      │
                ┌─────┴─────┐
                │           │
            Success      Failed
                │           │
                ▼           ▼
          ┌─────────┐  ┌─────────┐
          │ Save    │  │ Display │
          │ to File │  │ Error   │
          └────┬────┘  └────┬────┘
               │            │
               ▼            │
          ┌─────────┐       │
          │ Display │       │
          │ Success │       │
          └────┬────┘       │
               │            │
               └────────────┘
                      │
                      ▼
                    RETURN
```

#### 4.3.3 Data Persistence Flow
```
                Save Operation
                      │
                      ▼
              ┌───────────────┐
              │ Get all data  │
              │ from service  │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Convert to    │
              │ JSON format   │
              └───────┬───────┘
                      │
                      ▼
              ┌───────────────┐
              │ Open/Create   │
              │ file          │
              └───────┬───────┘
                      │
                ┌─────┴─────┐
                │           │
            Success      Failed
                │           │
                ▼           ▼
          ┌─────────┐  ┌─────────┐
          │ Write   │  │ Log     │
          │ JSON    │  │ Error   │
          └────┬────┘  └────┬────┘
               │            │
               ▼            ▼
          ┌─────────┐  ┌─────────┐
          │ Close   │  │ Return  │
          │ file    │  │ false   │
          └────┬────┘  └─────────┘
               │
               ▼
          ┌─────────┐
          │ Return  │
          │ true    │
          └─────────┘

                Load Operation
                      │
                      ▼
              ┌───────────────┐
              │ Check if      │
              │ file exists   │
              └───────┬───────┘
                      │
                ┌─────┴─────┐
                │           │
             Exists      Not Exists
                │           │
                ▼           ▼
          ┌─────────┐  ┌──────────┐
          │ Read    │  │ Return   │
          │ file    │  │ empty    │
          └────┬────┘  │ list     │
               │       └──────────┘
               ▼
          ┌─────────┐
          │ Parse   │
          │ JSON    │
          └────┬────┘
               │
         ┌─────┴─────┐
         │           │
     Success      Failed
         │           │
         ▼           ▼
    ┌─────────┐  ┌─────────┐
    │ Return  │  │ Log     │
    │ data    │  │ Error   │
    │ list    │  └────┬────┘
    └─────────┘       │
                      ▼
                 ┌─────────┐
                 │ Return  │
                 │ empty   │
                 │ list    │
                 └─────────┘
```

## 5. Implementation Strategy

### 5.1 Phase 1: Core Infrastructure (Week 1)
1. Implement ServiceManager class
2. Add getter methods to existing services for retrieving all data
3. Create basic structure for DataPersistence
4. Write unit tests for ServiceManager

### 5.2 Phase 2: Persistence Layer (Week 2)
1. Implement JSON serialization for all entity types
2. Implement file I/O operations
3. Add error handling and logging
4. Test save/load operations

### 5.3 Phase 3: CLI Development (Week 3)
1. Create CommandLineInterface class
2. Implement menu system
3. Add user input validation
4. Integrate with ServiceManager

### 5.4 Phase 4: Testing & Documentation (Week 4)
1. Comprehensive testing of all new components
2. Integration testing
3. Update documentation
4. User acceptance testing

## 6. Testing Strategy

### 6.1 Unit Tests
- ServiceManager operations
- DataPersistence save/load operations
- CLI input validation

### 6.2 Integration Tests
- End-to-end data flow
- Service coordination through ServiceManager
- Persistence after operations

### 6.3 User Acceptance Tests
- Complete user workflows
- Error recovery scenarios
- Data integrity verification

## 7. Expected Benefits

### 7.1 User Benefits
- **Persistent Data**: Data survives application restarts
- **Easy Access**: Command-line interface for non-technical users
- **Data Portability**: Export/import capabilities
- **Unified Experience**: Single interface for all services

### 7.2 Technical Benefits
- **Better Architecture**: Separation of concerns
- **Maintainability**: Centralized service management
- **Extensibility**: Easy to add new services
- **Testability**: Improved test coverage

## 8. Risk Assessment

### 8.1 Technical Risks
- **File I/O Failures**: Mitigated by error handling and backups
- **Data Corruption**: Mitigated by validation and JSON schema
- **Performance**: File operations are fast for expected data volumes

### 8.2 Compatibility Risks
- **Backward Compatibility**: New code maintains existing API
- **Data Migration**: No existing data to migrate (new feature)

## 9. Success Criteria

The enhancement will be considered successful when:
1. All existing unit tests pass
2. New components have ≥80% test coverage
3. Data persists across application restarts
4. CLI provides full CRUD operations for all services
5. Documentation is complete and accurate

## 10. Conclusion

This enhancement plan significantly expands the functionality of the Service Management System while maintaining backward compatibility with existing code. The addition of a unified service manager, persistent storage, and command-line interface transforms the system from a development library into a complete, user-ready application.

The scale of this enhancement is equivalent to porting the application to a new language, as it involves architectural redesign, new major components, and comprehensive testing infrastructure updates.

---

**Document Version:** 1.0  
**Status:** Ready for Implementation  
**Approved By:** Pending Review
