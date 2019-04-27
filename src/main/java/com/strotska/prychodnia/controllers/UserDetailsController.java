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
    @PreAuthorize("#username == authentication.principal || hasRole('ADMIN')")
    public ResponseEntity<List<UserDetails>> getUserByUsername(@RequestParam(name = "username", required = false) String username) {
        if (username != null) {
            return this.userService.getUserByUsername(username).map(u -> new ResponseEntity<>(Utils.listOf(u), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } else {
            return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("#user.pesel == authentication.principal")
    public ResponseEntity<Void> updateDetails(@RequestBody UserDetailsDTO user) {
        return this.userService.updateUserDetails(user).map(userDetails -> new ResponseEntity<Void>(HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
