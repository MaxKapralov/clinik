package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.Utils;
import com.strotska.prychodnia.model.Identity;
import com.strotska.prychodnia.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class RepositoryInit implements CommandLineRunner {

    @Autowired
    private IdentityRepository identityRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void run(String... args) {
        addIdentities();
    }

    private void addIdentities() {
        identityRepository.save(new Identity("user", passwordEncoder.encode("password"), Utils.setOf(Role.ROLE_USER)));
    }
}
