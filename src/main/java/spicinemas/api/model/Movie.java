package spicinemas.api.model;


import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import spicinemas.api.model.type.MovieLanguage;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.model.type.MovieLocation;
import spicinemas.api.util.Helper;

import java.util.Arrays;
import java.util.Objects;

public class Movie {
    private String title;
    private String imdbID;
    private String imdbRating;
    private String[] soundEffects;
    private String poster;
    private String[] stills;
    private String plot;
    private MovieListingType movieListingType;
    private MovieLocation location;
    private MovieLanguage language;

    private Movie(String title, String imdbID, MovieListingType movieListingType)
    {
        this.title=title;
        this.imdbID=imdbID;
        this.movieListingType=movieListingType;
    }

    public static class MovieBuilder
    {
        private Movie movie;
        public MovieBuilder(String title, String imdbID,MovieListingType movieListingType)
        {
            movie = new Movie(title, imdbID, movieListingType);

        }

        public MovieBuilder imdbRating(String imdbRating){
            movie.imdbRating=imdbRating;
            return this;
        }
        public MovieBuilder soundEffect(String soundEffects[]){
            movie.soundEffects=soundEffects;
            return this;
        }
        public MovieBuilder poster(String poster){
            movie.poster=poster;
            return this;
        }
        public MovieBuilder stills(String stills[]){
            movie.stills=stills;
            return this;
        }
        public MovieBuilder plot(String plot){
            movie.plot=plot;
            return this;
        }
        public Movie build()
        {
            return movie;
        }

        public MovieBuilder location(MovieLocation location) {
            movie.location = location;
            return this;
        }

        public MovieBuilder language(MovieLanguage language) {
            movie.language = language;
            return this;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String[] getSoundEffects() {
        return soundEffects;
    }

    public String getPoster() {
        return poster;
    }

    public String[] getStills() {
        return stills;
    }

    public String getPlot() {
        return plot;
    }

    public MovieListingType getMovieListingType() {
        return movieListingType;
    }

    public MovieLocation getLocation() {
        return location;
    }

    public MovieLanguage getLanguage() {
        return language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(imdbID, movie.imdbID) &&
                Objects.equals(imdbRating, movie.imdbRating) &&
                Arrays.equals(soundEffects, movie.soundEffects) &&
                Objects.equals(poster, movie.poster) &&
                Arrays.equals(stills, movie.stills) &&
                Objects.equals(plot, movie.plot) &&
                movieListingType == movie.movieListingType &&
                location == movie.location &&
                language == movie.language;
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(title, imdbID, imdbRating, poster, plot, movieListingType, location, language);
        result = 31 * result + Arrays.hashCode(soundEffects);
        result = 31 * result + Arrays.hashCode(stills);
        return result;
    }


}