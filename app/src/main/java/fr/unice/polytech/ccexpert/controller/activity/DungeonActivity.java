package fr.unice.polytech.ccexpert.controller.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Dungeon;
import fr.unice.polytech.ccexpert.model.Sets;

public class DungeonActivity extends Activity {
    private YouTubePlayerView youTubePlayerView;
    private @Nullable YouTubePlayer initializedYouTubePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        int door = getIntent().getIntExtra("door", 1);
        int base = getIntent().getIntExtra("base", 1);
        boolean f2p = getIntent().getBooleanExtra("f2p", false);
        Dungeon dungeon = null;

        for (Dungeon d : Sets.getInstance().getDungeonSet(door, base)) {
            if (d.isF2p() == f2p) {
                dungeon = d;
                break;
            }
        }
        final String urlYoutube = dungeon != null ? dungeon.getUrlYoutube() : "https://www.youtube.com/watch?v=Z5FPjcbUaKE&index=41&list=PL9fQGS1-vUVSvQ7VI_fx06MiWRmU4LJnq";

        String title = getResources().getString(R.string.dungeon) + " " + door + " / " + base;
        TextView dungeonName = findViewById(R.id.dungeonName);
        dungeonName.setText(title);
        dungeonName.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(initializedYouTubePlayer -> {
            initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    DungeonActivity.this.initializedYouTubePlayer = initializedYouTubePlayer;

                    String videoId = urlYoutube.split("v=")[1].split("&list")[0];
                    initializedYouTubePlayer.loadVideo("nHeNMXCDan0", 10);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().getDecorView().setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                }
            });
        }, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (initializedYouTubePlayer != null)
            initializedYouTubePlayer.pause();
    }
}