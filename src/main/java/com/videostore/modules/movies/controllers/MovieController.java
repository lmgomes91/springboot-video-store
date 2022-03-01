package com.videostore.modules.movies.controllers;

import com.videostore.modules.movies.dtos.CreateMovieDto;
import com.videostore.modules.movies.dtos.DetailedResponseMovieDto;
import com.videostore.modules.movies.dtos.ResponseMovieDto;
import com.videostore.modules.movies.dtos.UpdateMovieDto;
import com.videostore.modules.movies.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private ListMoviesService listMoviesService;
    @Autowired
    private CreateMoviesService createMoviesService;
    @Autowired
    private UpdateMoviesService updateMoviesService;
    @Autowired
    private DeleteMoviesService deleteMoviesService;
    @Autowired
    private ListMovieByIdService listMovieByIdService;

    @GetMapping()
    public Page<ResponseMovieDto> list(@RequestParam(required = false) String title,
                            @PageableDefault(sort = "id",
                                    direction = Sort.Direction.DESC,
                                    page = 0, size = 5) Pageable pagination)
    {
        return listMoviesService.execute(title, pagination);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DetailedResponseMovieDto> listById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(listMovieByIdService.execute(id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseMovieDto> create(@RequestBody @Valid CreateMovieDto createMovieDto){
            return ResponseEntity.ok(createMoviesService.execute(createMovieDto));
    }



    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseMovieDto> update(@PathVariable UUID id, @RequestBody @Valid UpdateMovieDto updateMovieDto) {
        try{
            return ResponseEntity.ok(updateMoviesService.execute(updateMovieDto, id));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Boolean> delete(@PathVariable UUID id){
        try{
            deleteMoviesService.execute(id);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}

