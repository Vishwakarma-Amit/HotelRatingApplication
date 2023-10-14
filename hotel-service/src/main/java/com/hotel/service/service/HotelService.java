package com.hotel.service.service;

import java.util.List;

import com.hotel.service.entity.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	Hotel getHotelById(String hotelId);
	
	List<Hotel> getAllHotel();
	
	Hotel updateHotel(String hotelId, Hotel hotel);
	
	String deleteHotel(String hotelId);

}
