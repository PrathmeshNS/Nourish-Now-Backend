package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.UserProfile;
import com.pscode.nourish_now.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("Profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("save-profile")
    public ResponseEntity<String> saveProfile(@RequestBody MultipartFile file) throws IOException {
        UserProfile userProfile = profileService.saveProfile(file);
        if (userProfile != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile saved successfully!!");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Profile could not be saved!!");
    }


}
