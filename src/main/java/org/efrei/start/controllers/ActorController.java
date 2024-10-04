package org.efrei.start.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.efrei.start.dto.ActorResponse;
import org.efrei.start.dto.CreateActor;
import org.efrei.start.models.Actor;
import org.efrei.start.services.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ActorResponse> create(@RequestBody CreateActor createActor) {
        // Handle null movieIds
        if (createActor.getMovieIds() == null) {
            createActor.setMovieIds(new HashSet<>()); // Set to empty if null
        }

        Actor createdActor = actorService.create(createActor);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponse(createdActor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorResponse> getById(@PathVariable String id) {
        Actor actor = actorService.getById(id);
        return actor != null ? new ResponseEntity<>(convertToResponse(actor), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ActorResponse>> getAll() {
        List<Actor> actors = List.of(actorService.getAll());
        List<ActorResponse> actorResponses = actors.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(actorResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorResponse> update(@PathVariable String id, @RequestBody CreateActor createActor) {
        Actor updatedActor = actorService.update(id, createActor);
        return updatedActor != null ? new ResponseEntity<>(convertToResponse(updatedActor), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        actorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Helper method to convert Actor to ActorResponse
    private ActorResponse convertToResponse(Actor actor) {
        Set<String> movieIds = actor.getMovies().stream()
                .map(movie -> "/movies/" + movie.getId()) // Adjust to match your movie ID format
                .collect(Collectors.toSet());
        return new ActorResponse(actor.getId(), actor.getFullName(), movieIds);
    }
}
