package org.efrei.start.dto;

import java.util.Set;
import java.util.stream.Collectors;
import org.efrei.start.models.Movie;

public class DirectorResponse {
    private String id;
    private String name;
    private Set<String> movieIds; // Set of movie IDs as URIs

    // Constructors
    public DirectorResponse() {
    }

    public DirectorResponse(String id, String name, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movieIds = movies.stream().map(Movie::getId).collect(Collectors.toSet());
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
