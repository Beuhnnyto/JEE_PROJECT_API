package org.efrei.start.dto;

public class ReviewResponse {
    private String id;
    private String reviewerName;
    private double rating;
    private String comment;
    private String movieId; // The movie ID this review is for

    // Constructors
    public ReviewResponse() {
    }

    public ReviewResponse(String id, String reviewerName, double rating, String comment, String movieId) {
        this.id = id;
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
        this.movieId = movieId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
