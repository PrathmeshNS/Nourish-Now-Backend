package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.exception.custome.UserAlreadyExistException;
import com.pscode.nourish_now.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ngo")
public class NgoController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/")
    public String getMethodName() {
        return "Hello From NGO!!";
    }


    @PostMapping("register")
    public ResponseEntity<?> registerNgo(@RequestBody Users user) {
        user.setRole(UserRole.NGO);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(ReviewStatus.FALSE);
        try {
            return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
