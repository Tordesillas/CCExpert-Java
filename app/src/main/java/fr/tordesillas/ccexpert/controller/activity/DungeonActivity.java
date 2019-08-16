package fr.tordesillas.ccexpert.controller.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import java.util.Collection;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Dungeon;
import fr.tordesillas.ccexpert.model.Sets;

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

        Collection<Dungeon> dungeons = Sets.getInstance().getDungeonSet(door, base);
        if (dungeons.size() == 1) {
            dungeon = dungeons.iterator().next();
        } else {
            for (Dungeon d : dungeons) {
                if (d.isF2p() == f2p) {
                    dungeon = d;
                    break;
                }
            }
        }

        final String urlYoutube = dungeon != null ? dungeon.getUrlYoutube() : "Z5FPjcbUaKE";

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

                    initializedYouTubePlayer.loadVideo(urlYoutube, 10);

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