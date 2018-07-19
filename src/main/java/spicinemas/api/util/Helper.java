package spicinemas.api.util;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLanguage;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.model.type.MovieLocation;

public class Helper {

    public static String [] convertJsonArrayToStringArray(JSONArray jsonArray)
    {
        String[] strArr=null;
        if(jsonArray!=null) {
            strArr = new String[jsonArray.size()];
            {
                for(int i =0;i<strArr.length;i++)
                    strArr[i]=(String)jsonArray.get(i);
            }
        }

        return strArr;
    }

    public static Movie convertJsonToMovie(JSONObject json)
    {
        MovieListingType listingType = MovieListingType.valueOf((String)json.get("listingType"));
        MovieLanguage language = MovieLanguage.valueOf((String)json.get("Language"));
        MovieLocation location = MovieLocation.valueOf((String)json.get("Location"));
        return new Movie.MovieBuilder((String)json.get("Title"),(String)json.get("imdbID"),listingType)
                .plot((String)json.get("Plot"))
                .poster((String)json.get("Poster"))
                .imdbRating((String)json.get("imdbRating"))
                .soundEffect(Helper.convertJsonArrayToStringArray((JSONArray) json.get("SoundEffects")))
                .stills(Helper.convertJsonArrayToStringArray((JSONArray) json.get("Stills")))
                .language(language)
                .location(location)
                .build();
    }
}
