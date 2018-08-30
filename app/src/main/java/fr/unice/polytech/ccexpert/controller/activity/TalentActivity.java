package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.model.Talent;

public class TalentActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent);

        Talent talent = Sets.getInstance().getTalent(getIntent().getStringExtra("talentName"));

        TextView title = findViewById(R.id.talentName);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                title.setText(talent.getFrenchName());
                break;
            default:
                title.setText(talent.getName());
        }

        ((ImageView) findViewById(R.id.talentPicture)).setImageResource(getResources().getIdentifier(talent.getTalentResource(), "drawable", getPackageName()));
        TextView des = findViewById(R.id.talentDescription);

        com.shawnlin.numberpicker.NumberPicker picker = findViewById(R.id.pickerLevel);
        if (talent.getDescription()[0] == null) {
            picker.setMinValue(3);
            picker.setValue(3);
            des.setText(talent.getDescription()[2]);
        } else {
            picker.setMinValue(1);
            picker.setValue(1);
            des.setText(talent.getDescription()[0]);
        }
        if (talent.getDescription()[7] == null) {
            picker.setMaxValue(5);
        } else {
            picker.setMaxValue(8);
        }

        picker.setOnValueChangedListener((numPicker, oldVal, newVal) -> des.setText(talent.getDescription()[newVal-1]));
    }
}
