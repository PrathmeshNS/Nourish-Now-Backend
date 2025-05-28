package com.pscode.nourish_now.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pscode.nourish_now.entity.History;
import com.pscode.nourish_now.service.HistoryService;
import com.pscode.nourish_now.utility.PreDefineMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("history")
public class HistoryController {

	@Autowired
	private HistoryService service;
	
	@GetMapping("find-history/{id}")
	public ResponseEntity<List<History>> findByHistoryId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.findByHistoryId(id), HttpStatus.OK);
	}
	
	@PostMapping("add-history")
	public ResponseEntity<?> saveHistory(@RequestBody History history) {		
		History history2 =  service.saveHistory(history);
		if (history2 !=null) {
			return new ResponseEntity<>(history2,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new UsernameNotFoundException(PreDefineMessage.INVALID_DETAILS),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("get-all-history")
	public ResponseEntity<List<History>> getAllHistory() {
		return ResponseEntity.ok(service.getAllHistory());
	}
	
	@GetMapping("get-hotel-history/{id}")
	public ResponseEntity<?> getHistoryEntityByHotelId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.getHistoryByHotelId(id), HttpStatus.OK);
	}
	
	@GetMapping("get-ngo-history/{id}")
	public ResponseEntity<?> getHistoryByHotelId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.getHistoryByNgoId(id), HttpStatus.OK);
	}
	
}
