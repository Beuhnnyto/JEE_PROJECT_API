package org.efrei.start.dto;

import java.util.Set;

import org.efrei.start.global.Category;

public class MovieResponse {
    private String id;
    private String title;
    private int year;
    private Category category;
    private double rating; // Ranking system (1.0 to 10.0)
    private Set<String> actorIds; // Set of Actor IDs
    private String directorId; // Director ID

    // Constructors
    public MovieResponse() {
    }

    public MovieResponse(String id, String title, int year, Category category, double rating, Set<String> actorIds,
            String directorId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.category = category;
        this.rating = rating;
        this.actorIds = actorIds;
        this.directorId = directorId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<String> getActorIds() {
        return actorIds;
    }

    public void setActorIds(Set<String> actorIds) {
        this.actorIds = actorIds;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }
}
