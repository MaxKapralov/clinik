package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("SELECT doctor FROM Doctor doctor WHERE EXISTS(SELECT service FROM doctor.services service WHERE service.id = :id)")
    Iterable<Doctor> findByServiceId(@Param("id") Long id);
}
