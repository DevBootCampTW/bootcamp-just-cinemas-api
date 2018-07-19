package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLanguage;
import java.util.stream.Stream;

public class LanguageFilter implements Filter {

    private final MovieLanguage language;

    public LanguageFilter(MovieLanguage language) {
    this.language = language;
    }


    @Override
    public Stream<Movie> filter(Stream<Movie> movies){
        return movies.filter(movie -> language == movie.getLanguage());
    }
}
