package fr.unice.polytech.ccexpert.controller.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Dungeon;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.view.ExpandedGridView;
import fr.unice.polytech.ccexpert.view.HeroCardAdapter;

public class DungeonActivity extends BaseActivity {
    private YouTubePlayerView youTubePlayerView;
    private @Nullable YouTubePlayer initializedYouTubePlayer;
    private FullScreenManager fullScreenManager;

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
        ((TextView) findViewById(R.id.dungeonName)).setText(title);

        fullScreenManager = new FullScreenManager(this);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        this.getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.initialize(initializedYouTubePlayer -> {
            initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady() {
                    DungeonActivity.this.initializedYouTubePlayer = initializedYouTubePlayer;

                    String videoId = urlYoutube.split("v=")[1].split("&list")[0];
                    initializedYouTubePlayer.loadVideo("nHeNMXCDan0", 10);
                }
            });
            addFullScreenListenerToPlayer(initializedYouTubePlayer);
        }, true);

        ((TextView) findViewById(R.id.textHeroes)).setText(R.string.heroesInDungeon);

        ListAdapter la = new HeroCardAdapter(this, Sets.getInstance().getHeroesFaculties(dungeon.getHeroesIds()));
        ExpandedGridView gridView = findViewById(R.id.listHeroes);
        gridView.setAdapter(la);
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

    @Override
    public void onBackPressed() {
        if (youTubePlayerView.isFullScreen())
            youTubePlayerView.exitFullScreen();
        else
            super.onBackPressed();
    }

    private void addFullScreenListenerToPlayer(final YouTubePlayer youTubePlayer) {
        youTubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                fullScreenManager.enterFullScreen();

                Drawable icon = ContextCompat.getDrawable(DungeonActivity.this, R.drawable.ic_pause_36dp);
                youTubePlayerView.getPlayerUIController().setCustomAction1(icon, view -> {
                    if(youTubePlayer != null) youTubePlayer.pause();
                });
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                fullScreenManager.exitFullScreen();

                youTubePlayerView.getPlayerUIController().showCustomAction1(false);
            }
        });
    }
}

class FullScreenManager {
    private Activity context;
    private View[] views;

    FullScreenManager(Activity context, View ... views) {
        this.context = context;
        this.views = views;
    }

    void enterFullScreen() {
        View decorView = context.getWindow().getDecorView();
        hideSystemUI(decorView);
        for (View view : views) {
            view.setVisibility(View.GONE);
            view.invalidate();
        }
    }

    void exitFullScreen() {
        View decorView = context.getWindow().getDecorView();
        showSystemUI(decorView);
        for(View view : views) {
            view.setVisibility(View.VISIBLE);
            view.invalidate();
        }
    }

    private void hideSystemUI(View mDecorView) {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void showSystemUI(View mDecorView) {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}