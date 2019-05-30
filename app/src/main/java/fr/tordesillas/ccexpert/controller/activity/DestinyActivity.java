package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.DestinyProcessor;

public class DestinyActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private DestinyProcessor dp;
    private TextView fameAmount;
    private TextView goldAmount;
    private TextView ihAmount;
    private TextView heroCardsAmount;
    private TextView crystalsAmount;
    private TextView karmic4Amount;
    private Toast toast;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destiny);

        ((TextView) findViewById(R.id.shardTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        dp = new DestinyProcessor();

        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(1);
        aimLvlPicker.setMinValue(2);
        currentLvlPicker.setMaxValue(59);
        aimLvlPicker.setMaxValue(60);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        fameAmount = findViewById(R.id.fameAmount);
        goldAmount = findViewById(R.id.goldAmount);
        ihAmount = findViewById(R.id.ihAmount);
        heroCardsAmount = findViewById(R.id.heroCardsAmount);
        crystalsAmount = findViewById(R.id.crystalsAmount);
        karmic4Amount = findViewById(R.id.karmic4Amount);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!DestinyActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(DestinyActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            fameAmount.setText("0");
            goldAmount.setText("0");
            ihAmount.setText("0");
            heroCardsAmount.setText("0");
            crystalsAmount.setText("0");
            karmic4Amount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            int firstLevel = currentLvlPicker.getValue();
            int secondLevel = aimLvlPicker.getValue();
            fameAmount.setText(dp.computeFame(firstLevel, secondLevel));
            goldAmount.setText(dp.computeGold(firstLevel, secondLevel));
            ihAmount.setText(dp.computeIH(firstLevel, secondLevel));
            heroCardsAmount.setText(dp.computeHeroCards(firstLevel, secondLevel));
            crystalsAmount.setText(dp.computeCrystals(firstLevel, secondLevel));
            karmic4Amount.setText(dp.computeKarmic4(firstLevel, secondLevel));
        }
    }

    @Override
    protected void onStop() {
        if (toast != null) {
            toast.cancel();
        }
        super.onStop();
    }
}
