/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: Appointment class allows user to create an Appointment object with a unique appointmentID,
 *  with appointmentDate and description fields. The fields must meet the given constraints.
 *  Updated: January 20, 2026 
 */
package AppointmentService;

import java.util.Date;

public class Appointment {
	private final String appointmentID;  // appointmentID is immutable
	private Date appointmentDate;
	private String description;
	
	// Initialize length constraints
	private final int MAX_ID_LENGTH = 10;		
	private final int MAX_DESCRIPTION_LENGTH = 50;
	
	public Appointment(String appointmentID, Date appointmentDate, String description) {
		if (appointmentID == null) {
			throw new NullPointerException("appointmentID cannot be null");
		}
		if (appointmentID.length() > MAX_ID_LENGTH) {
			throw new IllegalArgumentException("appointmentID length cannot exceed 10 characters");
		}
		if (appointmentDate == null) {
			throw new NullPointerException("appointmentDate cannot be null");
		}
		if (appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("appointmentDate cannot be before current date");
		}
		if (description == null) {
			throw new NullPointerException("description cannot be null");
		}
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new IllegalArgumentException("description length cannot exceed 50 characters");
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
		if (appointmentDate == null) {
			throw new NullPointerException("appointmentDate cannot be null");
		}
		if (appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("appointmentDate cannot be before current date");
		}
		this.appointmentDate = appointmentDate;
	}

	public void setDescription(String description) {
		if (description == null) {
			throw new NullPointerException("description cannot be null");
		}
		if (description.length() > MAX_DESCRIPTION_LENGTH) {
			throw new IllegalArgumentException("description length cannot exceed 50 characters");
		}
		this.description = description;
	}

	
	
}
