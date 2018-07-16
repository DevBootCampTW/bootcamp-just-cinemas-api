package spicinemas.api.config.db;

import spicinemas.api.model.Movie;

import java.util.Map;

public interface Reader {
    public Map<String, Movie> readMovies();
}
