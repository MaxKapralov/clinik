package com.strotska.prychodnia.model.dto;

import com.strotska.prychodnia.model.BaseEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserDetailsDTO extends BaseEntity {
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
    private String street;
    @NotEmpty
    private String city;
    @NotEmpty
    private String zipCode;
    @Pattern(regexp = "^[0-9]{11}$")
    private String pesel;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(@NotEmpty String name, @NotEmpty String surname, @Email String email, @Pattern(regexp = "^\\d{9}$") String phoneNumber, @NotEmpty String flatNumber, @NotEmpty String street, @NotEmpty String city, @NotEmpty String zipCode, @Pattern(regexp = "^[0-9]{11}$") String pesel) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flatNumber = flatNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.pesel = pesel;
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
}
