package com.strotska.prychodnia.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.Instant;

@Entity
public class Appointment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "userDetails_id", referencedColumnName = "id")
    private UserDetails patient;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    private Instant term;

    private boolean free;

    public Appointment() {
    }

    public Appointment(UserDetails patient, Doctor doctor, Service service, Instant term, boolean free) {
        this.patient = patient;
        this.doctor = doctor;
        this.service = service;
        this.term = term;
        this.free = free;
    }

    public UserDetails getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Service getService() {
        return service;
    }

    public Instant getTerm() {
        return term;
    }

    public boolean isFree() {
        return free;
    }

    public void setPatient(UserDetails patient) {
        this.patient = patient;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", service=" + service +
                ", term=" + term +
                ", free=" + free +
                '}';
    }
}
