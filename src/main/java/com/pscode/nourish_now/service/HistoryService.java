package com.pscode.nourish_now.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pscode.nourish_now.entity.History;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.repository.HistoryRepository;
import com.pscode.nourish_now.utility.SystemDateTimeProvider;

@Service
public class HistoryService {

	@Autowired
	private HistoryRepository repository;

	@Autowired
	private UserService userService;

	public List<History> findByHistoryId(Long hId) {
		return repository.findByHId(hId);
	}

	private Users findUserById(Long id) {
		return userService.findUserById(id);
	}

	public History saveHistory(History history) {
		String dataTime = SystemDateTimeProvider.returnDateTime();
		history.setDate(dataTime.substring(0, 10));
		history.setTime(dataTime.substring(11));
		Users hotelUsers = findUserById(history.getHotelUsers().getId());
		Users ngoUsers = findUserById(history.getNgoUsers().getId());
		if ((hotelUsers.getRole().equals(UserRole.HOTEL)) &&
				(ngoUsers.getRole().equals(UserRole.NGO))) {
			return repository.save(history);
		}
		return null;
	}

	public List<History> getAllHistory() {
		return repository.findAll();
	}
	
	public List<History> getHistoryByHotelId(Long id) {
		System.out.println(id);
		return repository.findAll().stream()
				.filter(history -> history.getHotelUsers().getId().equals(id))
				.collect(Collectors.toList());
	}

	public List<History> getHistoryByNgoId(Long id) {
		System.out.println(id);
		return repository.findAll().stream()
				.filter(history -> history.getNgoUsers().getId().equals(id))
				.collect(Collectors.toList());
	}
}
