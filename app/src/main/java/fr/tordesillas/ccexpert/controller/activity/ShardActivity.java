package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.ShardProcessor;

public class ShardActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private Spinner heroType;
    private TextView shardAmount;
    private TextView expAmount;
    private TextView sacrifice1Amount;
    private TextView sacrifice2Amount;
    private TextView sacrifice3Amount;
    private TextView sacrifice4Amount;
    private ShardProcessor sp;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shard);

        ((TextView) findViewById(R.id.shardTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        sp = new ShardProcessor(getBaseContext());
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(1);
        aimLvlPicker.setMinValue(2);
        currentLvlPicker.setMaxValue(9);
        aimLvlPicker.setMaxValue(10);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        heroType = findViewById(R.id.heroTypeSpinner);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        heroType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                updateNumbers();
                try {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
                } catch (Exception ignored) {}
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        expAmount = findViewById(R.id.expAmount);
        shardAmount = findViewById(R.id.shardAmount);
        sacrifice1Amount = findViewById(R.id.s1Amount);
        sacrifice2Amount = findViewById(R.id.s2Amount);
        sacrifice3Amount = findViewById(R.id.s3Amount);
        sacrifice4Amount = findViewById(R.id.s4Amount);

        updateNumbers();
    }

    @SuppressLint("SetTextI18n")
    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!ShardActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(ShardActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            expAmount.setText("0");
            shardAmount.setText("0");
            sacrifice1Amount.setText("0");
            sacrifice2Amount.setText("0");
            sacrifice3Amount.setText("0");
            sacrifice4Amount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            expAmount.setText(sp.computeExp(currentLvlPicker.getValue(), aimLvlPicker.getValue(), heroType.getSelectedItem().toString()));
            shardAmount.setText(sp.computeShard(currentLvlPicker.getValue(), aimLvlPicker.getValue(), heroType.getSelectedItem().toString()));
            int[] sacrifices = sp.computeSacrifices(currentLvlPicker.getValue(), aimLvlPicker.getValue(), heroType.getSelectedItem().toString());
            sacrifice1Amount.setText(sacrifices[0] + "");
            sacrifice2Amount.setText(sacrifices[1] + "");
            sacrifice3Amount.setText(sacrifices[2] + "");
            sacrifice4Amount.setText(sacrifices[3] + "");
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
