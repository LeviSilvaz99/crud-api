package com.example.crudapi.resource;

import com.example.crudapi.model.Movie;
import com.example.crudapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MoviesResource {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> listAll(){
        return movieRepository.findAll();
    }

    @GetMapping("movies/{code}")
    public Movie findById(@PathVariable Long code){
        return movieRepository.findById(code).orElse(null);
    }

    @PutMapping("/movies/{code}")
    public Movie update(@PathVariable ("code") Long code, @RequestBody Movie movie){
        return movieRepository.findById(code).map(
                record -> {
                    record.setName(movie.getName());
                    record.setGender(movie.getGender());
                    record.setClassification(movie.getClassification());
                    record.setNote(movie.getNote());
                    Movie movieUpdate = movieRepository.save(movie);
                    return movieRepository.save(record);
                }).orElse(null);
    }


    @PostMapping("/movie")
    public Movie registerMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @DeleteMapping("/movie")
    public void deletMovie(@RequestBody Movie movie){
        movieRepository.delete(movie);
    }

}
