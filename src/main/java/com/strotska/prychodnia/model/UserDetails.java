package com.strotska.prychodnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserDetails extends BaseEntity {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String flatNumber;
    private String street;
    private String city;
    private String zipCode;
    @Column(unique = true)
    private String pesel;
    @OneToOne
    @JoinColumn(name = "identity_id", referencedColumnName = "id")
    @JsonIgnore
    private Identity identity;

    public UserDetails() {
    }

    public UserDetails(String name, String surname, String email, String phoneNumber, String flatNumber, String street, String city, String zipCode, String pesel, Identity identity) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flatNumber = flatNumber;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
