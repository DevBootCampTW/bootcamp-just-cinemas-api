package spicinemas.api.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.error.InvalidRequestException;
import spicinemas.api.model.Movie;
import spicinemas.api.model.filters.MovieFilter;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    private MovieController controller;

    @Mock
    private MovieService movieService;

    @Before
    public void setUp(){
        controller = new MovieController(movieService);
    }

    @Test
    public void testGetMoviesReturnMovieList() {

        Movie movie = new Movie.MovieBuilder("Harry potter", "123", MovieListingType.NOW_SHOWING).build();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        when(movieService.getMovieList(any(MovieFilter.class))).thenReturn(movieList);

        Assert.assertEquals(movieList, controller.getMovies(null, null, null));
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetMoviesThrowsInvalidRequestException() {
        controller.getMovies("INVALID_VALUE", "INVALID_LANGUAGE", "INVALID_LOCATION");
    }

    @Test
    public void getMovieShouldReturnMovie() {

        Movie movie = new Movie.MovieBuilder("Harry potter", "123", MovieListingType.NOW_SHOWING).build();
        when(movieService.getMovie("123")).thenReturn(movie);

        assertEquals(movie, controller.getMovie("123"));

    }

}