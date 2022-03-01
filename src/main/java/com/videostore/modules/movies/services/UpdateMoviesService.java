package com.videostore.modules.movies.services;

import com.videostore.modules.movies.dtos.CreateMovieDto;
import com.videostore.modules.movies.dtos.ResponseMovieDto;
import com.videostore.modules.movies.dtos.UpdateMovieDto;
import com.videostore.modules.movies.entities.Movie;
import com.videostore.modules.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateMoviesService {
    @Autowired
    private MovieRepository movieRepository;

    public ResponseMovieDto execute(UpdateMovieDto updateMovieDto, UUID id) throws Exception {

        Optional<Movie> movie = movieRepository.findById(id);

        if(movie.isEmpty()){
            throw new Exception("Movie not found");
        }

        movie.get().setTitle(updateMovieDto.getTitle());
        movie.get().setDescription(updateMovieDto.getDescription());
        movie.get().setGenres(updateMovieDto.getGenres());

        return ResponseMovieDto.convert(movie.get());
    }
}
