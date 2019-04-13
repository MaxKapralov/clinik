package com.strotska.prychodnia.controllers;

import com.strotska.prychodnia.model.dto.UserDTO;
import com.strotska.prychodnia.service.NewUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sign-up")
public class NewUserController {

    private final NewUserService newUserService;

    public NewUserController(NewUserService newUserService) {
        this.newUserService = newUserService;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDTO user) {
        return newUserService.saveNewUser(user).map(userDetails -> new ResponseEntity<>("Created", HttpStatus.CREATED))
                .orElse(new ResponseEntity<>("User with pesel: " + user.getPesel() + " already exists", HttpStatus.BAD_REQUEST));
    }
}
