package com.strotska.prychodnia.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{9}$")
    private String phoneNumber;
    @NotEmpty
    private String flatNumber;
    @NotEmpty
    private String streetNumber;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String zipCode;
    @Pattern(regexp = "^[0-9]{11}$")
    private String pesel;
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
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
