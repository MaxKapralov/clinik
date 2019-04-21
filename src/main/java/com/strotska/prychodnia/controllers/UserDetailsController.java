package com.strotska.prychodnia.controllers;

import com.strotska.prychodnia.Utils;
import com.strotska.prychodnia.model.UserDetails;
import com.strotska.prychodnia.model.dto.UserDetailsDTO;
import com.strotska.prychodnia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-details")
public class UserDetailsController {

    private final UserService userService;

    public UserDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("#username == authentication.principal")
    public ResponseEntity<List<UserDetails>> getUserByUsername(@RequestParam("username") String username) {
        return this.userService.getUserByUsername(username).map(u -> new ResponseEntity<>(Utils.listOf(u), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PutMapping("/{id}")
    @PreAuthorize("#user.pesel == authentication.principal")
    public ResponseEntity<Void> updateDetails(@RequestBody UserDetailsDTO user) {
        return this.userService.updateUserDetails(user).map(userDetails -> new ResponseEntity<Void>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
