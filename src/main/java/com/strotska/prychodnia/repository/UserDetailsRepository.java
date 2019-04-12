package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
}
