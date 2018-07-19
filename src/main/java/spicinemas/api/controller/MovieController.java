package spicinemas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import spicinemas.api.error.InvalidRequestException;
import spicinemas.api.model.Movie;
import spicinemas.api.model.filters.MovieFilter;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.service.MovieService;

import java.util.List;

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

        MovieFilter filter = getMovieFilter(listingType);

        return movieService.getMovieList(filter);
    }

    private MovieFilter getMovieFilter(String listingType) {
        MovieFilter filter = new MovieFilter();

        try {
            if (isValidParam(listingType)) {
                filter.addListingTypeFilter(MovieListingType.valueOf(listingType));
            }
        } catch (IllegalArgumentException e)
        {
            throw new InvalidRequestException();
        }
        return filter;
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
