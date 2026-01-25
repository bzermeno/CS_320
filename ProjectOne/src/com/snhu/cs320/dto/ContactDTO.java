/*  Author Name: Beau Zermeno
 *  Date: January 25, 2026
 *  Course ID: CS-320
 *  Description: Data Transfer Object for Contact API requests and responses
 */
package com.snhu.cs320.dto;

public class ContactDTO {
    private String contactID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public ContactDTO() {
    }

    public ContactDTO(String contactID, String firstName, String lastName, String phone, String address) {
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
