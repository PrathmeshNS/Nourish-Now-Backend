package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.dto.UserDto;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        return service.loginUser(user);
    }

    @GetMapping("get-by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>( service.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("get-all-hotel")
    public List<Users> getAllHotel() {
        return service.getAllHotel();
    }

    @GetMapping("get-all-ngo")
    public List<Users> getAllNgo() {
        return service.getAllNgo();
    }


    @GetMapping("/no-of-ngo")
    public int getNoOfNgo() {
        return service.getNoOfNgo();
    }

    @GetMapping("/no-of-hotel")
    public int getNoOfHotel() {
        return service.getNoOfHotel();
    }

    @GetMapping("/no-of-active-hotel")
    public int getNoOfVerifiedHotel() {
        return service.getNoOfVerifiedHotel();
    }

    @GetMapping("/no-of-active-ngo")
    public int getNoOfVerifiedNgo() {
        return service.getNoOfVerifiedNgo();
    }

    @GetMapping("/no-of-unactive-hotel")
    public int getNoOfUnVerifiedHotel() {
        return service.getNoOfUnVerifiedHotel();
    }

    @GetMapping("/no-of-unactive-ngo")
    public int getNoOfUnActiveNgo() {
        return service.getNoOfUnVerifiedNgo();
    }

}
