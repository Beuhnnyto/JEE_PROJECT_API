package org.efrei.start.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Actor {

    @Id
    private String id;

    private String fullName;

    private String firstName;

    private String lastName;

    private int age;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();

    // Constructors
    public Actor() {
        this.id = UUID.randomUUID().toString(); // Generate UUID
    }

    public Actor(String name) {
        this.id = UUID.randomUUID().toString(); // Generate UUID
        this.fullName = name;
        this.firstName = name.split(" ")[0];
        this.lastName = name.split(" ")[1];
        this.age = 0;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

}
