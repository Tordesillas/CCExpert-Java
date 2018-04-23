package fr.unice.polytech.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.DestinyProcessor;

public class DestinyActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private DestinyProcessor dp;
    private TextView fameAmount;
    private TextView goldAmount;
    private TextView ihAmount;
    private TextView heroCardsAmount;
    private TextView crystalsAmount;
    private Toast toast;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny);

        ((TextView) findViewById(R.id.shardTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        toast = Toast.makeText(DestinyActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
        dp = new DestinyProcessor();

        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(1);
        aimLvlPicker.setMinValue(2);
        currentLvlPicker.setMaxValue(39);
        aimLvlPicker.setMaxValue(40);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        fameAmount = findViewById(R.id.fameAmount);
        goldAmount = findViewById(R.id.goldAmount);
        ihAmount = findViewById(R.id.ihAmount);
        heroCardsAmount = findViewById(R.id.heroCardsAmount);
        crystalsAmount = findViewById(R.id.crystalsAmount);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            toast.show();
            fameAmount.setText("0");
            goldAmount.setText("0");
            ihAmount.setText("0");
            heroCardsAmount.setText("0");
            crystalsAmount.setText("0");
        } else {
            int firstLevel = currentLvlPicker.getValue();
            int secondLevel = aimLvlPicker.getValue();
            fameAmount.setText(dp.computeFame(firstLevel, secondLevel));
            goldAmount.setText(dp.computeGold(firstLevel, secondLevel));
            ihAmount.setText(dp.computeIH(firstLevel, secondLevel));
            heroCardsAmount.setText(dp.computeHeroCards(firstLevel, secondLevel));
            crystalsAmount.setText(dp.computeCrystals(firstLevel, secondLevel));
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        toast.cancel();
    }
}
