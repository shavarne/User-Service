package com.project.user.service.UserService;

import com.project.user.service.UserService.entities.Rating;
import com.project.user.service.UserService.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
@Service
class UserServiceApplicationTests {



	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client").build();
//
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("savedRating.toString() = " + savedRating.toString());
//
//	}
}
