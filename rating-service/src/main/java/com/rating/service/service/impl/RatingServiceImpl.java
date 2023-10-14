package com.rating.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.service.entity.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import com.rating.service.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public Rating getRatingById(String ratingId) {
		return ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating with Id "+ratingId+ " not found!!"));
	}

	@Override
	public Rating updateRating(String ratingId, Rating rating) {
		
		Rating savedRating = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating with Id "+ratingId+ " not found!!"));
		
		savedRating.setHotelId(rating.getHotelId()!=null?rating.getHotelId(): savedRating.getHotelId());
		savedRating.setUserId(rating.getUserId()!=null?rating.getUserId(): savedRating.getUserId());
		savedRating.setRating(rating.getRating()>0?rating.getRating(): savedRating.getRating());
		savedRating.setFeedback(rating.getFeedback()!=null?rating.getFeedback(): savedRating.getFeedback());
		
		return ratingRepository.save(savedRating);
	}

	@Override
	public String deleteRating(String ratingId) {
		Rating rating = ratingRepository.findById(ratingId).orElseThrow(()-> new ResourceNotFoundException("Rating with Id "+ratingId+ " not found!!"));
		if(rating!=null) {
			ratingRepository.deleteById(ratingId);
			return "Rating with Id "+ratingId+ " deleted successfully!!"; 
		}
		return null;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByHotelId(hotelId);
	}

}
