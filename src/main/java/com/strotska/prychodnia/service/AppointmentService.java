package com.strotska.prychodnia.service;

import com.strotska.prychodnia.model.Appointment;
import com.strotska.prychodnia.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
    }

    public List<Appointment> getAppointmentsForServiceInTerm(Long serviceId, Instant from, Instant to) {
        return this.appointmentRepository.findAppointmentsByServiceIdAndTerm(serviceId, from, to);
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return this.appointmentRepository.findById(id);
    }

    public Optional<Appointment> saveAppointment(Appointment appointment) {
        return Optional.of(this.appointmentRepository.save(appointment));
    }

    public Optional<Appointment> reserveAppointment(Long id, String username) {
        return userService.getUserByUsername(username).flatMap(user -> getAppointmentById(id).map(appointment -> {
                    appointment.setPatient(user);
                    appointment.setFree(false);
                    return saveAppointment(appointment);
                })
        ).orElse(Optional.empty());
    }

    public List<Appointment> getUserHistory(String username) {
        return this.appointmentRepository.findAllForUser(username);
    }

    public List<Appointment> getUserHistoryForPatient(Long id) {
        return this.appointmentRepository.findAllForUserId(id);
    }

    public List<Appointment> getCalendar(Long serviceId, Instant date) {
        if (date != null) {
            return this.appointmentRepository.findCalendarForServiceAndDate(serviceId, date, date.plus(1, ChronoUnit.DAYS));
        }
        return this.appointmentRepository.findCalendarForServiceAndDate(serviceId, null, null);
    }

    public List<Appointment> getAppointmentsForDays(Instant startDate, Instant endDate, Long id) {
        List<Appointment> reserved = appointmentRepository.findAllByTermBetweenAndDoctor_Id(startDate, endDate, id);
        List<Appointment> result = new ArrayList<>();
        for(; startDate.isBefore(endDate); startDate = startDate.plus(1, ChronoUnit.DAYS)) {
            for (int i = 0; i < 16; i++) {
                Instant term = startDate.plus(30 * i, ChronoUnit.MINUTES);
                if (reserved.stream().noneMatch(appointment -> (appointment.getTerm().isAfter(term) && appointment.getTerm().isBefore(term.plus(30, ChronoUnit.MINUTES))) || appointment.getTerm().equals(term))) {
                    result.add(new Appointment(null, null, null, term, true));
                }
            }
        }
        result.sort(Comparator.comparing(Appointment::getTerm));
        return result;
    }
}
