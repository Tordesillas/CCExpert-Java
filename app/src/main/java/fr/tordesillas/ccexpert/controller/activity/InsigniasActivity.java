package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Insignia;
import fr.tordesillas.ccexpert.model.Sets;
import fr.tordesillas.ccexpert.view.InsigniasAdapter;

public class InsigniasActivity extends BaseActivity {
    private static List<Insignia> insignias;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        TextView title = findViewById(R.id.talentsTitle);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        title.setText(R.string.insignias);

        if (insignias == null) {
            insignias = Sets.getInstance().getInsignias();
        }

        GridView gridTalents = findViewById(R.id.talentsGrid);
        gridTalents.setAdapter(new InsigniasAdapter(this, insignias));

        gridTalents.setOnItemClickListener((adapterView, view, i, l) -> {
            Insignia insignia = insignias.get(i);
            try {
                Intent intent = new Intent(InsigniasActivity.this, InsigniaActivity.class);
                intent.putExtra("insigniaName", insignia.getFrenchName());
                startActivity(intent);
            } catch (NullPointerException ignored) { }
        });
    }
}
