package com.user.service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.user.service.entity.Rating;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
	
	@GetMapping("/ratings/users/{userId}")
	List<Rating> getRating(@PathVariable String userId);
	
	@PostMapping("/ratings/")
	Rating createRating(Rating rating);
	
	@PutMapping("/ratings/{ratingId}")
	Rating createRating(@PathVariable String ratingId,Rating rating);
	
	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);

}


