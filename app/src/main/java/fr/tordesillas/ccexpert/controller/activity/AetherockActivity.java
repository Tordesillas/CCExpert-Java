package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.AetherockProcessor;

public class AetherockActivity extends BaseActivity {
    private AetherockProcessor ap;
    private TextView loseLevel;
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private TextView aetherockAmount;
    private TextView equipmentTomeAmount;
    private TextView lifeAmount;
    private TextView attackAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aetherock);

        ((TextView) findViewById(R.id.aetherockTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        ap = new AetherockProcessor();
        loseLevel = findViewById(R.id.loseLevel);
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(59);
        aimLvlPicker.setMaxValue(60);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        aetherockAmount = findViewById(R.id.aetherockAmount);
        equipmentTomeAmount = findViewById(R.id.equipmentTomeAmount);
        lifeAmount = findViewById(R.id.hpAmount);
        attackAmount = findViewById(R.id.attackAmount);

        updateNumbers();
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            loseLevel.setVisibility(View.VISIBLE);
            aetherockAmount.setText("0");
            equipmentTomeAmount.setText("0");
            lifeAmount.setText("0");
            attackAmount.setText("0");
        } else {
            loseLevel.setVisibility(View.INVISIBLE);
            aetherockAmount.setText(ap.computeAetherock(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            equipmentTomeAmount.setText(ap.computeEquipmentTome(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            lifeAmount.setText(ap.computeHealth(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            attackAmount.setText(ap.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
        }
    }
}
