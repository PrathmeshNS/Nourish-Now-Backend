package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import com.pscode.nourish_now.service.TemporaryMealBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("temporary-meal-details")
public class TemporaryMealBookingDetailsController {

    @Autowired
    private TemporaryMealBookingDetailsService bookingDetailsService;

    @PostMapping("add-temporary-data")
    public ResponseEntity<?> addTemporaryData(@RequestBody TemporaryMealBookingDetails temporaryMealBookingDetails) {
        return bookingDetailsService.saveTempMeal(temporaryMealBookingDetails);
    }

    @GetMapping("get-all-meal")
    public ResponseEntity<?> getAllMealBookingDetails() {
        return bookingDetailsService.getAllTemporaryMealDetails();
    }

    @GetMapping("get-hotel-booked-meal/{id}")
    public ResponseEntity<?> getHotelBookingDetails(@PathVariable("id") Long id) {
        return bookingDetailsService.getHotelBooking(id);
    }

    @GetMapping("get-ngo-booked-meal/{id}")
    public ResponseEntity<?> getNgoBookingDetails(@PathVariable("id") Long id) {
        return bookingDetailsService.getNgoBooking(id);
    }

    @GetMapping("approve-food/{tmdId}")
    public ResponseEntity<?> approveFood(@PathVariable("tmdId") Long tmdId) {
        return bookingDetailsService.approveBooking(tmdId);
    }


}
