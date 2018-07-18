package spicinemas.api.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.error.MovieNotFoundException;
import spicinemas.api.model.Movie;
import spicinemas.api.model.filters.MovieFilter;
import spicinemas.api.model.type.MovieListingType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spicinemas.api.model.type.MovieLanguage.ENGLISH;
import static spicinemas.api.model.type.MovieLanguage.HINDI;
import static spicinemas.api.model.type.MovieListingType.NOW_SHOWING;
import static spicinemas.api.model.type.MovieLocation.BANGALORE;
import static spicinemas.api.model.type.MovieLocation.DELHI;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository repository;
    private MovieService service;

    @Before
    public void setup()
    {
        service= new MovieService(repository);
    }

    @Test
    public void testGetMovieListNowShowing()
    {
        List<Movie> exptectedList = new ArrayList<>();
        Movie testNowShowing = new Movie.MovieBuilder("Test1","1",NOW_SHOWING).build();
        Movie testUpcoming = new Movie.MovieBuilder("Test2","2",MovieListingType.UP_COMING).build();
        List<Movie> movieMockList = new ArrayList<>();
        movieMockList.add(testNowShowing);
        movieMockList.add(testUpcoming);
        exptectedList.add(testNowShowing);
        when(repository.getAllMovies()).thenReturn(movieMockList);

        MovieFilter filter = new MovieFilter();
        filter.addListingTypeFilter(NOW_SHOWING);

        List<Movie> actual = service.getMovieList(filter);

        assertEquals(exptectedList, actual);

    }

    @Test
    public void testGetMovieListUpcoming()
    {
        List<Movie> exptectedList = new ArrayList<>();
        Movie testNowShowing = new Movie.MovieBuilder("Test1","1",NOW_SHOWING).build();
        Movie testUpcoming = new Movie.MovieBuilder("Test2","2",MovieListingType.UP_COMING).build();
        List<Movie> movieMockList = new ArrayList<>();
        movieMockList.add(testNowShowing);
        movieMockList.add(testUpcoming);
        exptectedList.add(testUpcoming);
        when(repository.getAllMovies()).thenReturn(movieMockList);

        MovieFilter filter = new MovieFilter();
        filter.addListingTypeFilter(MovieListingType.UP_COMING);

        List<Movie> actual = service.getMovieList(filter);

        assertEquals(exptectedList, actual);

    }

    @Test
    public void testGetMovieListSorting()
    {
        List<Movie> exptectedList = new ArrayList<>();
        Movie movieZ = new Movie.MovieBuilder("Z","1",NOW_SHOWING).build();
        Movie movieA = new Movie.MovieBuilder("A","2",NOW_SHOWING).build();
        List<Movie> movieMockList = new ArrayList<>();
        movieMockList.add(movieZ);
        movieMockList.add(movieA);

        exptectedList.add(movieA);
        exptectedList.add(movieZ);
        when(repository.getAllMovies()).thenReturn(movieMockList);

        MovieFilter filter = new MovieFilter();

        List<Movie> actual = service.getMovieList(filter);

        assertEquals(exptectedList, actual);

    }

    @Test
    public void testGetMovieListFilteredByLocation()
    {
        List<Movie> exptectedList = new ArrayList<>();
        Movie bangaloreMovie = new Movie.MovieBuilder("Z","1",NOW_SHOWING).location(BANGALORE).build();

        Movie delhiMovie = new Movie.MovieBuilder("A","2",NOW_SHOWING).location(DELHI).build();
        List<Movie> movieMockList = new ArrayList<>();
        movieMockList.add(bangaloreMovie);
        movieMockList.add(delhiMovie);

        exptectedList.add(delhiMovie);
        when(repository.getAllMovies()).thenReturn(movieMockList);

        MovieFilter filter = new MovieFilter();
        filter.addLocationFilter(DELHI);

        List<Movie> actual = service.getMovieList(filter);

        assertEquals(exptectedList, actual);

    }

    @Test
    public void testGetMovieListFilteredByLanguage()
    {
        List<Movie> exptectedList = new ArrayList<>();
        Movie englishMovie = new Movie.MovieBuilder("Z","1",NOW_SHOWING).language(ENGLISH).build();

        Movie hindiMovie = new Movie.MovieBuilder("A","2",NOW_SHOWING).language(HINDI).build();
        List<Movie> movieMockList = new ArrayList<>();
        movieMockList.add(englishMovie);
        movieMockList.add(hindiMovie);

        exptectedList.add(hindiMovie);
        when(repository.getAllMovies()).thenReturn(movieMockList);

        MovieFilter filter = new MovieFilter();
        filter.addLanguageFilter(HINDI);

        List<Movie> actual = service.getMovieList(filter);

        assertEquals(exptectedList, actual);

    }

    @Test(expected = MovieNotFoundException.class)
    public void testGetMovieNotFound(){
        Optional<Movie> movie = Optional.empty();
        when(repository.getMovie("123")).thenReturn(movie);

        service.getMovie("123");
    }

    @Test
    public void testGetMovie(){
        Movie movie = mock(Movie.class);
        Optional<Movie> movieOptional = Optional.of(movie);
        when(repository.getMovie("123")).thenReturn(movieOptional);

        assertEquals(movie, service.getMovie("123"));
    }


}
