package com.strotska.prychodnia.service;

import com.strotska.prychodnia.Utils;
import com.strotska.prychodnia.model.Appointment;
import com.strotska.prychodnia.model.dto.AppointmentDTO;
import com.strotska.prychodnia.model.dto.AppointmentVal;
import com.strotska.prychodnia.repository.AppointmentRepository;
import com.strotska.prychodnia.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class AppointmentService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    public AppointmentService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
    }

    public List<AppointmentDTO> getAppointmentsForServiceInTerm(Long serviceId, Instant from, Instant to) {
        List<AppointmentDTO> result = new ArrayList<>();
        doctorRepository.findByServiceId(serviceId).forEach(doctor -> {
            Map<LocalDate, List<AppointmentVal>> appointments = new HashMap<>();

            doctor.getAppointments().forEach(appointment -> {
                if (appointment.getTerm().isAfter(from) && appointment.getTerm().isBefore(to)) {
                    LocalDate term = LocalDateTime.ofInstant(appointment.getTerm(), ZoneOffset.ofHours(2)).toLocalDate();

                    if (appointments.containsKey(term)) {
                        appointments.get(term).add(new AppointmentVal(appointment.getId(), appointment.getTerm(), appointment.isFree()));
                    } else {
                        appointments.put(term, Utils.listOf(new AppointmentVal(appointment.getId(), appointment.getTerm(), appointment.isFree())));
                    }
                }
            });
            if (appointments.size() > 0) {
                result.add(new AppointmentDTO(doctor.getFullName(), appointments));
            }
        });
        return result;
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return this.appointmentRepository.findById(id);
    }

    public Optional<Appointment> saveAppointment(Appointment appointment) {
        return Optional.of(this.appointmentRepository.save(appointment));
    }

    public Optional<Appointment> reserveAppointment(Long id, String username) {
        return userService.getUserByUsername(username).flatMap(user -> getAppointmentById(id).map(appointment -> {
                    appointment.setUserDetails(user);
                    appointment.setFree(false);
                    return saveAppointment(appointment);
                })
        ).orElse(Optional.empty());
    }
}
