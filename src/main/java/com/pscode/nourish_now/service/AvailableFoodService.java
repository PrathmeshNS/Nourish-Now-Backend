package com.pscode.nourish_now.service;

import com.pscode.nourish_now.entity.AvailableFood;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.repository.AvailableFoodRepository;
import com.pscode.nourish_now.utility.SystemDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableFoodService {

    @Autowired
    private UserService userService;

    @Autowired
    private AvailableFoodRepository repository;

    private Users getHotelUser(Long id) {
        Users users = userService.findUserById(id);
        if (users != null && users.getRole().equals(UserRole.HOTEL)) {
            return users;
        }
        return null;
    }

    public List<AvailableFood> getAllAvailableFood() {
        return repository.findAll();
    }


    public AvailableFood saveAvailableFood(AvailableFood food) {
        Users hotelUser = getHotelUser(food.getHotelUsers().getId());
        food.setDateTime(SystemDateTimeProvider.returnDateTime());
        assert hotelUser != null;
        if (!hotelUser.getEmail().isEmpty()) {
            return repository.save(food);
        }
        return null;
    }

    public long deleteAvailableFood(Long id) {
        return repository.deleteByFoodId(id);
    }

    public ResponseEntity<?> getFoodById(int aId) {
        return new ResponseEntity<>(repository.findfoodById(aId), HttpStatus.OK);
    }
}
