package com.pscode.nourish_now.service;


import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import com.pscode.nourish_now.repository.TemporaryMealBookingDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TemporaryMealBookingDetailsService {

    @Autowired
    private TemporaryMealBookingDetailsRepository  mealDetailsRepository;

    public List<TemporaryMealBookingDetails> getAllTemporaryMealDetails() {
        return  mealDetailsRepository.findAll();
    }

    public List<TemporaryMealBookingDetails> getHotelBooking(Long hotelId) {
        return mealDetailsRepository.findAll().stream()
                .filter(hotel -> hotel.getHotelUsers().getId().equals(hotelId))
                .collect(toList());
    }
    public List<TemporaryMealBookingDetails> getNgoBooking(Long ngoId) {
        return mealDetailsRepository.findAll().stream()
                .filter(ngo -> ngo.getNgoUsers().getId().equals(ngoId))
                .collect(toList());
    }

}
