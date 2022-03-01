package com.videostore.modules.movies.services;

import com.videostore.modules.movies.dtos.DetailedResponseMovieDto;
import com.videostore.modules.movies.dtos.UpdateMovieDto;
import com.videostore.modules.movies.entities.Movie;
import com.videostore.modules.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ListMovieByIdService {
    @Autowired
    private MovieRepository movieRepository;

    public DetailedResponseMovieDto execute(UUID id) throws Exception {

        Optional<Movie> movie = movieRepository.findById(id);

        if(movie.isEmpty()){
            throw new Exception("Movie not found");
        }
        return DetailedResponseMovieDto.convert(movie.get());
    }
}
