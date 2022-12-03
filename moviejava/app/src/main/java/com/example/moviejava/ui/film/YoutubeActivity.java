package com.example.moviejava.ui.film;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.moviejava.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity {
    private static final String key = "AIzaSyAUkXpB5cX0hK6Hmc3Dl_aSZBCpSLlPACc";
    private YouTubePlayer.OnInitializedListener listener;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        YouTubePlayerView youTubePlayerView =(YouTubePlayerView) findViewById(R.id.youtube_player);
        Bundle bundle= getIntent().getExtras();
        String k= bundle.getString("video_key");
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (k != null) {
                    youTubePlayer.loadVideo(k);
                    youTubePlayer.play();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.e("rrr",youTubeInitializationResult.toString());
            }
        };
        youTubePlayerView.initialize(key, listener);

    }
}