package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Hero;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.model.StringToResource;

public class HeroActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Hero hero = Sets.getInstance().getHero(getIntent().getStringExtra("heroName"));

        TextView heroName = findViewById(R.id.heroTitle);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        heroName.setText(hero.getFrenchName());

        ((ImageView) findViewById(R.id.heroPic)).setImageResource(getResources().getIdentifier(hero.getPicture(), "drawable", getPackageName()));
    }
}
