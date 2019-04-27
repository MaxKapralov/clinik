package com.strotska.prychodnia.service;

import com.strotska.prychodnia.model.Doctor;
import com.strotska.prychodnia.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Doctor> findAllDoctors() {
        return this.doctorRepository.findAll();
    }

    public Optional<Doctor> saveDoctor(Doctor doctor) {
        return Optional.of(this.doctorRepository.save(doctor));
    }
    public void deleteDoctor(Long id) {
        this.doctorRepository.deleteById(id);
    }
}
