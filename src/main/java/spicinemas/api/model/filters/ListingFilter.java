package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieListingType;

import java.util.List;
import java.util.stream.Collectors;

public class ListingFilter implements Filter {

    private final MovieListingType type;

    public ListingFilter(MovieListingType type) {
        this.type = type;
    }

    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream().filter(movie -> type == movie.getMovieListingType()).collect(Collectors.toList());
    }
}
