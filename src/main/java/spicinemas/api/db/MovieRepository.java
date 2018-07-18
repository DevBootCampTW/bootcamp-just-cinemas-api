package spicinemas.api.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepository {

    private Map<String, Movie> movieMap;

    @Autowired
    MovieRepository(Reader reader) {
        movieMap = reader.readMovies();
    }

    public Optional<Movie> getMovie(String imdbID) {
       return   Optional.ofNullable(movieMap.get(imdbID));


    }

    public List<Movie> getAllMovies() {
        return new LinkedList<>(movieMap.values());
    }
}
