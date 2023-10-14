package com.rating.service.service;

import java.util.List;

import com.rating.service.entity.Rating;

public interface RatingService {
	
	Rating saveRating(Rating rating);
	
	List<Rating> getAllRating();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String hotelId);
	
	Rating getRatingById(String ratingId);
	
	Rating updateRating(String ratingId, Rating rating);
	
	String deleteRating(String ratingId);

}
