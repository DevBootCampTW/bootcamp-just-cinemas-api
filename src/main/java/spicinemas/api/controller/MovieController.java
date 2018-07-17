package spicinemas.api.controller;

import org.springframework.web.bind.annotation.*;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.error.MovieNotFoundException;
import spicinemas.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import spicinemas.api.type.MovieListingType;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MovieController {

    MovieRepository movieRepo;

    @Autowired
    public MovieController(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @RequestMapping(value = "/init",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void init() {
        }

    @RequestMapping(value = "/movies/now-showing",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getNowShowingMovies() {
        return movieRepo.getNowShowingMovies();
    }

    @RequestMapping(value = "/movies/{imdbID}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(@PathVariable("imdbID") String imdbID) {
        Movie movie = movieRepo.getMovie(imdbID);

        if(null == movie){
            throw new MovieNotFoundException();
        }
        return movie;
    }

}
