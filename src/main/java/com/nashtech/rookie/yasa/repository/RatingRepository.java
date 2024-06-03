package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    Page<Rating> findByProductId(int id, Pageable pageable);
    Page<Rating> findByUserId(int id, Pageable pageable);
    Page<Rating> findByUserIdAndProductId(int userId, int productId, Pageable pageable);
}
