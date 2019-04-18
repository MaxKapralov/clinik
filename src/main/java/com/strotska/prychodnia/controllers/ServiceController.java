package com.strotska.prychodnia.controllers;

import com.strotska.prychodnia.model.Service;
import com.strotska.prychodnia.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Service>> getServices(@RequestParam(name = "id", required = false) Long id) {
        return serviceService.getServices(id).map(services -> new ResponseEntity<>(services, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<String>  addService(@RequestBody Service service) {
        return serviceService.saveService(service).map(s -> new ResponseEntity<>("Created", HttpStatus.CREATED))
                .orElse(new ResponseEntity<>("Service with name: " + service.getName() + " already exists", HttpStatus.BAD_REQUEST));
    }
}
