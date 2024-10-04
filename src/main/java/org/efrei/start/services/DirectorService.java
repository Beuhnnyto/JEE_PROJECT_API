package org.efrei.start.services;

import java.util.stream.Collectors;

import org.efrei.start.dto.CreateDirector;
import org.efrei.start.models.Director;
import org.efrei.start.repositories.DirectorRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {

    private final DirectorRepository repository;
    private final MovieService movieService;

    public DirectorService(DirectorRepository repository, @Lazy MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    // Create a director
    public Director create(CreateDirector createDirector) {
        Director director = new Director();
        director.setName(createDirector.getName());
        director.setMovies(createDirector.getMovieIds().stream()
                .map(movieService::getById) // Assuming movieService.getById() returns a Movie object
                .collect(Collectors.toSet()));
        return repository.save(director);
    }

    // Get a director by ID
    public Director getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Get all directors
    public Director[] getAll() {
        return repository.findAll().toArray(Director[]::new);
    }

    // Update an existing director
    public Director update(String id, CreateDirector createDirector) {
        Director director = repository.findById(id).orElse(null);
        if (director == null) {
            return null; // Director not found
        }

        director.setName(createDirector.getName());
        director.setMovies(createDirector.getMovieIds().stream()
                .map(movieService::getById)
                .collect(Collectors.toSet()));

        return repository.save(director);
    }

    // Delete a director by ID
    public void delete(String id) {
        repository.deleteById(id);
    }
}
