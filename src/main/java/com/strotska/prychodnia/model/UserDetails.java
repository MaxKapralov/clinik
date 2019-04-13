package com.strotska.prychodnia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UserDetails extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String flatNumber;
    private String streetNumber;
    private String street;
    private String city;
    private String zipCode;
    @Column(unique = true)
    private String pesel;
    @OneToOne
    @JoinColumn(name = "identity_id", referencedColumnName = "id")
    private Identity identity;

    public UserDetails() {
    }

    public UserDetails(String name, String surname, String email, String phoneNumber, String flatNumber, String streetNumber, String street, String city, String zipCode, String pesel, Identity identity) {
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
        this.identity = identity;
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
}
