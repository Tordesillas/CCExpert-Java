package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.NoSuchElementException;

import fr.tordesillas.ccexpert.CCExpertMain;
import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Insignia;
import fr.tordesillas.ccexpert.model.Sets;

public class InsigniaActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent);

        try {
            Insignia insignia = Sets.getInstance().getInsignia(getIntent().getStringExtra("insigniaName"));
            TextView title = findViewById(R.id.talentName);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
            title.setText(insignia.getName());

            ((ImageView) findViewById(R.id.talentPicture)).setImageResource(getResources().getIdentifier(insignia.getInsigniaResource(), "drawable", getPackageName()));
            TextView des = findViewById(R.id.talentDescription);

            com.shawnlin.numberpicker.NumberPicker picker = findViewById(R.id.pickerLevel);
            if (insignia.getDescription()[0] == null) {
                picker.setMinValue(3);
                picker.setValue(3);
                des.setText(insignia.getDescription()[2]);
            } else {
                picker.setMinValue(1);
                picker.setValue(1);
                des.setText(insignia.getDescription()[0]);
            }
            if (insignia.getDescription()[7] == null) {
                picker.setMaxValue(7);
            } else {
                picker.setMaxValue(10);
            }

            picker.setOnValueChangedListener((numPicker, oldVal, newVal) -> des.setText(insignia.getDescription()[newVal-1]));
        }
        catch (NullPointerException | NoSuchElementException e) {
            Toast.makeText(InsigniaActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            Intent closeActivitiesIntent = new Intent(this, CCExpertMain.class);
            closeActivitiesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(closeActivitiesIntent);
        }
    }
}
