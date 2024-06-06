package com.nashtech.rookie.yasa.service;

import com.nashtech.rookie.yasa.entity.Product;
import com.nashtech.rookie.yasa.entity.Rating;
import com.nashtech.rookie.yasa.repository.RatingRepository;
import com.nashtech.rookie.yasa.service.rating.RatingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RatingServiceTest {

    @InjectMocks
    private RatingServiceImpl ratingService;
    @Mock
    private RatingRepository ratingRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public Rating getSampleRating() {
        Rating rating = new Rating();
        rating.setId(1);
        rating.setContent("test");

        Product product = new Product();
        product.setId(2);
        rating.setProduct(product);
    }
}
