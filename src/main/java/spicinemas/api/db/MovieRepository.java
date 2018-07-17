package spicinemas.api.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    private Map<String, Movie> movieMap;

    @Autowired
    MovieRepository(Reader reader) {
        movieMap = reader.readMovies();
    }

    public List<Movie> getNowShowingMovies() {
        return movieMap.values().stream()
                .filter(movie -> MovieListingType.NOW_SHOWING.equals(movie.getMovieListingType()))
                .sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());
    }



    public Movie getMovie(String imdbID) {

        return movieMap.get(imdbID);
    }

}
