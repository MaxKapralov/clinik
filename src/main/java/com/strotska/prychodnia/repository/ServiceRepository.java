package com.strotska.prychodnia.repository;

import com.strotska.prychodnia.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    Optional<Service> findByName(String name);

    @Query("SELECT service FROM Service service WHERE (:id IS NULL OR service.id = :id)")
    Optional<Iterable<Service>> search(@Param("id")Long id);
}
