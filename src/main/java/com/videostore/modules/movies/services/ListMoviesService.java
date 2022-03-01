package com.videostore.modules.movies.services;

import com.videostore.modules.movies.dtos.ResponseMovieDto;
import com.videostore.modules.movies.entities.Movie;
import com.videostore.modules.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListMoviesService {

    @Autowired
    private MovieRepository movieRepository;

    public Page<ResponseMovieDto> execute(String title, Pageable pagination) {

        Page<Movie> movies;

        if(title != null){
            movies =  this.movieRepository.findByTitle(title, pagination);
        } else {
            movies = this.movieRepository.findAll(pagination);
        }

        return movies.map(ResponseMovieDto::convert);
    }
}
