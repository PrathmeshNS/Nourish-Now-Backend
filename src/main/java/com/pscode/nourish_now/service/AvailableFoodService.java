package com.pscode.nourish_now.service;

import java.util.List;

import com.pscode.nourish_now.utility.SystemDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pscode.nourish_now.entity.AvailableFood;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.repository.AvailableFoodRepository;

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


}
