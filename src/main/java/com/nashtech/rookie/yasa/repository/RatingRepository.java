package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByProductId(int id);
    List<Rating> findByUserId(int id);
    List<Rating> findByUserIdAndProductId(int userId, int productId);
}
