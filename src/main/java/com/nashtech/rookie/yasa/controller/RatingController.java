package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import com.nashtech.rookie.yasa.entity.Rating;
import com.nashtech.rookie.yasa.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public RatingDto createRating(@RequestBody CreateRatingDto dto) {
      return ratingService.createRating(dto);
    }

    @GetMapping
    public List<RatingDto> getAll(@RequestParam(name="user", required = false) Integer userId,
                               @RequestParam(name="product", required = false) Integer productId) {
    if (userId != null && productId == null) return ratingService.getByUser(userId);
    if (userId == null && productId != null) return ratingService.getByProduct(productId);
    if (userId != null && productId != null) return ratingService.getByUserAndProduct(userId,productId);
        return ratingService.getAll();
    }

    @GetMapping("/{id}")
    public RatingDto getRating(@PathVariable(name="id") int ratingId){
       return ratingService.getRating(ratingId);
    }

    @PutMapping("/{id}")
    public RatingDto updateRating(@PathVariable(name="id") int ratingId, UpdateRatingDto rating) {
        return ratingService.updateRating(ratingId, rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable(name="id")int id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

}
