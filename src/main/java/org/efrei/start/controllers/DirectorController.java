package org.efrei.start.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.efrei.start.dto.CreateDirector;
import org.efrei.start.dto.DirectorResponse;
import org.efrei.start.models.Director;
import org.efrei.start.services.DirectorService;
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
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<DirectorResponse> create(@RequestBody CreateDirector createDirector) {
        // Handle null movieIds
        if (createDirector.getMovieIds() == null) {
            createDirector.setMovieIds(new HashSet<>()); // Set to empty if null
        }

        Director createdDirector = directorService.create(createDirector);
        DirectorResponse response = new DirectorResponse(
                createdDirector.getId(), // No prefix here
                createdDirector.getName(),
                createdDirector.getMovies().stream()
                        .map(movie -> "movies/" + movie.getId()) // Prefix movie IDs with "movies/"
                        .collect(Collectors.toSet())); // Set<String> movieIds
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{directorId}") // Updated path variable
    public ResponseEntity<DirectorResponse> getById(@PathVariable String directorId) {
        Director director = directorService.getById(directorId);
        if (director != null) {
            DirectorResponse response = new DirectorResponse(
                    director.getId(), // No prefix here
                    director.getName(),
                    director.getMovies().stream()
                            .map(movie -> "movies/" + movie.getId()) // Prefix movie IDs with "movies/"
                            .collect(Collectors.toSet())); // Set<String> movieIds
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponse>> getAll() {
        Director[] directors = directorService.getAll();
        List<DirectorResponse> responses = Arrays.stream(directors)
                .map(director -> new DirectorResponse(
                        director.getId(), // No prefix here
                        director.getName(),
                        director.getMovies().stream()
                                .map(movie -> "movies/" + movie.getId()) // Prefix movie IDs with "movies/"
                                .collect(Collectors.toSet())) // Set<String> movieIds
                ).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("/{directorId}") // Updated path variable
    public ResponseEntity<DirectorResponse> update(@PathVariable String directorId,
            @RequestBody CreateDirector createDirector) {
        Director updatedDirector = directorService.update(directorId, createDirector);
        if (updatedDirector != null) {
            DirectorResponse response = new DirectorResponse(
                    updatedDirector.getId(), // No prefix here
                    updatedDirector.getName(),
                    updatedDirector.getMovies().stream()
                            .map(movie -> "movies/" + movie.getId()) // Prefix movie IDs with "movies/"
                            .collect(Collectors.toSet())); // Set<String> movieIds
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{directorId}") // Updated path variable
    public ResponseEntity<Void> delete(@PathVariable String directorId) {
        directorService.delete(directorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
