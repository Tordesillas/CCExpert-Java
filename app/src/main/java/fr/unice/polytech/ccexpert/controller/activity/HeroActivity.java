package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Hero;
import fr.unice.polytech.ccexpert.model.Sets;

public class HeroActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Hero hero = Sets.getInstance().getHero(getIntent().getStringExtra("heroName"));

        TextView heroName = findViewById(R.id.heroTitle);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        heroName.setText(hero.getFrenchName());

        final boolean[] evo = {false};
        int resHero = getResources().getIdentifier(hero.getPicture() + "2", "drawable", getPackageName());
        int resHeroEvo = getResources().getIdentifier(hero.getPicture() + "3", "drawable", getPackageName());
        ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHero);

        findViewById(R.id.evoHero).setOnClickListener(view -> {
            if (evo[0]) {
                ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHero);
                evo[0] = false;
            } else {
                ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHeroEvo);
                evo[0] = true;
            }
        });
    }
}
