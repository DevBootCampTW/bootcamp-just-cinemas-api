package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLanguage;

import java.util.List;
import java.util.stream.Collectors;

public class LanguageFilter implements Filter {

    private final MovieLanguage language;

    public LanguageFilter(MovieLanguage language) {
    this.language = language;
    }


    @Override
    public List<Movie> filter(List<Movie> movies) {
        return movies.stream().filter(movie -> language == movie.getLanguage()).collect(Collectors.toList());
    }
}
