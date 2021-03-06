package spicinemas.api.model.filters;

import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLanguage;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.model.type.MovieLocation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieFilter {

    private List<Filter> filters;

    public MovieFilter() {
        this.filters = new LinkedList<>();
    }


    public void addListingTypeFilter(MovieListingType listingType){
        this.filters.add(new ListingFilter(listingType));
    }

    public List<Movie> filter(List<Movie> movies){
        Stream<Movie> filteredMovies = movies.stream();
        for(Filter filter : filters){
            filteredMovies = filter.filter(filteredMovies);
        }

        return filteredMovies.collect(Collectors.toList());
    }

    public void addLocationFilter(MovieLocation location) {
        this.filters.add(new LocationFilter(location));
    }

    public void addLanguageFilter(MovieLanguage language) {
    this.filters.add(new LanguageFilter(language));
    }
}
