package spicinemas.api.model;


import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import spicinemas.api.type.MovieListingType;
import spicinemas.api.util.Helper;

public class Movie {
    private String title;
    private String imdbID;
    private String imdbRating;
    private String[] soundEffects;
    private String poster;
    private String[] stills;
    private String plot;
    private MovieListingType movieListingType;
    private Movie(String title, String imdbID,MovieListingType movieListingType)
    {
        this.title=title;
        this.imdbID=imdbID;
        this.movieListingType=movieListingType;
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



    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Movie)) {
            return false;
        }
        return imdbID.equals(((Movie) obj).imdbID);
    }
}