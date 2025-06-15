package com.pscode.nourish_now.service;


import com.pscode.nourish_now.entity.History;
import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import com.pscode.nourish_now.enums.MealBookingStatus;
import com.pscode.nourish_now.repository.TemporaryMealBookingDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class TemporaryMealBookingDetailsService {

    @Autowired
    private TemporaryMealBookingDetailsRepository mealDetailsRepository;

    @Autowired
    private AvailableFoodService availableFoodService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private  UserService userService;

    private List<TemporaryMealBookingDetails> mealDetailsList;

    private Logger logger = LoggerFactory.getLogger(TemporaryMealBookingDetailsService.class);

    public ResponseEntity<?> getAllTemporaryMealDetails() {
        mealDetailsList = mealDetailsRepository.findAll();
        return new ResponseEntity<>(mealDetailsList, HttpStatus.OK);
    }

    public ResponseEntity<?> getHotelBooking(Long hotelId) {
        mealDetailsList = mealDetailsRepository.findAll().stream()
                .filter(available -> available.getFood().getHotelUsers().getId().equals(hotelId))
                .collect(toList());

        if (!mealDetailsList.isEmpty()) {
            return new ResponseEntity<>(mealDetailsList, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Data Found!!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getNgoBooking(Long ngoId) {
        mealDetailsList = mealDetailsRepository.findAll().stream()
                .filter(ngo -> ngo.getNgoUsers().getId().equals(ngoId))
                .collect(toList());
        if (!mealDetailsList.isEmpty()) {
            return new ResponseEntity<>(mealDetailsList, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Record Found!!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> saveTempMeal(TemporaryMealBookingDetails temporaryMealBookingDetails) {
        temporaryMealBookingDetails.setMealBookingStatus(MealBookingStatus.HOLDING);
        System.out.println(temporaryMealBookingDetails);
        temporaryMealBookingDetails.setNgoUsers(userService.findUserById(temporaryMealBookingDetails.getNgoUsers().getId()));
        System.out.println(temporaryMealBookingDetails);
        if (mealDetailsRepository.save(temporaryMealBookingDetails) != null) {
            logger.info("in if Condition!!");
            return new ResponseEntity<>("Data Inserted Successfully!!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> approveBooking(Long tmdId) {
        TemporaryMealBookingDetails details = mealDetailsRepository.findByTmdId(tmdId);
        System.out.println(details);
        if (details != null) {
            History history = new History();
            history.setHotelUsers(details.getFood().getHotelUsers());
            history.setNgoUsers(details.getNgoUsers());
            history.setTypeOfProviding(details.getFood().getTypeOfProviding());
            History sendToUserHistory = historyService.saveHistory(history);
            if (sendToUserHistory != null) {
                System.out.println(history);
                    logger.info("History Record Inserted Successfully!!!");
                if (mealDetailsRepository.deleteByTmdId(tmdId)>0) {
                    logger.info("Temporary Meal record Deleted Successfully!!!");
                    if ((availableFoodService.deleteAvailableFood(details.getFood().getAId())) > 0) {
                        logger.info("Available Food record Deleted Successfully!!!");
                        return new ResponseEntity<>(sendToUserHistory,HttpStatus.FOUND);
                    }
                }
            }
        }
                return new ResponseEntity<>("Something Went Wrong!!!",HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
