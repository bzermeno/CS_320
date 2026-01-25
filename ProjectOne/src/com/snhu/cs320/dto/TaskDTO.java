/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: Data Transfer Object for Task API requests and responses
 */
package com.snhu.cs320.dto;

public class TaskDTO {
    private String taskID;
    private String name;
    private String description;

    public TaskDTO() {
    }

    public TaskDTO(String taskID, String name, String description) {
        this.taskID = taskID;
        this.name = name;
        this.description = description;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
