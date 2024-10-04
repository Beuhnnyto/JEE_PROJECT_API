package org.efrei.start.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.efrei.start.dto.CreateReview;
import org.efrei.start.dto.ReviewResponse;
import org.efrei.start.models.Review;
import org.efrei.start.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody CreateReview createReview) {
        Review review = reviewService.create(createReview);
        return review != null ? new ResponseEntity<>(convertToResponse(review), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable String id) {
        Review review = reviewService.getById(id);
        return review != null ? new ResponseEntity<>(convertToResponse(review), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        List<Review> reviews = List.of(reviewService.getAll());
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> update(@PathVariable String id, @RequestBody CreateReview createReview) {
        Review updatedReview = reviewService.update(id, createReview);
        return updatedReview != null ? new ResponseEntity<>(convertToResponse(updatedReview), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ReviewResponse convertToResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getReviewerName(),
                review.getRating(),
                review.getComment(),
                "movie/" + review.getMovie().getId()
        );
    }
}
