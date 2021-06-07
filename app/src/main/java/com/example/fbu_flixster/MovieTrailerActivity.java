package com.example.fbu_flixster;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fbu_flixster.databinding.ActivityMovieTrailerBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    YouTubePlayerView playerView;
    ActivityMovieTrailerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieTrailerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        playerView = binding.player;

        final String videoId = getIntent().getStringExtra("KEY");

        // initialize with API key stored in secrets.xml
        playerView.initialize(MovieDetailsActivity.YT_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Log.e("MovieTrailerActivity", "Error initializing YouTube player " + youTubeInitializationResult.toString());
            }
        });
    }
}