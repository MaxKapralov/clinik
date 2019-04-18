package com.strotska.prychodnia.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor extends BaseEntity {

    private String name;

    private String surname;

    private String specialty; // todo make enum

    @ManyToMany
    @JoinTable(
            name = "services_doctors",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Service> services;

    @OneToMany
    private Set<Appointment> appointments;

    public Doctor() {
    }

    public Doctor(String name, String surname, String specialty, Set<Service> services, Set<Appointment> appointments) {
        this.name = name;
        this.surname = surname;
        this.specialty = specialty;
        this.services = services;
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Set<Service> getServices() {
        return services;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public String getFullName() {
        return String.format("%s %s", surname, name);
    }
}
