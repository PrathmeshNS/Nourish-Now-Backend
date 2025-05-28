package com.pscode.nourish_now.service;

import com.pscode.nourish_now.dto.UserDto;
import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.exception.custome.UserAlreadyExistException;
import com.pscode.nourish_now.repository.UserRepository;
import com.pscode.nourish_now.service.jwt.JwtService;
import com.pscode.nourish_now.utility.PreDefineMessage;
import com.pscode.nourish_now.utility.SystemDateTimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	@Lazy
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	public Users findUserById(Long id) {
		return repository.findByUserId(id);
	}

	// Method is Specially Used to get user From the database
	public Users fetchByUsername(String email) {
		return repository.findByEmail(email);
	}

	public Users saveUser(Users user) throws UserAlreadyExistException {
		user.setDateOfJoining(SystemDateTimeProvider.returnDateTime());
		
		if (fetchByUsername(user.getEmail())==null) {
			return repository.save(user);			
		}
		throw new UserAlreadyExistException(PreDefineMessage.EMAIL_ALREADY_EXIST);
	}

	public ResponseEntity<?> loginUser(Users user) {

		Users authUsers = repository.findByEmail(user.getEmail());
		UserDto userDto = authUsers.getUserDto();

		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			if (authUsers.getRole().equals(UserRole.ADMIN)) {
				userDto.setJwtToken(jwtService.generateJwtToken(user.getEmail(), authUsers.getRole().toString()));
				String data ="";
				return new ResponseEntity<>(userDto, HttpStatus.OK);
			} else {
				if ((authUsers.getRole().equals(UserRole.HOTEL)) || authUsers.getRole().equals(UserRole.NGO)) {
					if (authUsers.getStatus().equals(ReviewStatus.TRUE)) {
						userDto.setJwtToken(
								jwtService.generateJwtToken(user.getEmail(), authUsers.getRole().toString()));
						return new ResponseEntity<>(userDto, HttpStatus.OK);
					}
					return new ResponseEntity<>(PreDefineMessage.DETAILS_ARE_VERIFYING,
							HttpStatus.UNAUTHORIZED);
				}
			}
		}
		return new ResponseEntity<>(PreDefineMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
	}


	public List<Users> getAllHotel() {
		return repository.findAll().stream()
				.filter(hotel -> hotel.getRole().equals(UserRole.HOTEL))
				.collect(toList());
	}

	public List<Users> getAllNgo() {
		return repository.findAll().stream()
				.filter(ngo -> ngo.getRole().equals(UserRole.NGO))
				.collect(toList());
	}
}