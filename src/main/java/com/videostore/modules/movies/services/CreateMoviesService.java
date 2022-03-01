package com.videostore.modules.movies.services;

import com.videostore.modules.movies.dtos.CreateMovieDto;
import com.videostore.modules.movies.dtos.ResponseMovieDto;
import com.videostore.modules.movies.entities.Movie;
import com.videostore.modules.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMoviesService {
    @Autowired
    private MovieRepository movieRepository;

    public ResponseMovieDto execute(CreateMovieDto createMovieDto){
        Movie movie = movieRepository.save(new Movie(createMovieDto.getTitle(), createMovieDto.getDescription(), createMovieDto.getGenres()));
        return ResponseMovieDto.convert(movie);
    }
}
