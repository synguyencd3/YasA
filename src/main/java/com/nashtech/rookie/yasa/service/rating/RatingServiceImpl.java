package com.nashtech.rookie.yasa.service.rating;

import com.nashtech.rookie.yasa.dto.request.CreateRatingDto;
import com.nashtech.rookie.yasa.dto.request.UpdateRatingDto;
import com.nashtech.rookie.yasa.dto.response.RatingDto;
import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.entity.Rating;
import com.nashtech.rookie.yasa.exceptions.NotFoundException;
import com.nashtech.rookie.yasa.mapper.RatingMapper;
import com.nashtech.rookie.yasa.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public RatingDto createRating(CreateRatingDto dto) {
        Rating rating = RatingMapper.INSTANCE.toEntity(dto);
        rating = ratingRepository.save(rating);
        return RatingMapper.INSTANCE.toDto(rating);
    }

    @Override
    public Page<RatingDto> getAll( int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return ratingRepository.findAll(pageable).map(RatingMapper.INSTANCE::toDto);
    }

    @Override
    public Page<RatingDto> getByProduct(int productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return ratingRepository.findByProductId(productId,pageable).map(RatingMapper.INSTANCE::toDto);
    }

    @Override
    public Page<RatingDto> getByUser(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return ratingRepository.findByUserId(userId,pageable).map(RatingMapper.INSTANCE::toDto);
    }

    @Override
    public Page<RatingDto> getByUserAndProduct(int userId, int productId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return ratingRepository.findByUserIdAndProductId(userId,productId,pageable).map(RatingMapper.INSTANCE::toDto);
    }

    @Override
    public RatingDto getRating(int ratingId) {
        return ratingRepository.findById(ratingId).map(RatingMapper.INSTANCE::toDto).orElseThrow(() -> new NotFoundException("Rating not found"));
    }

    @Override
    public RatingDto updateRating(int id, UpdateRatingDto dto) {
        return ratingRepository.findById(id).map(rating -> {
            var updatedRating = RatingMapper.INSTANCE.updateEntity(rating,dto);
            updatedRating = ratingRepository.save(updatedRating);
            return RatingMapper.INSTANCE.toDto(updatedRating);
        }).orElseThrow(() -> new NotFoundException("Product or rating not found"));
    }

    @Override
    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }

}
