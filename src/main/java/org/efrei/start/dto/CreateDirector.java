package org.efrei.start.dto;

import java.util.HashSet;
import java.util.Set;

public class CreateDirector {

    private String id;
    private String name;
    private Set<String> movieIds; // Set of Movie IDs directed by this director

    // Default Constructor
    public CreateDirector() {
        this.movieIds = new HashSet<>(); // Initialize to avoid null
    }

    // Parameterized Constructor
    public CreateDirector(String id, String name, Set<String> movieIds) {
        this.id = id;
        this.name = name;
        // Initialize movieIds, ensuring it's never null
        this.movieIds = (movieIds != null) ? movieIds : new HashSet<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(Set<String> movieIds) {
        // Avoid null assignment
        this.movieIds = (movieIds != null) ? movieIds : new HashSet<>();
    }

    // Method to add a movie ID with the proper format
    public void addMovieId(String movieId) {
        // Format the movieId and add to the set
        if (movieId != null && !movieId.trim().isEmpty()) {
            this.movieIds.add("/movies/" + movieId.trim());
        }
    }
}
