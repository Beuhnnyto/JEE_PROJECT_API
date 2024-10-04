package org.efrei.start.dto;

import java.util.Set;

public class ActorResponse {
    private String id;
    private String fullName;
    private Set<String> movieIds; // Set of movie IDs as URIs

    // Constructors
    public ActorResponse() {
    }

    public ActorResponse(String id, String fullName, Set<String> movieIds) {
        this.id = id;
        this.fullName = fullName;
        this.movieIds = movieIds;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(Set<String> movieIds) {
        this.movieIds = movieIds;
    }
}
