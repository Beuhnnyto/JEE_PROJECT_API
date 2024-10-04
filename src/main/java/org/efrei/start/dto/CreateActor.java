package org.efrei.start.dto;

import java.util.HashSet;
import java.util.Set;

public class CreateActor {

    private String id;
    private String fullName;
    private String firstName;
    private String lastName;
    private int age;
    private Set<String> movieIds; // Set of Movie IDs in which this actor has acted
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
    public Set<String> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(Set<String> movieIds) {
        this.movieIds = (movieIds != null) ? movieIds : new HashSet<>();
    }
    
    // Getters and Setters
}