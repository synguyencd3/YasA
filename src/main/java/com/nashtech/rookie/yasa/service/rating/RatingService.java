package com.nashtech.rookie.yasa.service.rating;
import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RatingService {
     RatingDto createRating(CreateRatingDto dto);

     Page<RatingDto> getAll(int page, int size);

     Page<RatingDto> getByProduct(int id, int page, int size);

     Page<RatingDto> getByUser(int id, int page, int size);
     Page<RatingDto> getByUserAndProduct(int userId, int productId, int page, int size);

     RatingDto getRating(int id);

     RatingDto updateRating(int id, UpdateRatingDto dto);

     void deleteRating(int id);
}
