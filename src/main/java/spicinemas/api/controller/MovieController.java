package spicinemas.api.controller;

import org.springframework.web.bind.annotation.*;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.error.MovieNotFoundException;
import spicinemas.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import spicinemas.api.model.filters.MovieFilter;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.service.MovieService;

import java.util.List;
import java.util.Objects;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/movies/now-showing",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getNowShowingMovies() {

        MovieFilter filter = new MovieFilter();
        return movieService.getMovieList(filter);
    }

    @RequestMapping(value = "/movies",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getMovies(@RequestParam(value = "listingType", defaultValue = "") String listingType) {

        MovieFilter filter = new MovieFilter();

        if(isValidParam(listingType)) {
            filter.addListingTypeFilter(MovieListingType.valueOf(listingType));
        }

        return movieService.getMovieList(filter);
    }

    private boolean isValidParam(String param) {
        return null != param && !param.isEmpty();
    }

    @RequestMapping(value = "/movies/{imdbID}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(@PathVariable("imdbID") String imdbID) {
        return movieService.getMovie(imdbID);


    }

}
