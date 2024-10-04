package org.efrei.start.services;

import java.util.stream.Collectors;

import org.efrei.start.dto.CreateActor;
import org.efrei.start.models.Actor;
import org.efrei.start.repositories.ActorRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository repository;
    private final MovieService movieService;

    public ActorService(ActorRepository repository, @Lazy MovieService movieService) {
        this.repository = repository;
        this.movieService = movieService;
    }

    // Create an actor
    public Actor create(CreateActor createActor) {
        Actor actor = new Actor();
        actor.setFullName(createActor.getFullName());
        actor.setFirstName(createActor.getFirstName());
        actor.setLastName(createActor.getLastName());
        actor.setAge(createActor.getAge());

        // Map movie IDs to Movie objects using the movieService
        actor.setMovies(createActor.getMovieIds().stream()
                .map(movieService::getById) // Assuming movieService.getById() returns a Movie object
                .collect(Collectors.toSet()));

        return repository.save(actor);
    }

    // Get an actor by ID
    public Actor getById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Get all actors
    public Actor[] getAll() {
        return repository.findAll().toArray(Actor[]::new);
    }

    // Update an existing actor
    public Actor update(String id, CreateActor createActor) {
        Actor actor = repository.findById(id).orElse(null);
        if (actor == null) {
            return null; // Actor not found
        }

        actor.setFullName(createActor.getFullName());
        actor.setFirstName(createActor.getFirstName());
        actor.setLastName(createActor.getLastName());
        actor.setAge(createActor.getAge());

        // Update movie list
        actor.setMovies(createActor.getMovieIds().stream()
                .map(movieService::getById) // Assuming movieService.getById() returns a Movie object
                .collect(Collectors.toSet()));

        return repository.save(actor);
    }

    // Delete an actor by ID
    public void delete(String id) {
        repository.deleteById(id);
    }
}
