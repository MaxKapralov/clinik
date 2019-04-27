package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.UserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
    Optional<UserDetails> findByPesel(String pesel);
    @Query("SELECT userDetails FROM UserDetails userDetails WHERE EXISTS (SELECT identity FROM userDetails.identity identity WHERE EXISTS (SELECT role FROM identity.roles role WHERE role = 'ROLE_USER'))")
    List<UserDetails> findAllPatients();
}