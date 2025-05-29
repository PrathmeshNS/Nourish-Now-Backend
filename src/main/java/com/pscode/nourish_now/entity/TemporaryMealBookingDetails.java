package com.pscode.nourish_now.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryMealBookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tmdId;
    private String dateTime;
    private String description;
    private int approxPersonCanEat;
    private String typeOfProviding;

    @ManyToOne
    @JoinColumn(name = "ngoUserId", referencedColumnName = "id")
    private Users ngoUsers;

    @ManyToOne
    @JoinColumn(name = "hotelUserId", referencedColumnName = "id")
    private Users hotelUsers;

}
