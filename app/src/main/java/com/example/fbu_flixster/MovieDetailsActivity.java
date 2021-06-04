package com.example.fbu_flixster;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fbu_flixster.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String TAG = "MovieDetailsActivity";

    Movie movie;

    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("MOVIE"));
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);

        Log.d(TAG, "Movie details for: " + movie.getTitle());
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float rating = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(rating = rating > 0 ? rating / 2.0f : rating);

    }
}