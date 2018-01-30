package fr.unice.polytech.ccexpert.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.pierfrancescosoffritti.youtubeplayer.player.*;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Dungeon;
import fr.unice.polytech.ccexpert.model.Sets;

public class DungeonActivity extends BaseActivity {
    private YouTubePlayerView youTubePlayerView;

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

        ((TextView) findViewById(R.id.dungeonName)).setText("Donjon " + door + " / " + base);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String videoId = urlYoutube.split("v=")[1].split("&list")[0];
                        initializedYouTubePlayer.loadVideo(videoId, 0);
                    }
                });
            }
        }, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}
