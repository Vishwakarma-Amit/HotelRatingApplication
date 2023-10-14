package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.clients.RatingService;
import com.user.service.entity.Rating;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating() {
		Rating rating = Rating.builder().rating(3).hotelId("69e8b1ff-8736-4e7f-b5f9-0ac96d68b07d")
				.userId("cc901b73-b7b9-49d9-800f-3b410c59a939").feedback("Best hotel in Bangalore").build();
		Rating savedRating = ratingService.createRating(rating);
		System.out.println(savedRating);
	}

}
