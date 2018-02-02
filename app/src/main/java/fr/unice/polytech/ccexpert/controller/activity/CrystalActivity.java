package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.CrystalProcessor;

public class CrystalActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal);

        ((TextView) findViewById(R.id.crystalTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        final CrystalProcessor cp = new CrystalProcessor();
        final NumberPicker currentLvlPicker = findViewById(R.id.currentLvl);
        final NumberPicker aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(99);
        aimLvlPicker.setMaxValue(100);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        findViewById(R.id.crystalButton).setOnClickListener(v -> {
            if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
                Toast.makeText(CrystalActivity.this, "Le héros ne peut pas perdre de niveau.", Toast.LENGTH_SHORT).show();
            } else {
                createSimulatorDialog(cp.printCrystalAmount(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            }
        });
    }
}
