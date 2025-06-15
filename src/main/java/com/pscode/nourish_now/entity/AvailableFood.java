package com.pscode.nourish_now.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;
    private String dateTime;
    private ArrayList<String> foodItem;
    private int approxPersonCanEat;
    private String typeOfProviding;

    @ManyToOne
    @JoinColumn(name = "hotelUserId", referencedColumnName = "id")
    private Users hotelUsers;

}
