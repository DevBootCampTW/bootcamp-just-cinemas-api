package spicinemas.api.config.db;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.springframework.stereotype.Component;
import spicinemas.api.model.Movie;
import spicinemas.api.util.Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
@Component
public class JSONReader implements Reader{

private InputStreamReader reader;
   public JSONReader() throws FileNotFoundException {
    String path = "build/resources/main/movie-list.json";
    reader = new FileReader(path);
    }

    @Override
    public Map<String, Movie> readMovies() {
        Map<String, Movie> movieMap = null;
        JSONParser parser = new JSONParser();
        try {
            JSONArray movieArray = (JSONArray)parser.parse(reader);
            movieMap = new HashMap<>();
            for (Object o : movieArray) {
                Movie movie = Helper.convertJsonToMovie((JSONObject) o);
                movieMap.put(movie.getImdbID(), movie);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movieMap;
    }

}
