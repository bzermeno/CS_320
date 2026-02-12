/*  Author Name: Beau Zermeno
 *  Date: June 15, 2025 
 *  Course ID: CS-320
 *  Description: AppointmentTest class tests that an Appointment object with valid fields is created successfully,
 *  and that attempting to create an object with invalid fields throws the proper exceptions.
 */
package AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

public class AppointmentTest {
	private Appointment appointment;
	
    private Date getDateTwoWeeksOut() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14); // 2 weeks from current date
        return calendar.getTime();
    }
    
    private Date getDateFourWeeksOut() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 28); // 4 weeks from current date
        return calendar.getTime();
    }
    
    private Date getPastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -14); // 2 weeks before current date
        return calendar.getTime();
    }
    
    @BeforeEach
    public void setUp() {
        appointment = new Appointment("12345", getDateTwoWeeksOut(), "Annual check-up");
    }
    
    @Test
    public void testAppointmentCreatedSuccessfully() {
        assertEquals(appointment.getAppointmentID(), "12345");
        assertTrue(appointment.getAppointmentDate().after(new Date()));
        assertEquals(appointment.getDescription(), "Annual check-up");
    }

    @Test
    public void testInvalidAppointmentID() {
    	// appointmentID is null
    	assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, getDateTwoWeeksOut(), "Meeting");
        });
    	// appointmentID is too long
    	assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123456789012", getDateTwoWeeksOut(), "Meeting");
        });
    }
    
    @Test
    public void testInvalidDate() {
    	// appointmentDate is null
    	assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", null, "Meeting");
        });
    	// appointmentDate is before current date
    	assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", getPastDate(), "Meeting");
        });
    }

    @Test
    public void testInvalidDescription() {
        // description is null
    	assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", getDateTwoWeeksOut(), null);
        });
    	// description is too long
    	assertThrows(IllegalArgumentException.class, () -> {
    		new Appointment("12345", getDateTwoWeeksOut(), "Annual check-up 1234567890 1234567890 1234567890 1234567890");
    	});
    }
    
    @Test
    public void testSetAppointmentDate() {
    	// Set appointmentDate 4 weeks from current date
    	appointment.setAppointmentDate(getDateFourWeeksOut());
    	assertTrue(appointment.getAppointmentDate().after(new Date()));
    	// appointmentDate is null
    	assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(null);
        });
    	// appointmentDate is before current date
    	assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(getPastDate());
        });	
    }
    
    @Test
    public void testSetDescription() {
    	// Set description with valid field
    	appointment.setDescription("One-hour massage");
    	assertEquals(appointment.getDescription(), "One-hour massage");
    	// description is null
    	assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(null);
        });
    	// description is too long
    	assertThrows(IllegalArgumentException.class, () -> {
    		appointment.setDescription("Annual check-up 1234567890 1234567890 1234567890 1234567890");
    	});
    }
}

