package org.efrei.start.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Director {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();

    // Constructors
    public Director() {
        this.id = UUID.randomUUID().toString(); // Generate UUID
    }

    public Director(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    // Getters and Setters 
    
}
