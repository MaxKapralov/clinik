package com.strotska.prychodnia.service;

import com.strotska.prychodnia.model.UserDetails;
import com.strotska.prychodnia.model.dto.UserDetailsDTO;
import com.strotska.prychodnia.repository.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<UserDetails> updateUserDetails(UserDetailsDTO userDetails) {
        return this.userDetailsRepository.findById(userDetails.getId()).map(user -> {
            user.setName(userDetails.getName());
            user.setSurname(userDetails.getSurname());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setStreet(userDetails.getStreet());
            user.setFlatNumber(userDetails.getFlatNumber());
            user.setCity(userDetails.getCity());
            user.setZipCode(userDetails.getZipCode());
            user.setEmail(userDetails.getEmail());
            return this.userDetailsRepository.save(user);
        });
    }

    public List<UserDetails> getAllUsers() {
        return userDetailsRepository.findAllPatients();
    }
}
