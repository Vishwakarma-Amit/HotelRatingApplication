package com.hotel.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.service.entity.Hotel;
import com.hotel.service.service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	
	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/")
	public ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
		return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		return new ResponseEntity<List<Hotel>>(hotelService.getAllHotel(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
		return new ResponseEntity<>(hotelService.getHotelById(hotelId), HttpStatus.OK);
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId,@RequestBody Hotel hotel){
		return new ResponseEntity<>(hotelService.updateHotel(hotelId, hotel), HttpStatus.OK);
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<String> deleteHotelById(@PathVariable String hotelId){
		return new ResponseEntity<>(hotelService.deleteHotel(hotelId), HttpStatus.OK);
	}
	

}
