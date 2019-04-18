package com.strotska.prychodnia.service;

import com.strotska.prychodnia.model.Service;
import com.strotska.prychodnia.repository.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Optional<Service> saveService(Service service) {
        return serviceRepository.findByName(service.getName()).map(s -> Optional.<Service>empty())
                .orElse(Optional.of(this.serviceRepository.save(service)));
    }

    public Optional<Iterable<Service>> getServices(Long id) {
        return this.serviceRepository.search(id);
    }
}
