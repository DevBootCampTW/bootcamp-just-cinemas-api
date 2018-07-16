package spicinemas.api.controller;

import org.springframework.web.bind.annotation.*;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import spicinemas.api.type.MovieListingType;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieRepository movieRepo;

    @RequestMapping(value = "/init",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void init() {

        movieRepo.addMovie(new Movie("Dunkirk", "good", MovieListingType.NOW_SHOWING));
    }

    @RequestMapping(value = "/movies/now-showing",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getNowShowingMovies() {
        return movieRepo.getNowShowingMovies();
    }

    @RequestMapping(value = "/movies/{imdbID}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(@PathVariable("imdbID") String imdbID) {
        return movieRepo.getMovie(imdbID);
    }

}
