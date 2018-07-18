package spicinemas.api.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import spicinemas.api.model.type.MovieLanguage;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.model.type.MovieLocation;
import spicinemas.api.util.Helper;

@EqualsAndHashCode
@Getter
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

    private Movie(String title, String imdbID,MovieListingType movieListingType)
    {
        this.title=title;
        this.imdbID=imdbID;
        this.movieListingType=movieListingType;
    }

    @EqualsAndHashCode
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


    public static Movie convertJsonToMovie(JSONObject json)
    {
        MovieListingType listingType = MovieListingType.valueOf((String)json.get("listingType"));

        return new Movie.MovieBuilder((String)json.get("Title"),(String)json.get("imdbID"),listingType)
                .plot((String)json.get("Plot"))
                .poster((String)json.get("Poster"))
                .imdbRating((String)json.get("imdbRating"))
                .soundEffect(Helper.convertJsonArrayToStringArray((JSONArray) json.get("SoundEffects")))
                .stills(Helper.convertJsonArrayToStringArray((JSONArray) json.get("Stills")))
                .build();
    }

}