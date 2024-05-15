package com.nashtech.rookie.yasa.service.rating;
import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;

import java.util.List;

public interface RatingService {
     RatingDto createRating(CreateRatingDto dto);

     List<RatingDto> getAll();

     List<RatingDto> getByProduct(int id);

     List<RatingDto> getByUser(int id);
     List<RatingDto> getByUserAndProduct(int userId, int productId);

     RatingDto getRating(int id);

     RatingDto updateRating(int id, UpdateRatingDto dto);

     void deleteRating(int id);
}
