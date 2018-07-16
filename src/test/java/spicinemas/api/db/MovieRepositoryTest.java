package spicinemas.api.db;


import org.jooq.DSLContext;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import spicinemas.SpiCinemasApplication;
import spicinemas.api.config.db.JSONReader;
import spicinemas.api.config.db.Reader;
import spicinemas.api.model.Movie;
import spicinemas.api.type.MovieListingType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class MovieRepositoryTest {
    private MovieRepository movieRepo;

    static final String moveiJson ="{\n" +
            "    \"Plot\":\"Forced to spend his summer holidays with his muggle relations, Harry Potter gets a real shock when he gets a surprise visitor: Dobby the house-elf, who warns Harry Potter against returning to Hogwarts, for terrible things are going to happen. Harry decides to ignore Dobby's warning and continues with his pre-arranged schedule. But at Hogwarts, strange and terrible things are indeed happening: Harry is suddenly hearing mysterious voices from inside the walls, muggle-born students are being attacked, and a message scrawled on the wall in blood puts everyone on his/her guard - \\\"The Chamber Of Secrets Has Been Opened. Enemies Of The Heir, Beware\\\" .\",\n" +
            "    \"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg\",\n" +
            "    \"SoundEffects\": [\"DOLBY\",\"DTS\"],\n" +
            "    \"Stills\": [\n" +
            "      \"https://i.imgur.com/3fJ1P48.gif\",\n" +
            "      \"https://i.imgur.com/j6ECXmD.gif\",\n" +
            "      \"https://i.imgur.com/v0ooIH0.gif\"\n" +
            "    ],\n" +
            "    \"Title\":\"Harry Potter and the Chamber of Secrets\",\n" +
            "    \"imdbID\":\"tt0295297\",\n" +
            "    \"listingType\": \"NOW_SHOWING\",\n" +
            "    \"imdbRating\":\"7.4\",\n" +
            "  }";


        @Before
        public void setUp() throws ParseException {



            Reader reader = Mockito.mock(JSONReader.class);
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(moveiJson);

            Map<String,Movie> testMap = new HashMap<>();
            Movie movie = new Movie(jsonObject);
            testMap.put(movie.getImdbID(),movie);
            Mockito.when(reader.readMovies()).thenReturn(testMap);
            movieRepo = new MovieRepository(reader);
        }


    @Test
    public void testGetNowShowingMovieListNotNull(){
        List<Movie> movieList = movieRepo.getNowShowingMovies();
        Assert.assertNotNull(movieList);
    }

    @Test
    public void testGetNowShowingMovieListValid() throws ParseException {

        List<Movie> movieList = movieRepo.getNowShowingMovies();
        List<Movie> expectedList = new LinkedList<>();
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(moveiJson);

        Movie object = new Movie(jsonObject);
        expectedList.add(object);
        Assert.assertEquals(movieList, expectedList);

    }
}