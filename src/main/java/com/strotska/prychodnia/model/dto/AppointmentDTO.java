package com.strotska.prychodnia.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AppointmentDTO {
    private String doctorName;
    private Map<LocalDate, List<AppointmentVal>> appointments;

    public AppointmentDTO(String doctorName, Map<LocalDate, List<AppointmentVal>> appointments) {
        this.doctorName = doctorName;
        this.appointments = appointments;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Map<LocalDate, List<AppointmentVal>> getAppointments() {
        return appointments;
    }
}
