package com.project.user.service.UserService.external.service;

import com.project.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //POST
    @PostMapping("/ratings")
    Rating createRating(Rating rating);
    //when you dont have any user defined data types then
    //Rating createRating(Map<String,Objects> values)


    //PUT
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);


    @DeleteMapping("/delete/{ratingId}")
    Rating deleteRating(@PathVariable String ratingId);
}
