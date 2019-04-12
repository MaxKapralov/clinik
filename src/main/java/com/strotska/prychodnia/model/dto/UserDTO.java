package com.strotska.prychodnia.model.dto;

public class UserDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String flatNumber;
    private String streetNumber;
    private String street;
    private String city;
    private String zipCode;
    private String pesel;
    private String password;

    public UserDTO() {
    }

    public UserDTO(String name, String surname, String email, String phoneNumber, String flatNumber, String streetNumber, String street, String city, String zipCode, String pesel, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flatNumber = flatNumber;
        this.streetNumber = streetNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.pesel = pesel;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPesel() {
        return pesel;
    }

    public String getPassword() {
        return password;
    }
}
