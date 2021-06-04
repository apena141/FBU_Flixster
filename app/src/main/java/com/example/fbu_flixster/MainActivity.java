package com.example.fbu_flixster;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";
    private static final String API_KEY = "b4f45155b698ba40d8e9ce3bdcd4ebee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL + API_KEY, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "OnSuccess");
                JSONObject object = json.jsonObject;
                try {
                    JSONArray results = object.getJSONArray("results");
                    Log.i(TAG, "Results: " + results.toString());
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