package com.pscode.nourish_now.dto;

import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.enums.UserRole;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserDto {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String registrationNo;
    private String dateOfJoining;
    private String contactNo;
    private String website;
    private String city;
    private Long pincode;
    private UserRole role;
    private ReviewStatus status;

    private String jwtToken;

}
