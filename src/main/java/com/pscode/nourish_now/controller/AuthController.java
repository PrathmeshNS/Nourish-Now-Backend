package com.pscode.nourish_now.controller;


import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.service.UserService;
import com.pscode.nourish_now.service.jwt.JwtService;
import com.pscode.nourish_now.utility.PreDefineMessage;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @PermitAll
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            Authentication auth = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            Users authUsers = userService.fetchByUsername(user.getEmail());

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateJwtToken(userDetails.getUsername(), userDetails.getAuthorities().toString());

            if (auth.isAuthenticated()) {
                if (authUsers.getRole().equals(UserRole.ADMIN)) {
                    return ResponseEntity.ok(Map.of("token", token,"user",authUsers));
                } else {
                    if ((authUsers.getRole().equals(UserRole.HOTEL)) || authUsers.getRole().equals(UserRole.NGO)) {
                        if (authUsers.getStatus().equals(ReviewStatus.TRUE)) {
                            return ResponseEntity.ok(Map.of("token", token,"user",authUsers));
                        }
                        return new ResponseEntity<>(PreDefineMessage.DETAILS_ARE_VERIFYING, HttpStatus.UNAUTHORIZED);
                    }
                }
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong: " + e.getMessage());
        }
        return null;
    }
}
