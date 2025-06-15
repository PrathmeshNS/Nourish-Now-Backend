package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.AvailableFood;
import com.pscode.nourish_now.service.AvailableFoodService;
import com.pscode.nourish_now.utility.PreDefineMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("available-food")
public class AvailableFoodController {

    @Autowired
    private AvailableFoodService availableFoodService;

    @GetMapping("get-all-available-food")
    public ResponseEntity<?> getAllAvailableFood() {
        return new ResponseEntity<>(availableFoodService.getAllAvailableFood(), HttpStatus.OK);
    }

    @PostMapping("add-available-food")
    public ResponseEntity<?> addFood(@RequestBody AvailableFood availableFood) {
        if (availableFoodService.saveAvailableFood(availableFood) != null)
            return new ResponseEntity<>(PreDefineMessage.DATA_INSERTED_SUCCESSFULLY, HttpStatus.CREATED);
        return new ResponseEntity<>(PreDefineMessage.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("food-by-id/{aId}")
    public ResponseEntity<?> getFoodById(@PathVariable("aId") int aId) {
        return availableFoodService.getFoodById(aId);
    }


}
