package com.strotska.prychodnia.controllers;

import com.strotska.prychodnia.model.Appointment;
import com.strotska.prychodnia.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentsByServiceAndTerm(@RequestParam(name = "serviceId", required = false) Long serviceId,
                                                                             @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
                                                                             @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return new ResponseEntity<>(appointmentService.getAppointmentsForServiceInTerm(serviceId, from.toInstant(), to.toInstant()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#username == authentication.principal")
    public ResponseEntity reserveAppointment(@PathVariable Long id, @RequestBody String username) {
        return appointmentService.reserveAppointment(id, username).map(a -> new ResponseEntity(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/history")
    @PreAuthorize("#username == authentication.principal")
    public ResponseEntity<List<Appointment>> getUserHistory(@RequestParam("username") String username) {
        return new ResponseEntity<>(appointmentService.getUserHistory(username), HttpStatus.OK);
    }

    @GetMapping("/history/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> getUserHistory(@PathVariable Long id) {
        return new ResponseEntity<>(appointmentService.getUserHistoryForPatient(id), HttpStatus.OK);
    }

    @GetMapping("/calendar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> getCalendar(@RequestParam(name = "serviceId", required = false) Long serviceId,
                                                         @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date) {
        if (date != null) {
            return new ResponseEntity<>(appointmentService.getCalendar(serviceId, date.toInstant()), HttpStatus.OK);
        }
        return new ResponseEntity<>(appointmentService.getCalendar(serviceId, null), HttpStatus.OK);
    }

    @GetMapping("/new-week")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> getForNewWeek(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime date,
                                                           @RequestParam("id") Long id) {
        return new ResponseEntity<>(appointmentService.getAppointmentsForDays(date.toInstant(), date.plus(5, ChronoUnit.DAYS).toInstant(), id), HttpStatus.OK);
    }
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {
        return this.appointmentService.saveAppointment(appointment).map(a -> new ResponseEntity<>(a, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
