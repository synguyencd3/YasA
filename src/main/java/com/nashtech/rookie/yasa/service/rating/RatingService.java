package com.nashtech.rookie.yasa.service.rating;
import com.nashtech.rookie.yasa.entity.Rating;

import java.util.List;

public interface RatingService {
     Rating createRating(Rating rating);

     List<Rating> getAll();
}
