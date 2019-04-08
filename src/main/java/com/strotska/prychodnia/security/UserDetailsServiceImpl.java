package com.strotska.prychodnia.security;

import com.strotska.prychodnia.repository.IdentityRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private IdentityRepository identityRepository;

    public UserDetailsServiceImpl(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return identityRepository.findByUsername(username).map(user -> new User(user.getUsername(), user.getPassword(), getAuthority(user.getRoles())))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Set<String> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role))

        );
        return authorities;
    }
}
