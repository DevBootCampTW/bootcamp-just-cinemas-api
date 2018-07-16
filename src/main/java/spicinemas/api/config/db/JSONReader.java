package spicinemas.api.config.db;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.springframework.stereotype.Component;
import spicinemas.api.model.Movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
@Component
public class JSONReader implements Reader{

private InputStreamReader reader;
    JSONReader() throws FileNotFoundException {
    String path = "src/main/resources/movie-list.json";
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
                Movie movie = new Movie((JSONObject) o);
                movieMap.put(movie.getImdbID(), movie);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return movieMap;
    }

}
