package com.pscode.nourish_now.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aId;
	private String dateTime;
	private String description;
	private int approxPersonCanEat;
	private String typeOfProviding;

	@ManyToOne
	@JoinColumn(name = "hotelUserId", referencedColumnName = "id")
	private Users hotelUsers;

}
