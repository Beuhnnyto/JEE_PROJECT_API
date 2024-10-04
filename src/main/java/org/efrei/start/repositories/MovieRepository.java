package org.efrei.start.repositories;

import org.efrei.start.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {
    
}
