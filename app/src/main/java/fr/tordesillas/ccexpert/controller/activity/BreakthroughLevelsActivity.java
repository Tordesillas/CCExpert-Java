package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.BreakthroughLevelsProcessor;

public class BreakthroughLevelsActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private BreakthroughLevelsProcessor blp;
    private TextView ignitingStoneAmount;
    private TextView zenithStoneAmount;
    private TextView apexCrystalAmount;
    private TextView capstoneRubyAmount;
    private TextView ihAmount;
    private TextView crystalAmount;
    private TextView expAmount;
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
        setContentView(R.layout.activity_breakthrough_levels);

        ((TextView) findViewById(R.id.breakthroughLevelsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        blp = new BreakthroughLevelsProcessor();

        String[] levels = generateLevelsArray();
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setDisplayedValues(Arrays.copyOf(levels, levels.length-1));
        aimLvlPicker.setDisplayedValues(Arrays.copyOfRange(levels, 1, levels.length));
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(levels.length - 2);
        aimLvlPicker.setMaxValue(levels.length - 1);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        ignitingStoneAmount = findViewById(R.id.ignitingStoneAmount);
        zenithStoneAmount = findViewById(R.id.zenithStoneAmount);
        apexCrystalAmount = findViewById(R.id.apexCrystalAmount);
        capstoneRubyAmount = findViewById(R.id.capstoneRubyAmount);
        ihAmount = findViewById(R.id.ihAmount);
        crystalAmount = findViewById(R.id.crystalAmount);
        expAmount = findViewById(R.id.expAmount);
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
                toast = Toast.makeText(BreakthroughLevelsActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            toast.show();
            ignitingStoneAmount.setText("0");
            zenithStoneAmount.setText("0");
            apexCrystalAmount.setText("0");
            capstoneRubyAmount.setText("0");
            ihAmount.setText("0");
            crystalAmount.setText("0");
            expAmount.setText("0");
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
            ignitingStoneAmount.setText(blp.computeIgnitingStone(firstLevel, secondLevel));
            zenithStoneAmount.setText(blp.computeZenithStone(firstLevel, secondLevel));
            apexCrystalAmount.setText(blp.computeApexCrystal(firstLevel, secondLevel));
            capstoneRubyAmount.setText(blp.computeCapstoneRuby(firstLevel, secondLevel));
            ihAmount.setText(blp.computeIH(firstLevel, secondLevel));
            crystalAmount.setText(blp.computeCrystal(firstLevel, secondLevel));
            expAmount.setText(blp.computeExp(firstLevel, secondLevel));
            attackAmount.setText(blp.computeAttack(firstLevel, secondLevel));
            hpAmount.setText(blp.computeHP(firstLevel, secondLevel));
            dodgeAmount.setText(blp.computeDodge(firstLevel, secondLevel));
            accuracyAmount.setText(blp.computeAccuracy(firstLevel, secondLevel));
            tenacityAmount.setText(blp.computeTenacity(firstLevel, secondLevel));
            critAmount.setText(blp.computeCrit(firstLevel, secondLevel));
        }
    }

    private String[] generateLevelsArray() {
        List<String> levels = new ArrayList<>();
        for (int i = 1; i <= 35; i++) {
            levels.add(i + "");
            if (i != 35) {
                for (int j = 1; j <= 5; j++) {
                    levels.add(i + "-" + j);
                }
            }
        }
        return levels.toArray(new String[0]);
    }

    @Override
    protected void onStop() {
        if (toast != null) {
            toast.cancel();
        }
        super.onStop();
    }
}
