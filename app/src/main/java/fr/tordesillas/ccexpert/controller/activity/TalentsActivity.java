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
import fr.tordesillas.ccexpert.model.Talent;
import fr.tordesillas.ccexpert.view.TalentsAdapter;

public class TalentsActivity extends BaseActivity {
    private static List<Talent> talents;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        TextView title = findViewById(R.id.talentsTitle);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        title.setText(R.string.talents);

        if (talents == null) {
            talents = Sets.getInstance().getTalents();
        }

        GridView gridTalents = findViewById(R.id.talentsGrid);
        gridTalents.setAdapter(new TalentsAdapter(this, talents));

        gridTalents.setOnItemClickListener((adapterView, view, i, l) -> {
            Talent t = talents.get(i);
            try {
                Intent intent = new Intent(TalentsActivity.this, TalentActivity.class);
                intent.putExtra("talentName", t.getFrenchName());
                startActivity(intent);
            } catch (NullPointerException ignored) { }
        });
    }
}
