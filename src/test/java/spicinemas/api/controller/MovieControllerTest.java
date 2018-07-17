package spicinemas.api.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.error.MovieNotFoundException;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    private MovieController controller;

    @Mock
    private MovieRepository movieRepo;

    @Before
    public void setUp(){
        controller = new MovieController(movieRepo);
    }

    @Test
    public void getMovieShouldReturnMovie() {

        Movie movie = new Movie.MovieBuilder("Harry potter", "123", MovieListingType.NOW_SHOWING).build();
        when(movieRepo.getMovie("123")).thenReturn(movie);

        assertEquals(movie, controller.getMovie("123"));

    }

    @Test(expected = MovieNotFoundException.class)
    public void getMovieShouldThrowMovieNotFoundException() {

        when(movieRepo.getMovie("123")).thenReturn(null);

        controller.getMovie("123");

    }
}