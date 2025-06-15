package com.pscode.nourish_now.entity;

import com.pscode.nourish_now.enums.MealBookingStatus;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tmdId;
    private MealBookingStatus mealBookingStatus;

    @ManyToOne
    @JoinColumn(name = "aFood", referencedColumnName = "aId")
    private AvailableFood food;

    @ManyToOne
    @JoinColumn(name = "ngoUserId", referencedColumnName = "id")
    private Users ngoUsers;

}
