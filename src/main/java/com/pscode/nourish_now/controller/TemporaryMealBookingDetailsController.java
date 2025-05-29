package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import com.pscode.nourish_now.service.TemporaryMealBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("temporary-meal-details")
public class TemporaryMealBookingDetailsController {

    @Autowired
    private TemporaryMealBookingDetailsService bookingDetailsService;

    @GetMapping("get-all-meal")
    public List<TemporaryMealBookingDetails> getAllMealBookingDetails() {
        return bookingDetailsService.getAllTemporaryMealDetails();
    }

    @GetMapping("get-hotel-booked-meal/{id}")
    public List<TemporaryMealBookingDetails> getHotelBookingDetails(@PathVariable("id") Long hotelId) {
        return bookingDetailsService.getHotelBooking(hotelId);
    }

    @GetMapping("get-ngo-booked-meal/{id}")
    public List<TemporaryMealBookingDetails> getNgoBookingDetails(@PathVariable("id") Long ngoId) {
        return bookingDetailsService.getNgoBooking(ngoId);
    }
}
