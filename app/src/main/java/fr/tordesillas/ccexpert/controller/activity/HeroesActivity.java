package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Hero;
import fr.tordesillas.ccexpert.model.Sets;
import fr.tordesillas.ccexpert.view.HeroesAdapter;

public class HeroesActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);

        ((TextView) findViewById(R.id.heroesTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        final boolean[] sortedByName = {true};
        final List[] heroes = new List[]{
                Sets.getInstance().getHeroSorted(true),
                Sets.getInstance().getHeroSorted(false)
        };

        final HeroesAdapter adapterByAlphabet = new HeroesAdapter(this, heroes[0]);
        final HeroesAdapter adapterByOrder = new HeroesAdapter(this, heroes[1]);
        final GridView gridView = findViewById(R.id.heroesGrid);
        gridView.setAdapter(adapterByAlphabet);

        final FloatingActionButton sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(v -> {
            if (sortedByName[0]) {
                sortButton.setImageResource(R.drawable.ic_sort_by_order);
                sortedByName[0] = false;
                gridView.setAdapter(adapterByOrder);
            } else {
                sortButton.setImageResource(R.drawable.ic_sort_by_alphabet);
                sortedByName[0] = true;
                gridView.setAdapter(adapterByAlphabet);
            }
        });

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Hero hero;
            if (sortedByName[0]) {
                hero = (Hero) heroes[0].get(position);
            } else {
                hero = (Hero) heroes[1].get(position);
            }

            try {
                hero.getPetPicture();
                Intent intent = new Intent(HeroesActivity.this, HeroActivity.class);
                intent.putExtra("heroName", hero.getFrenchName());
                startActivity(intent);
            } catch (NullPointerException e) {
                Toast.makeText(HeroesActivity.this, getResources().getString(R.string.propNotAvailable), Toast.LENGTH_SHORT).show();
            }
        });
    }
}