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
            Movie movie1 = new Movie.MovieBuilder("Test1","tt100",MovieListingType.NOW_SHOWING).build();
            Movie movie2 = new Movie.MovieBuilder("Test2","tt200",MovieListingType.NOW_SHOWING).build();
            testMap.put(movie1.getImdbID(),movie1);
            testMap.put(movie2.getImdbID(),movie2);
            Mockito.when(reader.readMovies()).thenReturn(testMap);
            movieRepo = new MovieRepository(reader);
        }


    @Test
    public void testGetNowShowingMovieListNotNull(){
        List<Movie> movieList = movieRepo.getNowShowingMovies();
        Assert.assertNotNull(movieList);
    }

    @Test
    public void testGetNowShowingMovieListSorted(){

        List<Movie> movieList = movieRepo.getNowShowingMovies();
        List<Movie> expectedList = new LinkedList<>();

        Movie movie1 = new Movie.MovieBuilder("Test1","tt100",MovieListingType.NOW_SHOWING).build();
        Movie movie2 = new Movie.MovieBuilder("Test2","tt200",MovieListingType.NOW_SHOWING).build();
        expectedList.add(movie1);
        expectedList.add(movie2);
        Assert.assertEquals(movieList, expectedList);

    }

    @Test
    public void testGetMovieWhenMatchIsFoundShouldNotBeNull()
    {
        Movie movie = movieRepo.getMovie("tt100");

        Assert.assertEquals("tt100", movie.getImdbID());
    }

    @Test
    public void testGetMovieWhenNoMatchIsFoundShouldBeNull()
    {
        Movie movie = movieRepo.getMovie("notAnImdbMovieId");

        Assert.assertNull(movie);
    }

    @Test
    public void testGetMovieWhenNullIsPassedThenShouldReturnNull()
    {
        Movie movie = movieRepo.getMovie(null);

        Assert.assertNull(movie);
    }
}