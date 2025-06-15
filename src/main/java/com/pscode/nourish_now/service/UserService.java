package com.pscode.nourish_now.service;

import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.enums.UserRole;
import com.pscode.nourish_now.exception.custome.UserAlreadyExistException;
import com.pscode.nourish_now.repository.UserRepository;
import com.pscode.nourish_now.utility.PreDefineMessage;
import com.pscode.nourish_now.utility.SystemDateTimeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public Users findUserById(Long id) {
        return repository.findByUserId(id);
    }

    // Method is Specially Used to get user From the database
    public Users fetchByUsername(String email) {
        return repository.findByEmail(email);
    }

    public Users saveUser(Users user) throws UserAlreadyExistException {
        user.setDateOfJoining(SystemDateTimeProvider.returnDateTime());
        logger.info("Saving user: ");
        if (fetchByUsername(user.getEmail()) == null) {
            logger.info("User not found while searching!!: " + user);
            Users user1 = repository.save(user);
            return repository.save(user1);
        }
        throw new UserAlreadyExistException(PreDefineMessage.EMAIL_ALREADY_EXIST);
    }

    public ResponseEntity<?> loginUser(Users user) {

        Users authUsers = repository.findByEmail(user.getEmail());

        if (authUsers != null) {
            return new ResponseEntity<>(authUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credential", HttpStatus.UNAUTHORIZED);
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

    public int getNoOfNgo() {
        return getAllNgo().size();
    }

    public int getNoOfHotel() {
        return getAllHotel().size();
    }

    public int getNoOfVerifiedHotel() {
        return getAllHotel().stream().filter(hotel -> hotel.getStatus().equals(ReviewStatus.TRUE)).toList().size();
    }

    public int getNoOfVerifiedNgo() {
        return getAllNgo().stream().filter(ngo -> ngo.getStatus().equals(ReviewStatus.TRUE)).toList().size();
    }

    public int getNoOfUnVerifiedHotel() {
        return getAllHotel().stream().filter(hotel -> hotel.getStatus().equals(ReviewStatus.FALSE)).toList().size();

    }

    public int getNoOfUnVerifiedNgo() {
        return getAllNgo().stream().filter(hotel -> hotel.getStatus().equals(ReviewStatus.FALSE)).toList().size();
    }


}