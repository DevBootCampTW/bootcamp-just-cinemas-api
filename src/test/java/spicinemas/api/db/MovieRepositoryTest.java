package spicinemas.api.db;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import spicinemas.api.config.db.JSONReader;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieListingType;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class MovieRepositoryTest {
    private MovieRepository movieRepo;
    private Movie movie1;
    private Movie movie2;


    @Before
        public void setUp() {
            Reader reader = Mockito.mock(JSONReader.class);
            Map<String,Movie> testMap = new HashMap<>();
            movie1 = new Movie.MovieBuilder("Test1","tt100",MovieListingType.NOW_SHOWING).build();
            movie2 = new Movie.MovieBuilder("Test2","tt200",MovieListingType.NOW_SHOWING).build();
            testMap.put(movie1.getImdbID(), movie1);
            testMap.put(movie2.getImdbID(), movie2);
            when(reader.readMovies()).thenReturn(testMap);
            movieRepo = new MovieRepository(reader);
        }


    @Test
    public void testGetAllMovies(){
        List<Movie> expectedList = new LinkedList<>();
        expectedList.add(movie1);
        expectedList.add(movie2);

        List<Movie> movieList = movieRepo.getAllMovies();
        assertEquals(expectedList, movieList);
    }

    @Test
    public void testGetAMovie(){
        Optional<Movie> movie = movieRepo.getMovie("tt200");
        assertEquals(movie2, movie.get());
    }



}