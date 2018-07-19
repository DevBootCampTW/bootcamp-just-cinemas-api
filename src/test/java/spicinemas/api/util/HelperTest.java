package spicinemas.api.util;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.junit.Assert;
import org.junit.Test;
import spicinemas.api.model.Movie;
import spicinemas.api.model.type.MovieLanguage;
import spicinemas.api.model.type.MovieListingType;
import spicinemas.api.model.type.MovieLocation;


public class HelperTest {

    @Test
    public void testConvertJsonToMovie() throws ParseException {
        String jsonString = "{\n" +
                "    \"Plot\": \"Voldemort's power is growing stronger. He now has control over the Ministry of Magic and Hogwarts. Harry, Ron, and Hermione decide to finish Dumbledore's work and find the rest of the Horcruxes to defeat the Dark Lord. But little hope remains for the Trio, and the rest of the Wizarding World, so everything they do must go as planned.\",\n" +
                "    \"Poster\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTQ2OTE1Mjk0N15BMl5BanBnXkFtZTcwODE3MDAwNA@@._V1_SX300.jpg\",\n" +
                "    \"Title\": \"Harry Potter and the Deathly Hallows: Part 1\",\n" +
                "    \"imdbID\": \"tt0926084\",\n" +
                "    \"imdbRating\": \"7.7\",\n" +
                "    \"SoundEffects\": [\"RX6\",\"SDDS\"],\n" +
                "    \"Stills\": [\n" +
                "      \"https://i.imgur.com/i3aD05u.png\",\n" +
                "      \"https://i.imgur.com/ABinULO.gif\",\n" +
                "      \"https://i.imgur.com/Wflfyct.gif\"\n" +
                "    ],\n" +
                "    \"listingType\": \"NOW_SHOWING\"\n" +
                "    \"Location\": \"PUNE\"\n" +
                "    \"Language\": \"HINDI\"\n" +
                "  }";

        JSONObject json = (JSONObject) new JSONParser().parse(jsonString);
        MovieListingType listingType = MovieListingType.valueOf((String)json.get("listingType"));
        MovieLanguage language = MovieLanguage.valueOf((String)json.get("Language"));
        MovieLocation location = MovieLocation.valueOf((String)json.get("Location"));

        Movie movieExpected =  new Movie.MovieBuilder((String)json.get("Title"),(String)json.get("imdbID"),listingType)
                .plot((String)json.get("Plot"))
                .poster((String)json.get("Poster"))
                .imdbRating((String)json.get("imdbRating"))
                .soundEffect(Helper.convertJsonArrayToStringArray((JSONArray) json.get("SoundEffects")))
                .stills(Helper.convertJsonArrayToStringArray((JSONArray) json.get("Stills")))
                .location(location)
                .language(language)
                .build();

        Movie actual = Helper.convertJsonToMovie(json);

        Assert.assertEquals(movieExpected,actual);




    }

}
