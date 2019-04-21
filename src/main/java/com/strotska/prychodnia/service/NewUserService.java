package com.strotska.prychodnia.service;

import com.strotska.prychodnia.Utils;
import com.strotska.prychodnia.model.Identity;
import com.strotska.prychodnia.model.UserDetails;
import com.strotska.prychodnia.model.dto.UserDTO;
import com.strotska.prychodnia.repository.IdentityRepository;
import com.strotska.prychodnia.repository.UserDetailsRepository;
import com.strotska.prychodnia.security.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewUserService {

    private final UserDetailsRepository userDetailsRepository;
    private final IdentityRepository identityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public NewUserService(UserDetailsRepository userDetailsRepository, IdentityRepository identityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsRepository = userDetailsRepository;
        this.identityRepository = identityRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    public Optional<UserDetails> saveNewUser(UserDTO userDTO) {
        return userDetailsRepository.findByPesel(userDTO.getPesel()).map(userDetails -> Optional.<UserDetails>empty()).orElseGet(() -> {
            Identity identity = identityRepository.save(convertDTOToIdentity(userDTO));
            return Optional.of(userDetailsRepository.save(convertDTOToUserDetails(userDTO, identity)));
        });
    }

    private Identity convertDTOToIdentity(UserDTO userDTO) {
        return new Identity(userDTO.getPesel(), passwordEncoder.encode(userDTO.getPassword()), Utils.setOf(Role.ROLE_USER));
    }
    private UserDetails convertDTOToUserDetails(UserDTO userDTO, Identity identity) {
        return new UserDetails(userDTO.getName(), userDTO.getSurname(), userDTO.getEmail(),
                userDTO.getPhoneNumber(), userDTO.getFlatNumber(),
                userDTO.getStreet(), userDTO.getCity(), userDTO.getZipCode(), userDTO.getPesel(), identity);
    }
}
