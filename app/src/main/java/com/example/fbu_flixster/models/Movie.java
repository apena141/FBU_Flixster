package com.example.fbu_flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    String posterPath;
    String title;
    String overview;
    String backdropPath;

    // Since this method throws JSONException, when we call it we are responsible for handling it
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
    }

    public static List<Movie> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Movie> result = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            result.add(new Movie(jsonArray.getJSONObject(i)));
        }

        return result;
    }

    // See Assignment 1 hints for the image url to load and the sizes it support
    public String getPosterPath() { return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath); }

    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getOverview() { return overview; }

    public void setOverview(String overview) { this.overview = overview; }

    public String getBackdropPath() { return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath); }

    public void setBackdropPath(String backdropPath) { this.backdropPath = backdropPath; }
}
