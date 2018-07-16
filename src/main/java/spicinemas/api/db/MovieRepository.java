package spicinemas.api.db;

import org.springframework.stereotype.Repository;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.Collections;
import java.util.List;

@Repository
public class MovieRepository {

    public List<Movie> getNowShowingMovies() {
        return Collections.emptyList();
    }

    public void addMovie(Movie movie) {

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
