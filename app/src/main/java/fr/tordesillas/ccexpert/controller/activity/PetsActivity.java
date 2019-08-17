package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Sets;
import fr.tordesillas.ccexpert.model.Pet;
import fr.tordesillas.ccexpert.view.PetsAdapter;

public class PetsActivity extends BaseActivity {
    private static List<Pet> pets;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        TextView title = findViewById(R.id.talentsTitle);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        title.setText(R.string.pets);

        if (pets == null) {
            pets = Sets.getInstance().getPets();
        }

        GridView gridTalents = findViewById(R.id.talentsGrid);
        gridTalents.setAdapter(new PetsAdapter(this, pets));

        gridTalents.setOnItemClickListener((adapterView, view, i, l) -> {
            Pet p = pets.get(i);
            try {
                Intent intent = new Intent(PetsActivity.this, PetActivity.class);
                intent.putExtra("petName", p.getFrenchName());
                startActivity(intent);
            } catch (NullPointerException ignored) { }
        });
    }
}
