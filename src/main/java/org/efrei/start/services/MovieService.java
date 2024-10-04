package org.efrei.start.services;

import java.util.stream.Collectors;

import org.efrei.start.dto.CreateMovie;
import org.efrei.start.models.Movie;
import org.efrei.start.repositories.MovieRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final ActorService actorService;
    private final DirectorService directorService;

    public MovieService(MovieRepository repository, @Lazy ActorService actorService, DirectorService directorService) {
        this.repository = repository;
        this.actorService = actorService;
        this.directorService = directorService;
    }

    // Create a movie
    public Movie create(CreateMovie createMovie) {
        Movie movie = new Movie();
        movie.setTitle(createMovie.getTitle());
        movie.setYear(createMovie.getYear());
        movie.setCategory(createMovie.getCategory());
        movie.setRating(createMovie.getRating());

        // Map actor IDs to Actor objects
        movie.setActors(createMovie.getActorIds().stream()
                .map(actorService::getById) // Assuming actorService.getById() returns an Actor object
                .collect(Collectors.toSet()));

        // Map director ID to Director object
        movie.setDirector(directorService.getById(createMovie.getDirectorId()));

        return repository.save(movie);
    }

    // Get a movie by ID
    public Movie getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Get all movies
    public Movie[] getAll() {
        return repository.findAll().stream().toArray(Movie[]::new);
    }

    // Update an existing movie
    public Movie update(String id, CreateMovie createMovie) {
        Movie movie = repository.findById(id).orElse(null);
        if (movie == null) {
            return null; // Movie not found
        }

        movie.setTitle(createMovie.getTitle());
        movie.setYear(createMovie.getYear());
        movie.setCategory(createMovie.getCategory());
        movie.setRating(createMovie.getRating());

        // Update actors
        movie.setActors(createMovie.getActorIds().stream()
                .map(actorService::getById)
                .collect(Collectors.toSet()));

        // Update director
        movie.setDirector(directorService.getById(createMovie.getDirectorId()));

        return repository.save(movie);
    }

    // Delete a movie by ID
    public void delete(String id) {
        repository.deleteById(id);
    }
}
