package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    @Query("SELECT appointment FROM Appointment appointment WHERE EXISTS(SELECT service FROM appointment.service service WHERE service.id = :id)" +
            " AND (appointment.term >= :from AND appointment.term <= :to)")
    List<Appointment>findAppointmentsByServiceIdAndTerm(@Param("id") Long id, @Param("from")Instant from, @Param("to")Instant to);

    @Query("SELECT appointment FROM Appointment appointment WHERE EXISTS(SELECT user FROM appointment.patient user WHERE user.pesel = :pesel)")
    List<Appointment> findAllForUser(@Param("pesel") String pesel);
}
