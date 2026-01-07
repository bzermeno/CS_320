/*  Author Name: Beau Zermeno
 *  Date: January 7, 2026
 *  Course ID: CS-320
 *  Description: ServiceManager class provides unified management of ContactService, TaskService, 
 *  and AppointmentService. It coordinates operations across all services and integrates with
 *  DataPersistence for automatic data saving.
 */
package ServiceManager;

import ContactService.Contact;
import ContactService.ContactService;
import TaskService.Task;
import TaskService.TaskService;
import AppointmentService.Appointment;
import AppointmentService.AppointmentService;
import java.util.Map;

public class ServiceManager {
    private final ContactService contactService;
    private final TaskService taskService;
    private final AppointmentService appointmentService;
    private final DataPersistence dataPersistence;
    
    public ServiceManager() {
        this.contactService = new ContactService();
        this.taskService = new TaskService();
        this.appointmentService = new AppointmentService();
        this.dataPersistence = new DataPersistence();
        loadAllData();
    }
    
    // Contact operations
    public void addContact(Contact contact) {
        contactService.addContact(contact);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public void deleteContact(String contactID) {
        contactService.deleteContact(contactID);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public void updateContactFirstName(String contactID, String firstName) {
        contactService.updateFirstName(contactID, firstName);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public void updateContactLastName(String contactID, String lastName) {
        contactService.updateLastName(contactID, lastName);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public void updateContactPhone(String contactID, String phone) {
        contactService.updatePhone(contactID, phone);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public void updateContactAddress(String contactID, String address) {
        contactService.updateAddress(contactID, address);
        dataPersistence.saveContacts(contactService.getAllContacts());
    }
    
    public Map<String, Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
    
    // Task operations
    public void addTask(Task task) {
        taskService.addTask(task);
        dataPersistence.saveTasks(taskService.getAllTasks());
    }
    
    public void deleteTask(String taskID) {
        taskService.deleteTask(taskID);
        dataPersistence.saveTasks(taskService.getAllTasks());
    }
    
    public void updateTaskName(String taskID, String name) {
        taskService.updateName(taskID, name);
        dataPersistence.saveTasks(taskService.getAllTasks());
    }
    
    public void updateTaskDescription(String taskID, String description) {
        taskService.updateDescription(taskID, description);
        dataPersistence.saveTasks(taskService.getAllTasks());
    }
    
    public Map<String, Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    
    // Appointment operations
    public void addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
        dataPersistence.saveAppointments(appointmentService.getAllAppointments());
    }
    
    public void deleteAppointment(String appointmentID) {
        appointmentService.deleteAppointment(appointmentID);
        dataPersistence.saveAppointments(appointmentService.getAllAppointments());
    }
    
    public Map<String, Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    // Data persistence operations
    private void loadAllData() {
        Map<String, Contact> contacts = dataPersistence.loadContacts();
        for (Contact contact : contacts.values()) {
            try {
                contactService.addContact(contact);
            } catch (IllegalArgumentException e) {
                // Skip duplicate or invalid contacts
                System.err.println("Skipping invalid contact: " + e.getMessage());
            }
        }
        
        Map<String, Task> tasks = dataPersistence.loadTasks();
        for (Task task : tasks.values()) {
            try {
                taskService.addTask(task);
            } catch (IllegalArgumentException e) {
                // Skip duplicate or invalid tasks
                System.err.println("Skipping invalid task: " + e.getMessage());
            }
        }
        
        Map<String, Appointment> appointments = dataPersistence.loadAppointments();
        for (Appointment appointment : appointments.values()) {
            try {
                appointmentService.addAppointment(appointment);
            } catch (IllegalArgumentException e) {
                // Skip duplicate or invalid appointments
                System.err.println("Skipping invalid appointment: " + e.getMessage());
            }
        }
    }
    
    public void saveAllData() {
        dataPersistence.saveContacts(contactService.getAllContacts());
        dataPersistence.saveTasks(taskService.getAllTasks());
        dataPersistence.saveAppointments(appointmentService.getAllAppointments());
    }
}
