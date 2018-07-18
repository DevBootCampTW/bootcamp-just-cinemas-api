package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLocation;

import java.util.List;
import java.util.stream.Collectors;

public class LocationFilter implements Filter {
    private final MovieLocation location;

    public LocationFilter(MovieLocation location) {
        this.location = location;
    }


    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream().filter(movie -> location == movie.getLocation()).collect(Collectors.toList());
    }
}
