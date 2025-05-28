package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.dto.UserDto;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController("user")
public class UserController {
        private HttpHeaders headers = new HttpHeaders();

        private UserService service;
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        return getResponseEntity(user, headers, service);
    }

    static ResponseEntity<?> getResponseEntity(@RequestBody Users user, HttpHeaders headers, UserService service) {
        headers.set("Content-Type", "application/json");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<?> responseEntity =  service.loginUser(user);
        UserDto dto = (UserDto) responseEntity.getBody();
        System.out.println(dto);
        assert dto != null;
        headers.add("Authorization", "Bearer "+ dto.getJwtToken() );
        return responseEntity;
    }


    @GetMapping("get-all-hotel")
    public List<Users> getAllHotel() {
        return service.getAllHotel();
    }

    @GetMapping("get-all-ngo")
    public List<Users> getAllNgo() {
        return service.getAllNgo();
    }

}
