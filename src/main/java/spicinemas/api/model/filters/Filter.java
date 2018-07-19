package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;

import java.util.stream.Stream;

public interface Filter {

    Stream<Movie> filter(Stream<Movie> movie);
}
