package com.example.fbu_flixster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.fbu_flixster.databinding.ActivityMovieDetailsBinding;
import com.example.fbu_flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Headers;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    public static final String TAG = "MovieDetailsActivity";
    private static final String videoURL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=" + MainActivity.API_KEY;
    public static final String YT_API_KEY = BuildConfig.YT_API;

    Movie movie;
    ActivityMovieDetailsBinding binding;
    ImageView ivPlayer;
    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    ImageButton ibPlay;
    String movieYoutubeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tvTitle = binding.tvTitle;
        tvOverview = binding.tvOverview;
        ratingBar = binding.rbVoteAverage;
        ivPlayer = binding.ivPlayer;
        ibPlay = binding.imageButton;

        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("MOVIE"));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(videoURL, movie.getMovieID()), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Headers headers, JSON json) {
                        Log.d(TAG, "OnSuccess!");
                        JSONObject object = json.jsonObject;;
                        JSONArray results  = null;
                        try {
                            results = object.getJSONArray("results");
                            JSONObject jsonObject = results.getJSONObject(0);
                            movieYoutubeKey = jsonObject.getString("key");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                        Log.d(TAG, "OnFailure!");
                        Log.d(TAG, throwable.getMessage());
                    }
                });

        Glide.with(this)
                .load(movie.getBackdropPath())
                .centerCrop()
                .transform(new BlurTransformation(60))
                .into(ivPlayer);
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                i.putExtra("KEY", movieYoutubeKey);
                MovieDetailsActivity.this.startActivity(i);
            }
        });
        Log.d(TAG, "Movie ID: " + movie.getMovieID());

        float rating = movie.getVoteAverage().floatValue();
        ratingBar.setRating(rating = rating > 0 ? rating / 2.0f : rating);

    }

}