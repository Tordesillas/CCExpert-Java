package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.view.HeroesAdapter;

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

        final HeroesAdapter adapterByName = new HeroesAdapter(this, heroes[0]);
        final HeroesAdapter adapterByAlphabet = new HeroesAdapter(this, heroes[1]);
        final GridView gridView = findViewById(R.id.heroesGrid);
        gridView.setAdapter(adapterByName);

        final FloatingActionButton sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sortedByName[0]) {
                    sortButton.setImageResource(R.drawable.ic_sort_by_order);
                    sortedByName[0] = false;
                    gridView.setAdapter(adapterByAlphabet);
                } else {
                    sortButton.setImageResource(R.drawable.ic_sort_by_alphabet);
                    sortedByName[0] = true;
                    gridView.setAdapter(adapterByName);
                }
            }
        });
    }
}
