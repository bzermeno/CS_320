/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: Data Transfer Object for Appointment API requests and responses
 */
package com.snhu.cs320.dto;

import java.util.Date;

public class AppointmentDTO {
    private String appointmentID;
    private Date appointmentDate;
    private String description;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String appointmentID, Date appointmentDate, String description) {
        this.appointmentID = appointmentID;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
