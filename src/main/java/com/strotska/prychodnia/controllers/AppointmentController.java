package com.strotska.prychodnia.controllers;

import com.strotska.prychodnia.model.dto.AppointmentDTO;
import com.strotska.prychodnia.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByServiceAndTerm(@RequestParam("serviceId") Long serviceId,
                                                                                @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime from,
                                                                                @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime to) {
        return new ResponseEntity<>(appointmentService.getAppointmentsForServiceInTerm(serviceId, from.toInstant(), to.toInstant()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity reserveAppointment(@PathVariable Long id, @RequestBody String username) {
        return appointmentService.reserveAppointment(id, username).map(a -> new ResponseEntity(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
