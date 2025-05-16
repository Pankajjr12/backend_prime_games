package com.kumar.gamesstore.serviceImpl;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.ReviewNotFoundException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.Review;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.ReviewRepository;
import com.kumar.gamesstore.requests.CreateReviewRequest;
import com.kumar.gamesstore.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {

        Review newReview = new Review();

        newReview.setReviewText(req.getReviewText());
        newReview.setRating(req.getReviewRating());
        newReview.setProductImages(req.getProductImages());
        newReview.setUser(user);
        newReview.setProduct(product);

        product.getReviews().add(newReview);

        return reviewRepository.save(newReview);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {

        return reviewRepository.findReviewsByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId)
            throws ReviewNotFoundException, AuthenticationException {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found"));

        if (review.getUser().getId() != userId) {
            throw new AuthenticationException("You do not have permission to delete this review");
        }

        review.setReviewText(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException {
        // TODO Auto-generated method stub

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not found"));
        if (review.getUser().getId() != userId) {
            throw new AuthenticationException("You do not have permission to delete this review");
        }
        reviewRepository.delete(review);
    }

}
