package com.videostore.modules.movies.repositories;

import com.videostore.modules.movies.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Page<Movie> findByTitle(String title, Pageable pagination);
}