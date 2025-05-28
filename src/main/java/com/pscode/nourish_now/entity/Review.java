package com.pscode.nourish_now.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    private String description;
    private Double star;

    @ManyToOne
    @JoinColumn(name = "ngoUserId", referencedColumnName = "id")
    private Users ngoUsers;

    @ManyToOne
    @JoinColumn(name = "hotelUserId", referencedColumnName = "id")
    private Users hotelUsers;
}
