package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.CrystalProcessor;

public class CrystalActivity extends BaseActivity {
    private CrystalProcessor cp;
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private TextView crystalAmount;
    private TextView manaAmount;
    private TextView lifeAmount;
    private TextView attackAmount;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal);

        ((TextView) findViewById(R.id.crystalTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        cp = new CrystalProcessor();
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(99);
        aimLvlPicker.setMaxValue(100);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        crystalAmount = findViewById(R.id.crystalAmount);
        manaAmount = findViewById(R.id.manaAmount);
        lifeAmount = findViewById(R.id.hpAmount);
        attackAmount = findViewById(R.id.attackAmount);

        updateNumbers();
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!CrystalActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(CrystalActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            crystalAmount.setText("0");
            manaAmount.setText("0");
            lifeAmount.setText("0");
            attackAmount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            crystalAmount.setText(cp.computeCrystal(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            manaAmount.setText(cp.computeMana(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            lifeAmount.setText(cp.computeLife(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            attackAmount.setText(cp.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
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
