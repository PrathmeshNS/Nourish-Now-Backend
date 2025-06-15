package com.pscode.nourish_now.controller;


import com.pscode.nourish_now.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

/*
@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody Users user) {
		user.setRole(UserRole.ADMIN);
		user.setPassword(encoder.encode(user.getPassword()));
		try {
			return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
*/

    @GetMapping("/")
    public String ky() {
        return "Hello, There \n There is Admin naaaa...... \n I know You are Admin..... \n I will Detect you!!!";
    }

    @GetMapping("approve-user/{id}")
    public ResponseEntity<String> checkEmail(@PathVariable("id") Long id) {
        return adminService.approveUser(id);
    }

    @GetMapping("logout")
    public void logoutAdmin() {
        return;
    }

}
