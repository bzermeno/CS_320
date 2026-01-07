/*  Author Name: Beau Zermeno
 *  Date: January 7, 2026
 *  Course ID: CS-320
 *  Description: DataPersistence class handles file-based storage for all service data.
 *  Uses JSON format for serialization and provides save/load operations for contacts,
 *  tasks, and appointments.
 */
package ServiceManager;

import ContactService.Contact;
import TaskService.Task;
import AppointmentService.Appointment;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataPersistence {
    private static final String DATA_DIR = "data";
    private static final String CONTACTS_FILE = DATA_DIR + "/contacts.json";
    private static final String TASKS_FILE = DATA_DIR + "/tasks.json";
    private static final String APPOINTMENTS_FILE = DATA_DIR + "/appointments.json";
    
    public DataPersistence() {
        // Create data directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            System.err.println("Warning: Could not create data directory: " + e.getMessage());
        }
    }
    
    // Contact persistence methods
    public void saveContacts(Map<String, Contact> contacts) {
        try {
            StringBuilder json = new StringBuilder("{\n");
            boolean first = true;
            
            for (Map.Entry<String, Contact> entry : contacts.entrySet()) {
                if (!first) {
                    json.append(",\n");
                }
                first = false;
                
                Contact c = entry.getValue();
                json.append("  \"").append(escapeJson(c.getContactID())).append("\": {\n");
                json.append("    \"contactID\": \"").append(escapeJson(c.getContactID())).append("\",\n");
                json.append("    \"firstName\": \"").append(escapeJson(c.getFirstName())).append("\",\n");
                json.append("    \"lastName\": \"").append(escapeJson(c.getLastName())).append("\",\n");
                json.append("    \"phone\": \"").append(escapeJson(c.getPhone())).append("\",\n");
                json.append("    \"address\": \"").append(escapeJson(c.getAddress())).append("\"\n");
                json.append("  }");
            }
            
            json.append("\n}");
            
            Files.write(Paths.get(CONTACTS_FILE), json.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }
    
    public Map<String, Contact> loadContacts() {
        Map<String, Contact> contacts = new HashMap<>();
        
        try {
            if (!Files.exists(Paths.get(CONTACTS_FILE))) {
                return contacts;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(CONTACTS_FILE)));
            contacts = parseContactsJson(content);
        } catch (IOException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
        
        return contacts;
    }
    
    // Task persistence methods
    public void saveTasks(Map<String, Task> tasks) {
        try {
            StringBuilder json = new StringBuilder("{\n");
            boolean first = true;
            
            for (Map.Entry<String, Task> entry : tasks.entrySet()) {
                if (!first) {
                    json.append(",\n");
                }
                first = false;
                
                Task t = entry.getValue();
                json.append("  \"").append(escapeJson(t.getTaskID())).append("\": {\n");
                json.append("    \"taskID\": \"").append(escapeJson(t.getTaskID())).append("\",\n");
                json.append("    \"name\": \"").append(escapeJson(t.getName())).append("\",\n");
                json.append("    \"description\": \"").append(escapeJson(t.getDescription())).append("\"\n");
                json.append("  }");
            }
            
            json.append("\n}");
            
            Files.write(Paths.get(TASKS_FILE), json.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    public Map<String, Task> loadTasks() {
        Map<String, Task> tasks = new HashMap<>();
        
        try {
            if (!Files.exists(Paths.get(TASKS_FILE))) {
                return tasks;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(TASKS_FILE)));
            tasks = parseTasksJson(content);
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        
        return tasks;
    }
    
    // Appointment persistence methods
    public void saveAppointments(Map<String, Appointment> appointments) {
        try {
            StringBuilder json = new StringBuilder("{\n");
            boolean first = true;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            
            for (Map.Entry<String, Appointment> entry : appointments.entrySet()) {
                if (!first) {
                    json.append(",\n");
                }
                first = false;
                
                Appointment a = entry.getValue();
                json.append("  \"").append(escapeJson(a.getAppointmentID())).append("\": {\n");
                json.append("    \"appointmentID\": \"").append(escapeJson(a.getAppointmentID())).append("\",\n");
                json.append("    \"appointmentDate\": \"").append(dateFormat.format(a.getAppointmentDate())).append("\",\n");
                json.append("    \"description\": \"").append(escapeJson(a.getDescription())).append("\"\n");
                json.append("  }");
            }
            
            json.append("\n}");
            
            Files.write(Paths.get(APPOINTMENTS_FILE), json.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
        }
    }
    
    public Map<String, Appointment> loadAppointments() {
        Map<String, Appointment> appointments = new HashMap<>();
        
        try {
            if (!Files.exists(Paths.get(APPOINTMENTS_FILE))) {
                return appointments;
            }
            
            String content = new String(Files.readAllBytes(Paths.get(APPOINTMENTS_FILE)));
            appointments = parseAppointmentsJson(content);
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
        
        return appointments;
    }
    
    // JSON parsing helpers
    private Map<String, Contact> parseContactsJson(String json) {
        Map<String, Contact> contacts = new HashMap<>();
        
        // Simple JSON parser for our specific format
        String[] entries = json.split("\\},\\s*\"");
        
        for (String entry : entries) {
            try {
                String contactID = extractValue(entry, "contactID");
                String firstName = extractValue(entry, "firstName");
                String lastName = extractValue(entry, "lastName");
                String phone = extractValue(entry, "phone");
                String address = extractValue(entry, "address");
                
                if (contactID != null && !contactID.isEmpty()) {
                    Contact contact = new Contact(contactID, firstName, lastName, phone, address);
                    contacts.put(contactID, contact);
                }
            } catch (Exception e) {
                System.err.println("Error parsing contact entry: " + e.getMessage());
            }
        }
        
        return contacts;
    }
    
    private Map<String, Task> parseTasksJson(String json) {
        Map<String, Task> tasks = new HashMap<>();
        
        String[] entries = json.split("\\},\\s*\"");
        
        for (String entry : entries) {
            try {
                String taskID = extractValue(entry, "taskID");
                String name = extractValue(entry, "name");
                String description = extractValue(entry, "description");
                
                if (taskID != null && !taskID.isEmpty()) {
                    Task task = new Task(taskID, name, description);
                    tasks.put(taskID, task);
                }
            } catch (Exception e) {
                System.err.println("Error parsing task entry: " + e.getMessage());
            }
        }
        
        return tasks;
    }
    
    private Map<String, Appointment> parseAppointmentsJson(String json) {
        Map<String, Appointment> appointments = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        String[] entries = json.split("\\},\\s*\"");
        
        for (String entry : entries) {
            try {
                String appointmentID = extractValue(entry, "appointmentID");
                String dateStr = extractValue(entry, "appointmentDate");
                String description = extractValue(entry, "description");
                
                if (appointmentID != null && !appointmentID.isEmpty() && dateStr != null) {
                    Date date = dateFormat.parse(dateStr);
                    Appointment appointment = new Appointment(appointmentID, date, description);
                    appointments.put(appointmentID, appointment);
                }
            } catch (Exception e) {
                System.err.println("Error parsing appointment entry: " + e.getMessage());
            }
        }
        
        return appointments;
    }
    
    private String extractValue(String json, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(json);
        
        if (m.find()) {
            return unescapeJson(m.group(1));
        }
        
        return null;
    }
    
    private String escapeJson(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
    
    private String unescapeJson(String str) {
        if (str == null) {
            return "";
        }
        return str.replace("\\\"", "\"")
                  .replace("\\\\", "\\")
                  .replace("\\n", "\n")
                  .replace("\\r", "\r")
                  .replace("\\t", "\t");
    }
}
