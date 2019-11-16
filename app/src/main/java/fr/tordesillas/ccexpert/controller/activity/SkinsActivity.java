package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.SkinsProcessor;

public class SkinsActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private SkinsProcessor sp;
    private TextView shardAmount;
    private TextView scrapAmount;
    private TextView attackAmount;
    private TextView hpAmount;
    private TextView dodgeAmount;
    private TextView accuracyAmount;
    private TextView tenacityAmount;
    private TextView critAmount;
    private Toast toast;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);

        ((TextView) findViewById(R.id.skinsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        sp = new SkinsProcessor();

        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(19);
        aimLvlPicker.setMaxValue(20);
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
            if (toast == null || !toast.getView().isShown()) {
                toast = Toast.makeText(SkinsActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            toast.show();
            shardAmount.setText("0");
            scrapAmount.setText("0");
            attackAmount.setText("0");
            hpAmount.setText("0");
            dodgeAmount.setText("0");
            accuracyAmount.setText("0");
            tenacityAmount.setText("0");
            critAmount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
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

    @Override
    protected void onStop() {
        if (toast != null) {
            toast.cancel();
        }
        super.onStop();
    }
}
