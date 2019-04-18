package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.Utils;
import com.strotska.prychodnia.model.*;
import com.strotska.prychodnia.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.Set;

@Order(2)
@Component
public class RepositoryInit implements CommandLineRunner {

    @Autowired
    private IdentityRepository identityRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void run(String... args) {
        addIdentities();
        addUsers();
        addServicesAndDoctors();
    }

    private void addIdentities() {
        identityRepository.save(new Identity("user", passwordEncoder.encode("password"), Utils.setOf(Role.ROLE_USER)));
    }
    private void addUsers() {
        Identity identity = identityRepository.save(new Identity("test", passwordEncoder.encode("test"), Utils.setOf(Role.ROLE_USER)));
        UserDetails details = new UserDetails("name", "surname", "email", "phoneNumber", "flatNumber", "streetNumber", "street", "city", "zipCode", "test", identity);
        userDetailsRepository.save(details);
    }

    private void addServicesAndDoctors() {
        Set<Service> serviceSet = Utils.setOf(serviceRepository.save(new Service("Service 1")),
                serviceRepository.save(new Service("Service 2")),
                serviceRepository.save(new Service("Service 3")));
        Set<Appointment> appointments = Utils.setOf(appointmentRepository.save(new Appointment(Instant.now(), true)), appointmentRepository.save(new Appointment(Instant.now().plus(1, ChronoUnit.DAYS), false)));
        Doctor doctor = new Doctor("Maks", "Kap", "Smth", serviceSet, appointments);
        doctorRepository.save(doctor);
        Doctor doctor1 = new Doctor("Ala", "Kow", "Ssfdssh", serviceSet, Utils.setOf());
        doctorRepository.save(doctor1);
    }
}
