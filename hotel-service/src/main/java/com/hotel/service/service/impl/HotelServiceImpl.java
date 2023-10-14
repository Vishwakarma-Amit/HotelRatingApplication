package com.hotel.service.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.entity.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		
		String hotelId = UUID.randomUUID().toString();
		hotel.setHotelId(hotelId);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with hotel Id: "+hotelId));
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel updateHotel(String hotelId, Hotel hotel) {
		Hotel savedHotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with hotel Id: "+hotelId));
		
		savedHotel.setLocation(hotel.getLocation()!=null?hotel.getLocation():savedHotel.getLocation());
		savedHotel.setName(hotel.getName()!=null?hotel.getName():savedHotel.getName());
		savedHotel.setAbout(hotel.getAbout()!=null?hotel.getAbout():savedHotel.getAbout());
		
		return hotelRepository.save(savedHotel);
	}

	@Override
	public String deleteHotel(String hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Hotel not found with hotel Id: "+hotelId));
		
		if(hotel!=null) {
			hotelRepository.deleteById(hotelId);
			return "Hotel with Id "+hotelId+" deleted successfully!!";
		}
		return null;
	}

}
