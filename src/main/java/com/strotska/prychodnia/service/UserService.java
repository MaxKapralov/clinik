package com.strotska.prychodnia.service;

import com.strotska.prychodnia.model.UserDetails;
import com.strotska.prychodnia.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public Optional<UserDetails> getUserByUsername(String username) {
        return userDetailsRepository.findByPesel(username);
    }
}
