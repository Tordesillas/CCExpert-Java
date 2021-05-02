package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.InscriptionProcessor;

public class InscriptionActivity extends BaseActivity {
    private InscriptionProcessor ip;
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
        setContentView(R.layout.activity_inscription);

        ((TextView) findViewById(R.id.crystalTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        ip = new InscriptionProcessor();
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
            if (!InscriptionActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(InscriptionActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
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
            crystalAmount.setText(ip.computeCrystal(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            manaAmount.setText(ip.computeMana(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            lifeAmount.setText(ip.computeLife(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            attackAmount.setText(ip.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
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
