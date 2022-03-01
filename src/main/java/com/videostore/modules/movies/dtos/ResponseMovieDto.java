package com.videostore.modules.movies.dtos;

import com.videostore.modules.movies.entities.Movie;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.UUID;

public class ResponseMovieDto {
    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private UUID id;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private ArrayList<String> genres;

    public ResponseMovieDto(String title, UUID id, String description, ArrayList<String> genres) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.genres = genres;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public static ResponseMovieDto convert(Movie movie){
        return new ResponseMovieDto(movie.getTitle(), movie.getId(),movie.getDescription(),movie.getGenres());
    }

}
