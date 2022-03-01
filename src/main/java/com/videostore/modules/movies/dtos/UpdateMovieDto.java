package com.videostore.modules.movies.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


public class UpdateMovieDto {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @NotEmpty
    private ArrayList<String> genres;

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<String> getGenres() {
        return genres;
    }

}
