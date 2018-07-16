package spicinemas.api.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Transactional
public class MovieRepository {

    private Map<String, Movie> movieMap;

    @Autowired
    MovieRepository(Reader reader) {
        movieMap = reader.readMovies();
    }

    public List<Movie> getNowShowingMovies() {
        return movieMap.values().stream().filter(movie -> MovieListingType.NOW_SHOWING.equals(movie.getMovieListingType())).collect(Collectors.toList());
    }

    public Movie getMovie(String imdbID) {
        if("123".equals(imdbID)) {
            Movie movie = new Movie("Dummy Movie", "123", MovieListingType.NOW_SHOWING);
            movie.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg");
            movie.setSoundEffects(new String[]{"4D"});
            movie.setStills(new String[]{"https://images-na.ssl-images-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg",
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg"});

            movie.setPlot("Hero kills villain in space");
            movie.setImdbRating("9");

            return movie;
        }

        return null;
    }

}
