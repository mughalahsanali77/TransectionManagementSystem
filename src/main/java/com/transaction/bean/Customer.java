package com.transaction.bean;

public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String address;
    private String contactNumber;

    public Customer() {
    }

    public Customer(Integer customerId, String firstName, String lastName, String city, String state, String address, String contactNumber) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public Customer(String firstName, String lastName, String city, String state, String address, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Customer = ( " +
                "CUSTOMER_ID = " + customerId +
                "\nFIRST_NAME = " + firstName  +
                "\nLAST_NAME = " + lastName +
                "\nCITY = " + city +
                "\nSTATE = " + state +
                "\nADDRESS = " + address +
                "\nCONTACT_NUMBER = " + contactNumber + ")";
    }
}
