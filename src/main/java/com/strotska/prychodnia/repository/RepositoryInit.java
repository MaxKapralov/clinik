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
        UserDetails details = new UserDetails("name", "surname", "email", "phoneNumber", "flatNumber", "street", "city", "zipCode", "test", identity);
        userDetailsRepository.save(details);

        Identity identity2 = identityRepository.save(new Identity("admin", passwordEncoder.encode("admin"), Utils.setOf(Role.ROLE_ADMIN)));
        UserDetails details2 = new UserDetails("Admin", "Admin", "admin@mail.com", "phoneNumber", "flatNumber", "street", "city", "zipCode", "adminpesel", identity2);
        userDetailsRepository.save(details2);
    }

    private void addServicesAndDoctors() {
        Service s1 = serviceRepository.save(new Service("Service 1"));
        Service s2 = serviceRepository.save(new Service("Service 2"));
        Service s3 = serviceRepository.save(new Service("Service 3"));
        Doctor d1 = doctorRepository.save(new Doctor("First", "Doctor"));
        Doctor d2 = doctorRepository.save(new Doctor("Second", "Doctor"));
        Appointment a1 = new Appointment(userDetailsRepository.findByPesel("test").get(), d1, s1, Instant.now(), false);
        Appointment a2 = new Appointment(null, d2, s3, Instant.now().plus(1, ChronoUnit.HOURS), true);
        Appointment a3 = new Appointment(userDetailsRepository.findByPesel("test").get(), d1, s2, Instant.now().plus(1, ChronoUnit.DAYS), false);
        Appointment a4 = new Appointment(null, d2, s1, Instant.now().plus(2, ChronoUnit.HOURS).plus(1, ChronoUnit.DAYS), true);
        Appointment a5 = new Appointment(null, d1, s1, Instant.now().plus(2, ChronoUnit.HOURS).plus(1, ChronoUnit.DAYS), true);
        appointmentRepository.saveAll(Utils.setOf(a1, a2, a3, a4, a5));
    }
}
