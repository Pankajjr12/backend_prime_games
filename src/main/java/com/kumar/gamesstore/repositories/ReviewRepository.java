package com.kumar.gamesstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	   List<Review> findReviewsByUserId(Long userId);
	   List<Review> findReviewsByProductId(Long productId);
}
