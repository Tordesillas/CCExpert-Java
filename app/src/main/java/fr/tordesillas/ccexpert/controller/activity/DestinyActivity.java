package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.DestinyProcessor;

public class DestinyActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private DestinyProcessor dp;
    private TextView goldAmount;
    private TextView ihAmount;
    private TextView crystalsAmount;
    private TextView heroCardsAmount;
    private TextView karmic1Amount;
    private TextView karmic2Amount;
    private TextView karmic3Amount;
    private TextView karmic4Amount;
    private TextView karmic5Amount;
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
        currentLvlPicker.setMaxValue(79);
        aimLvlPicker.setMaxValue(80);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        goldAmount = findViewById(R.id.goldAmount);
        ihAmount = findViewById(R.id.ihAmount);
        crystalsAmount = findViewById(R.id.crystalsAmount);
        heroCardsAmount = findViewById(R.id.heroCardsAmount);
        karmic1Amount = findViewById(R.id.karmic1Amount);
        karmic2Amount = findViewById(R.id.karmic2Amount);
        karmic3Amount = findViewById(R.id.karmic3Amount);
        karmic4Amount = findViewById(R.id.karmic4Amount);
        karmic5Amount = findViewById(R.id.karmic5Amount);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!DestinyActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(DestinyActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            goldAmount.setText("0");
            ihAmount.setText("0");
            crystalsAmount.setText("0");
            heroCardsAmount.setText("0");
            karmic1Amount.setText("0");
            karmic2Amount.setText("0");
            karmic3Amount.setText("0");
            karmic4Amount.setText("0");
            karmic5Amount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            int firstLevel = currentLvlPicker.getValue();
            int secondLevel = aimLvlPicker.getValue();
            goldAmount.setText(dp.computeGold(firstLevel, secondLevel));
            ihAmount.setText(dp.computeIH(firstLevel, secondLevel));
            crystalsAmount.setText(dp.computeCrystals(firstLevel, secondLevel));
            heroCardsAmount.setText(dp.computeHeroCards(firstLevel, secondLevel));
            karmic1Amount.setText(dp.computeKarmic1(firstLevel, secondLevel));
            karmic2Amount.setText(dp.computeKarmic2(firstLevel, secondLevel));
            karmic3Amount.setText(dp.computeKarmic3(firstLevel, secondLevel));
            karmic4Amount.setText(dp.computeKarmic4(firstLevel, secondLevel));
            karmic5Amount.setText(dp.computeKarmic5(firstLevel, secondLevel));
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
