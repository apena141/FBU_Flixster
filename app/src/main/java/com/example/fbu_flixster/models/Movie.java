package com.example.fbu_flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    String posterPath;
    String title;
    String overview;
    String backdropPath;
    Double voteAverage;

    // Empty constructor for Parcel
    public Movie(){}

    // Since this method throws JSONException, when we call it we are responsible for handling it
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        voteAverage = jsonObject.getDouble("vote_average");
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

    public String getTitle() { return title; }

    public String getOverview() { return overview; }

    public String getBackdropPath() { return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath); }

    public Double getVoteAverage() { return voteAverage; }
}
