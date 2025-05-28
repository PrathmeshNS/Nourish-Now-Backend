package com.pscode.nourish_now.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String profileName;
    private String profileType;
    private byte[] profileImageDate;

    @OneToOne
    private Users users;
}
