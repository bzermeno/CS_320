/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: Integration tests for ContactController REST API endpoints
 */
package com.snhu.cs320.controller.test;

import com.snhu.cs320.ServiceManagementApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ServiceManagementApplication.class)
@AutoConfigureMockMvc
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateContact() throws Exception {
        String contactJson = "{\"contactID\":\"1\",\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                "\"phone\":\"1234567890\",\"address\":\"123 Main St\"}";

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Contact created successfully"))
                .andExpect(jsonPath("$.contactID").value("1"));
    }

    @Test
    public void testCreateContactWithDuplicateID() throws Exception {
        String contactJson = "{\"contactID\":\"2\",\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                "\"phone\":\"1234567890\",\"address\":\"123 Main St\"}";

        // Create first contact
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isCreated());

        // Try to create duplicate
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void testUpdateContactFirstName() throws Exception {
        String contactJson = "{\"contactID\":\"3\",\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                "\"phone\":\"1234567890\",\"address\":\"123 Main St\"}";

        // Create contact
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isCreated());

        // Update first name
        String updateJson = "{\"firstName\":\"Jane\"}";
        mockMvc.perform(put("/api/contacts/3/firstName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("First name updated successfully"));
    }

    @Test
    public void testDeleteContact() throws Exception {
        String contactJson = "{\"contactID\":\"4\",\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                "\"phone\":\"1234567890\",\"address\":\"123 Main St\"}";

        // Create contact
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isCreated());

        // Delete contact
        mockMvc.perform(delete("/api/contacts/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Contact deleted successfully"));
    }

    @Test
    public void testCreateContactWithInvalidPhone() throws Exception {
        String contactJson = "{\"contactID\":\"5\",\"firstName\":\"John\",\"lastName\":\"Doe\"," +
                "\"phone\":\"123\",\"address\":\"123 Main St\"}";

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
