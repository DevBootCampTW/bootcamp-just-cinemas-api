package spicinemas.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spicinemas.api.db.MovieRepository;
import spicinemas.api.error.MovieNotFoundException;
import spicinemas.api.model.Movie;
import spicinemas.api.model.filters.MovieFilter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {


    private MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository)
    {
        this.repository=repository;
    }


    public List<Movie> getMovieList(MovieFilter filter) {
        List<Movie> movies = repository.getAllMovies();

        return filter.filter(movies).stream().sorted(Comparator.comparing(Movie::getTitle))
                .collect(Collectors.toList());

    }

    public Movie getMovie(String imdbID) {
        Optional<Movie> movie = repository.getMovie(imdbID);
        if(!movie.isPresent()){
            throw new MovieNotFoundException();
        }

        return movie.get();
    }
}
