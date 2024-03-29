package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.SkinsProcessor;

public class SkinsActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private SkinsProcessor sp;
    private TextView loseLevel;
    private TextView shardAmount;
    private TextView scrapAmount;
    private TextView attackAmount;
    private TextView hpAmount;
    private TextView dodgeAmount;
    private TextView accuracyAmount;
    private TextView tenacityAmount;
    private TextView critAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);

        ((TextView) findViewById(R.id.skinsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        sp = new SkinsProcessor();

        loseLevel = findViewById(R.id.loseLevel);
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(29);
        aimLvlPicker.setMaxValue(30);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        shardAmount = findViewById(R.id.shardAmount);
        scrapAmount = findViewById(R.id.scrapAmount);
        attackAmount = findViewById(R.id.attackAmount);
        hpAmount = findViewById(R.id.hpAmount);
        dodgeAmount = findViewById(R.id.dodgeAmount);
        accuracyAmount = findViewById(R.id.accuracyAmount);
        tenacityAmount = findViewById(R.id.tenacityAmount);
        critAmount = findViewById(R.id.critAmount);

        updateNumbers();

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            loseLevel.setVisibility(View.VISIBLE);
            shardAmount.setText("0");
            scrapAmount.setText("0");
            attackAmount.setText("0");
            hpAmount.setText("0");
            dodgeAmount.setText("0");
            accuracyAmount.setText("0");
            tenacityAmount.setText("0");
            critAmount.setText("0");
        } else {
            loseLevel.setVisibility(View.INVISIBLE);
            int firstLevel = currentLvlPicker.getValue();
            int secondLevel = aimLvlPicker.getValue();
            shardAmount.setText(sp.computeShard(firstLevel, secondLevel));
            scrapAmount.setText(sp.computeScrap(firstLevel, secondLevel));
            attackAmount.setText(sp.computeAttack(firstLevel, secondLevel));
            hpAmount.setText(sp.computeHP(firstLevel, secondLevel));
            dodgeAmount.setText(sp.computeDodge(firstLevel, secondLevel));
            accuracyAmount.setText(sp.computeAccuracy(firstLevel, secondLevel));
            tenacityAmount.setText(sp.computeTenacity(firstLevel, secondLevel));
            critAmount.setText(sp.computeCrit(firstLevel, secondLevel));
        }
    }
}
