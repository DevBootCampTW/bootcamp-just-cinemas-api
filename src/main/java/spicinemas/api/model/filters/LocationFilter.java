package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLocation;
import java.util.stream.Stream;

public class LocationFilter implements Filter {
    private final MovieLocation location;

    public LocationFilter(MovieLocation location) {
        this.location = location;
    }


    @Override
    public Stream<Movie> filter(Stream<Movie> movies) {
        return movies.filter(movie -> location == movie.getLocation());
    }
}
