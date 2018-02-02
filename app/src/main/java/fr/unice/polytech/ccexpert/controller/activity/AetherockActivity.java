package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.AetherockProcessor;

public class AetherockActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aetherock);

        ((TextView) findViewById(R.id.aetherockTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        final AetherockProcessor ap = new AetherockProcessor();
        final NumberPicker currentLvlPicker = findViewById(R.id.currentLvl);
        final NumberPicker aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(19);
        aimLvlPicker.setMaxValue(20);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        findViewById(R.id.aetherockButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
                    Toast.makeText(AetherockActivity.this, "Le héros ne peut pas perdre de niveau.", Toast.LENGTH_SHORT).show();
                } else {
                    createSimulatorDialog(ap.printAetherockAmount(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
                }
            }
        });
    }
}
