package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.entity.Rating;
import com.nashtech.rookie.yasa.service.rating.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
      return ratingService.createRating(rating);
    }

    @GetMapping
    public List<Rating> getAll(@RequestParam(name="user", required = false) Integer userId,
                               @RequestParam(name="product", required = false) Integer productId) {

    if (userId != null && productId == null) //
    if (userId == null && productId != null)//
    if (userId != null && productId != null)
        return ratingService.getAll();
    }


}
