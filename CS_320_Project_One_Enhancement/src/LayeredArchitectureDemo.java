/*  Author Name: Beau Zermeno
 *  Date: January 24, 2026 
 *  Course ID: CS-320
 *  Description: Demo class showcasing the layered architecture in action.
 *  This demonstrates how requests flow through Controller -> Service -> DAO layers.
 */

/**
 * Demonstration of the layered architecture.
 * Shows how the three layers (Controller, Service, DAO) work together.
 */
public class LayeredArchitectureDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Layered Architecture Demonstration ===\n");
        
        // Demonstrate Contact Management
        demonstrateContactManagement();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate Task Management
        demonstrateTaskManagement();
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Demonstrate Appointment Management
        demonstrateAppointmentManagement();
    }
    
    private static void demonstrateContactManagement() {
        System.out.println("Contact Management Demo:");
        System.out.println("-".repeat(50));
        
        // Using default constructors (Controller creates its own Service and DAO)
        ContactService.ContactController controller = new ContactService.ContactController();
        
        // Create a contact through the Controller layer
        System.out.println("\n1. Creating a contact:");
        System.out.println("   Request: Create contact with ID '12345'");
        String result1 = controller.createContact("12345", "John", "Doe", "1234567890", "123 Main St");
        System.out.println("   Response: " + result1);
        
        // Update contact through the Controller layer
        System.out.println("\n2. Updating contact first name:");
        System.out.println("   Request: Update contact '12345' first name to 'Jane'");
        String result2 = controller.updateContactFirstName("12345", "Jane");
        System.out.println("   Response: " + result2);
        
        // Try to create duplicate contact
        System.out.println("\n3. Attempting to create duplicate contact:");
        System.out.println("   Request: Create contact with duplicate ID '12345'");
        String result3 = controller.createContact("12345", "Bob", "Smith", "9876543210", "456 Oak St");
        System.out.println("   Response: " + result3);
        
        // Delete contact
        System.out.println("\n4. Deleting contact:");
        System.out.println("   Request: Delete contact '12345'");
        String result4 = controller.deleteContact("12345");
        System.out.println("   Response: " + result4);
    }
    
    private static void demonstrateTaskManagement() {
        System.out.println("Task Management Demo:");
        System.out.println("-".repeat(50));
        
        TaskService.TaskController controller = new TaskService.TaskController();
        
        // Create a task
        System.out.println("\n1. Creating a task:");
        System.out.println("   Request: Create task with ID 'TASK001'");
        String result1 = controller.createTask("TASK001", "Complete Project", "Finish the CS-320 project");
        System.out.println("   Response: " + result1);
        
        // Update task description
        System.out.println("\n2. Updating task description:");
        System.out.println("   Request: Update task 'TASK001' description");
        String result2 = controller.updateTaskDescription("TASK001", "Complete layered architecture project");
        System.out.println("   Response: " + result2);
        
        // Delete task
        System.out.println("\n3. Deleting task:");
        System.out.println("   Request: Delete task 'TASK001'");
        String result3 = controller.deleteTask("TASK001");
        System.out.println("   Response: " + result3);
    }
    
    private static void demonstrateAppointmentManagement() {
        System.out.println("Appointment Management Demo:");
        System.out.println("-".repeat(50));
        
        AppointmentService.AppointmentController controller = new AppointmentService.AppointmentController();
        
        // Create future date (tomorrow)
        java.util.Date futureDate = new java.util.Date(System.currentTimeMillis() + 86400000L); // +1 day
        
        // Create an appointment
        System.out.println("\n1. Creating an appointment:");
        System.out.println("   Request: Create appointment with ID 'APT001'");
        String result1 = controller.createAppointment("APT001", futureDate, "Team meeting");
        System.out.println("   Response: " + result1);
        
        // Get appointment
        System.out.println("\n2. Retrieving appointment:");
        System.out.println("   Request: Get appointment 'APT001'");
        String result2 = controller.getAppointment("APT001");
        System.out.println("   Response: " + result2);
        
        // Delete appointment
        System.out.println("\n3. Deleting appointment:");
        System.out.println("   Request: Delete appointment 'APT001'");
        String result3 = controller.deleteAppointment("APT001");
        System.out.println("   Response: " + result3);
        
        // Try to get deleted appointment
        System.out.println("\n4. Attempting to retrieve deleted appointment:");
        System.out.println("   Request: Get appointment 'APT001'");
        String result4 = controller.getAppointment("APT001");
        System.out.println("   Response: " + result4);
    }
}
