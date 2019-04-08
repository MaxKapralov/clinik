package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.Identity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityRepository extends CrudRepository<Identity, Long> {

    Optional<Identity> findByUsername(String username);

}
