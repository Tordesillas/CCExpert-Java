package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.HeroRoll;
import fr.unice.polytech.ccexpert.model.Sets;

public class RollActivity extends BaseActivity {
    private TextView heroName;
    private ImageView heroPic;
    private ImageView rollBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        ((TextView) findViewById(R.id.rollTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        heroName = findViewById(R.id.heroName);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "igg.ttf"));

        heroPic = findViewById(R.id.heroPicture);
        rollBg = findViewById(R.id.roll_bg);

        findViewById(R.id.rollButton).setOnClickListener(view -> roll());
    }

    private void roll() {
        HeroRoll hero = Sets.getInstance().getHeroRoll();

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                heroName.setText(hero.getNameFr()); break;
            case "german":
            case "deutsch":
                heroName.setText(hero.getDeName()); break;
            default:
                heroName.setText(hero.getEnName());
        }

        String pic = hero.getPicture();
        switch (hero.getType()) {
            case 3:
                if (!"Gelatinous Champion".equals(hero.getEnName())) {
                    pic += "2";
                }
                rollBg.setImageResource(R.drawable.roll_bg_purple);
                break;
            case 2:
                rollBg.setImageResource(R.drawable.roll_bg_blue);
                break;
            case 1:
                rollBg.setImageResource(R.drawable.roll_bg_green);
                break;
        }
        heroPic.setImageResource(getResources().getIdentifier(pic, "drawable", getPackageName()));
    }
}
