package spicinemas.api.db;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import spicinemas.api.config.db.JSONReader;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MovieRepositoryTest {
    private MovieRepository movieRepo;



        @Before
        public void setUp() {
            Reader reader = Mockito.mock(JSONReader.class);
            Map<String,Movie> testMap = new HashMap<>();
            Movie movie = new Movie.MovieBuilder("Test","tt100",MovieListingType.NOW_SHOWING).build();
            testMap.put(movie.getImdbID(),movie);
            Mockito.when(reader.readMovies()).thenReturn(testMap);
            movieRepo = new MovieRepository(reader);
        }


    @Test
    public void testGetNowShowingMovieListNotNull(){
        List<Movie> movieList = movieRepo.getNowShowingMovies();
        Assert.assertNotNull(movieList);
    }

    @Test
    public void testGetNowShowingMovieListValid(){

        List<Movie> movieList = movieRepo.getNowShowingMovies();
        List<Movie> expectedList = new LinkedList<>();

        Movie movie = new Movie.MovieBuilder("Test","tt100",MovieListingType.NOW_SHOWING).build();
        expectedList.add(movie);
        Assert.assertEquals(movieList, expectedList);

    }
}