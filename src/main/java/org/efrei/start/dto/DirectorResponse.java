package org.efrei.start.dto;

import java.util.Set;

public class DirectorResponse {
    private String id;
    private String name;
    private Set<String> movieIds; // Set of movie IDs as URIs

    // Constructors
    public DirectorResponse() {
    }

    // Updated constructor to accept Set<String>
    public DirectorResponse(String id, String name, Set<String> movieIds) {
        this.id = id;
        this.name = name;
        this.movieIds = movieIds; // Directly assign the passed movie IDs
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
        this.movieIds = movieIds;
    }
}
