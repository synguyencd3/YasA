package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import com.nashtech.rookie.yasa.entity.Rating;
import com.nashtech.rookie.yasa.service.rating.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public RatingDto createRating(@RequestBody @Valid CreateRatingDto dto) {
      return ratingService.createRating(dto);
    }

    @GetMapping
    @CrossOrigin
    public Page<RatingDto> getAll(@RequestParam(name="user", required = false) Integer userId,
                                  @RequestParam(name="product", required = false) Integer productId,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size
    ) {
    if (userId != null && productId == null) return ratingService.getByUser(userId,page,size);
    if (userId == null && productId != null) return ratingService.getByProduct(productId,page,size);
    if (userId != null && productId != null) return ratingService.getByUserAndProduct(userId,productId,page, size);
        return ratingService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public RatingDto getRating(@PathVariable(name="id") int ratingId){
       return ratingService.getRating(ratingId);
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public RatingDto updateRating(@PathVariable(name="id") int ratingId,@RequestBody UpdateRatingDto rating) {
        return ratingService.updateRating(ratingId, rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable(name="id")int id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

}
