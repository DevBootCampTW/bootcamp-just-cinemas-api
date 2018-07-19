package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieListingType;
import java.util.stream.Stream;

public class ListingFilter implements Filter {

    private final MovieListingType type;

    public ListingFilter(MovieListingType type) {
        this.type = type;
    }

    @Override
     public Stream<Movie> filter(Stream<Movie> movies) {
        return movies.filter(movie -> type == movie.getMovieListingType());
    }
}
