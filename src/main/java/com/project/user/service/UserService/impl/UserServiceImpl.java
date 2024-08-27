package com.project.user.service.UserService.impl;

import com.project.user.service.UserService.entities.Hotel;
import com.project.user.service.UserService.entities.Rating;
import com.project.user.service.UserService.entities.User;
import com.project.user.service.UserService.exceptions.ResourceNotFoundException;
import com.project.user.service.UserService.external.service.HotelService;
import com.project.user.service.UserService.repositories.UserRepository;
import com.project.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        Optional<User> existingOptional = userRepository.findByNameAndEmail(user.getName(), user.getEmail());

        if (existingOptional.isPresent()) {
            // If the user exists, set the existing ID (no need to set it again, as it’s already set)
            User existing = existingOptional.get();
            user.setUserId(existing.getUserId());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User With Given Id Is Not Found On Server!! " + userId));

        //fetch rating for this user from Rating Service
        //        http://localhost:8083/ratings/users/2e87248b-72b3-4ed9-978f-085f52ea2984
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info(String.valueOf(ratingsOfUser));

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        for(Rating rating : ratings) {
            //api call to hotel service to get the hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getsinglehotel/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        }

        user.setRatings(ratings);
        return user;
    }


    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
