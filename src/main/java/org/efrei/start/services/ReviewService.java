package org.efrei.start.services;

import org.efrei.start.dto.CreateReview;
import org.efrei.start.models.Movie;
import org.efrei.start.models.Review;
import org.efrei.start.repositories.ReviewRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final MovieService movieService;

    public ReviewService(ReviewRepository repository, @Lazy MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    // Create a review
    public Review create(CreateReview createReview) {
        Movie movie = movieService.getById(createReview.getMovieId());
        if (movie == null) {
            return null; // Movie not found
        }
        Review review = new Review();
        review.setReviewerName(createReview.getReviewerName());
        review.setRating(createReview.getRating());
        review.setComment(createReview.getComment());
        review.setMovie(movie); // Link review to the movie
        return repository.save(review);
    }

    // Get a review by ID
    public Review getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Get all reviews
    public Review[] getAll() {
        return repository.findAll().toArray(Review[]::new);
    }

    // Update an existing review
    public Review update(String id, CreateReview createReview) {
        Review review = repository.findById(id).orElse(null);
        if (review == null) {
            return null; // Review not found
        }

        review.setReviewerName(createReview.getReviewerName());
        review.setRating(createReview.getRating());
        review.setComment(createReview.getComment());

        return repository.save(review);
    }

    // Delete a review by ID
    public void delete(String id) {
        repository.deleteById(id);
    }
}
