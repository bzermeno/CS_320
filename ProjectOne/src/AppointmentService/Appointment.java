/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Appointment class allows user to create an Appointment object with a unique appointmentID,
 *  with appointmentDate and description fields. The fields must meet the given constraints. 
 */
package AppointmentService;

import java.util.Date;

public class Appointment {
	private final String appointmentID;
	private Date appointmentDate;
	private String description;
	
	public Appointment(String appointmentID, Date appointmentDate, String description) {
		if (appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Invalid ID");
		}
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid date");
		}
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.appointmentID = appointmentID;
		this.appointmentDate = appointmentDate;
		this.description = description;
	}

	// Getter functions
	public String getAppointmentID() {
			return appointmentID;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	// Setter functions with validation
	public void setAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid date");
		}
		this.appointmentDate = appointmentDate;
	}

	public void setDescription(String description) {
		if (description == null || description.length() > 50) {
			throw new IllegalArgumentException("Invalid description");
		}
		this.description = description;
	}

	
	
}
