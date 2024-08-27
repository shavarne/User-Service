package com.project.user.service.UserService.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
