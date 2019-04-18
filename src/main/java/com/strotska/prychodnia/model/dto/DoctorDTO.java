package com.strotska.prychodnia.model.dto;

import com.strotska.prychodnia.model.Appointment;

import java.util.Set;

public class DoctorDTO {
    private String name;

    private String surname;

    private Set<Appointment> appointments;

    public DoctorDTO(String name, String surname, Set<Appointment> appointments) {
        this.name = name;
        this.surname = surname;
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }
}
