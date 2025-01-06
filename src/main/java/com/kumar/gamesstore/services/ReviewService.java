package com.kumar.gamesstore.services;

import java.util.List;

import javax.naming.AuthenticationException;
import com.kumar.gamesstore.exceptions.ReviewNotFoundException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.Review;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.requests.CreateReviewRequest;

public interface ReviewService {

	
	   Review createReview(CreateReviewRequest req,
               User user,
               Product product);


	   List<Review> getReviewsByProductId(Long productId);


	   Review updateReview(Long reviewId,
               String reviewText,
               double rating,
               Long userId) throws ReviewNotFoundException, AuthenticationException;



	   void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;
}
