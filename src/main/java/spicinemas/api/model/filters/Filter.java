package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;

import java.util.List;

public interface Filter {

    List<Movie> filter(List<Movie> movie);
}
