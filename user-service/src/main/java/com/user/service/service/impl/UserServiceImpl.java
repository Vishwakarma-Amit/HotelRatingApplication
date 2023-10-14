package com.user.service.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.service.clients.HotelService;
import com.user.service.clients.RatingService;
import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exception.ResourceNotFoundException;
import com.user.service.repositiry.UserRepository;
import com.user.service.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private RatingService ratingService;

	@Override
	public User saveUser(User user) {
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		return userRepository.save(user);
	}
	


	@Override
	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		for(User user: users) {
			List<Rating> ratings = ratingService.getRating(user.getUserId());
			ratings.stream().map(rating-> {

				Hotel hotel = hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
				return rating;
			}).collect(Collectors.toList());
			user.setRatings(ratings);
		}
		return users;
		
	}

	@Override
	public User GetUserById(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+userId));
		
		List<Rating> ratings = ratingService.getRating(user.getUserId());
		
		
		ratings.stream().map(rating-> {

			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratings);
		return user;
	}

	@Override
	public User updateUser(String userId, User user) {
		User savedUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+userId));
		
		savedUser.setName(user.getName()!=null?user.getName():savedUser.getName());
		savedUser.setEmail(user.getEmail()!=null?user.getEmail():savedUser.getEmail());
		savedUser.setAbout(user.getAbout()!=null?user.getAbout():savedUser.getAbout());
		
		return userRepository.save(savedUser);
	}

	@Override
	public String deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+userId));
		
		if(user!=null) {
			userRepository.deleteById(userId);
			return "User with userId "+userId+" deleted successfully";
		}
		return null;
	}

}
