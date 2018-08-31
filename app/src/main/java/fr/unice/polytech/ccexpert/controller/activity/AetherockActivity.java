package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.AetherockProcessor;

public class AetherockActivity extends BaseActivity {
    private AetherockProcessor ap;
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private TextView aetherockAmount;
    private TextView lifeAmount;
    private TextView attackAmount;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aetherock);

        ((TextView) findViewById(R.id.aetherockTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        ap = new AetherockProcessor();
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(19);
        aimLvlPicker.setMaxValue(20);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        aetherockAmount = findViewById(R.id.aetherockAmount);
        lifeAmount = findViewById(R.id.hpAmount);
        attackAmount = findViewById(R.id.attackAmount);

        updateNumbers();
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!AetherockActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(AetherockActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            aetherockAmount.setText("0");
            lifeAmount.setText("0");
            attackAmount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            aetherockAmount.setText(ap.computeAetherock(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            lifeAmount.setText(ap.computeHealth(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            attackAmount.setText(ap.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
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
