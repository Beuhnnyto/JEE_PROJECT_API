package org.efrei.start.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.efrei.start.global.Category;

import jakarta.persistence.*;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String title;
	private int year;

	@Enumerated(EnumType.STRING)
	private Category category;

	private double rating; // Ranking system as a rating (1.0 to 10.0)

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "actor_id"))
	private Set<Actor> actors = new HashSet<>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Review> reviews = new HashSet<>();

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "director_id")
	private Director director;

	// Constructors
	public Movie() {
	}

	public Movie(String title, int year, Category category, double rating, Director director) {
		this.id = UUID.randomUUID().toString(); // Generate UUID
		this.title = title;
		this.year = year;
		this.category = category;
		this.rating = rating;
		this.director = director;
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

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}
}
