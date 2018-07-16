package spicinemas.api.model;

import lombok.EqualsAndHashCode;
import spicinemas.api.type.MovieListingType;
@EqualsAndHashCode()
public class Movie {
    private String title;
    private String imdbID;
    private String imdbRating;
    private String[] soundEffects;
    private String poster;
    private String[] stills;
    private String plot;
    private MovieListingType movieListingType;
    public Movie(String title, String imdbID,MovieListingType movieListingType )
    {
        this.title=title;
        this.imdbID=imdbID;
        this.movieListingType=movieListingType;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setStills(String[] stills) {
        this.stills = stills;
    }

    public void setSoundEffects(String[] soundEffects) {
        this.soundEffects = soundEffects;
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
}