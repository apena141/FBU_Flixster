package com.example.fbu_flixster;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.fbu_flixster.adapters.MovieAdapter;
import com.example.fbu_flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    public static final String API_KEY = "b4f45155b698ba40d8e9ce3bdcd4ebee";

    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        movieList = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL + API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "OnSuccess");
                JSONObject object = json.jsonObject;
                try {
                    JSONArray results = object.getJSONArray("results");
                    Log.d(TAG, results.toString());
                    movieList.addAll(Movie.fromJsonArray(results));
                    Log.i(TAG, "Results: " + results.toString());
                    Log.i(TAG, "Movie list size: " + movieList.size());
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "Hit json exception " + e.getMessage());
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "OnFailure");
            }
        });
    }
}