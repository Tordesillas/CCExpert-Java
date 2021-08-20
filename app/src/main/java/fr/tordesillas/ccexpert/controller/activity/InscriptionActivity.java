package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.InscriptionProcessor;

public class InscriptionActivity extends BaseActivity {
    private InscriptionProcessor ip;
    private TextView loseLevel;
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private TextView crystalAmount;
    private TextView manaAmount;
    private TextView diskAmount;
    private TextView codexAmount;
    private TextView lifeAmount;
    private TextView attackAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ((TextView) findViewById(R.id.crystalTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        ip = new InscriptionProcessor();
        loseLevel = findViewById(R.id.loseLevel);
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(199);
        aimLvlPicker.setMaxValue(200);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        crystalAmount = findViewById(R.id.crystalAmount);
        manaAmount = findViewById(R.id.manaAmount);
        diskAmount = findViewById(R.id.diskAmount);
        codexAmount = findViewById(R.id.codexAmount);
        lifeAmount = findViewById(R.id.hpAmount);
        attackAmount = findViewById(R.id.attackAmount);

        updateNumbers();
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            loseLevel.setVisibility(View.VISIBLE);
            crystalAmount.setText("0");
            manaAmount.setText("0");
            diskAmount.setText("0");
            codexAmount.setText("0");
            lifeAmount.setText("0");
            attackAmount.setText("0");
        } else {
            loseLevel.setVisibility(View.INVISIBLE);
            crystalAmount.setText(ip.computeCrystal(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            manaAmount.setText(ip.computeMana(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            diskAmount.setText(ip.computeDisk(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            codexAmount.setText(ip.computeCodex(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            lifeAmount.setText(ip.computeLife(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            attackAmount.setText(ip.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
        }
    }
}
