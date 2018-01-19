/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tawfik.mycrudspringmvc.jsonProcessing;

import com.tawfik.mycrudspringmvc.model.Movie;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author tawfik
 */
public class JsonParser {

    private final String image_base_url = "http://image.tmdb.org/t/p/w185";

    private String popular = "popular";
    private String top_rated = "top_rated";

    private static ArrayList<Movie> popular_movies=new ArrayList<>();

    private static ArrayList<Movie> toprated_movies=new ArrayList<>();

    //popular or top_rated
    public ArrayList<Movie> getMovies(String movies_cat) throws MalformedURLException, IOException {

        URL url = new URL("http://api.themoviedb.org/3/movie/" + movies_cat + "?api_key=3c2f6a0199ac061e43e65001d476677c");

        if (movies_cat.equals(popular)) {
            if (popular_movies.isEmpty()) {
                getData(url, movies_cat);
            }
            return popular_movies;

        } else if (movies_cat.equals(top_rated)) {
            if (toprated_movies.isEmpty()) {
                getData(url, movies_cat);
            }
            return toprated_movies;
        }
        return new ArrayList();
    }

    public Movie getMovie(int id, String cat_type) throws MalformedURLException, IOException {
        URL url = new URL("http://api.themoviedb.org/3/movie/" + cat_type + "?api_key=3c2f6a0199ac061e43e65001d476677c");
        Movie movie = new Movie();

        try (InputStream is = url.openStream();
                JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("results");

            for (int i = 0; i < results.size(); i++) {
                JsonObject jsonMovie = results.getJsonObject(i);
                if (jsonMovie.getInt("id") == id) {
                    movie.setId(jsonMovie.getInt("id"));
                    movie.setTitle(jsonMovie.getString("title"));
                    movie.setOverview(jsonMovie.getString("overview"));
                    movie.setRelease_date(jsonMovie.getString("release_date"));
                    movie.setOriginal_title(jsonMovie.getString("original_title"));
                    movie.setPoster_path(image_base_url + jsonMovie.getString("poster_path"));
                    movie.setVote_average(jsonMovie.getJsonNumber("vote_average").doubleValue());
                    movie.setVote_count(jsonMovie.getInt("vote_count"));
                    System.out.println(movie.toString());

                }
            }

        }

        return movie;
    }

    public ArrayList<String> getMovieTrailer(int film_id) throws IOException {

        String yt_base_url = "https://www.youtube.com/watch?v=";
        ArrayList<String> urls = new ArrayList();

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + film_id + "/videos?api_key=3c2f6a0199ac061e43e65001d476677c");
            InputStream is = url.openStream();
            JsonReader jsr = Json.createReader(is);
            JsonObject object = jsr.readObject();
            JsonArray jsonArray = object.getJsonArray("results");
            for (int i = 0; i < jsonArray.size(); i++) {
                urls.add(yt_base_url + jsonArray.getJsonObject(i).getString("key"));
                System.out.println(yt_base_url + jsonArray.getJsonObject(i).getString("key"));

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(JsonParser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return urls;
    }

    public ArrayList<String> getEmbededMovieTrailer(int film_id) throws IOException {

        String yt_base_url = "https://www.youtube.com/embed/";
        ArrayList<String> urls = new ArrayList();

        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + film_id + "/videos?api_key=3c2f6a0199ac061e43e65001d476677c");
            InputStream is = url.openStream();
            JsonReader jsr = Json.createReader(is);
            JsonObject object = jsr.readObject();
            JsonArray jsonArray = object.getJsonArray("results");
            for (int i = 0; i < jsonArray.size(); i++) {
                urls.add(yt_base_url + jsonArray.getJsonObject(i).getString("key"));
                System.out.println(yt_base_url + jsonArray.getJsonObject(i).getString("key"));

            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(JsonParser.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return urls;
    }

    public void getData(URL url, String cat_type) throws IOException {
        try (InputStream is = url.openStream();
                JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("results");
            for (int i = 0; i < results.size(); i++) {
                Movie movie = new Movie();
                JsonObject jsonMovie = results.getJsonObject(i);
                movie.setId(jsonMovie.getInt("id"));
                movie.setTitle(jsonMovie.getString("title"));
                movie.setOverview(jsonMovie.getString("overview"));
                movie.setRelease_date("release_date");
                movie.setOriginal_title(jsonMovie.getString("original_title"));
                movie.setPoster_path(image_base_url + jsonMovie.getString("poster_path"));
                movie.setVote_average(jsonMovie.getJsonNumber("vote_average").doubleValue());
                movie.setVote_count(jsonMovie.getInt("vote_count"));
                if (cat_type.equals(popular)) {
                    popular_movies.add(movie);

                } else if (cat_type.equals(top_rated)) {
                    toprated_movies.add(movie);
                }
                System.out.println(movie.toString());
            }
            System.out.println(results.size());
        }

    }

}
