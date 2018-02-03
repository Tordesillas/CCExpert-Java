package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.ShardProcessor;

public class ShardActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shard);

        ((TextView) findViewById(R.id.shardTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        final ShardProcessor sp = new ShardProcessor(getResources());
        final NumberPicker currentLvlPicker = findViewById(R.id.currentLvl);
        final NumberPicker aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(1);
        aimLvlPicker.setMinValue(2);
        currentLvlPicker.setMaxValue(9);
        aimLvlPicker.setMaxValue(10);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        final Spinner heroType = findViewById(R.id.heroTypeSpinner);

        findViewById(R.id.shardButton).setOnClickListener(v -> {
            if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
                Toast.makeText(ShardActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT).show();
            } else {
                createSimulatorDialog(sp.printShardAmount(currentLvlPicker.getValue(), aimLvlPicker.getValue(), heroType.getSelectedItem().toString()));
            }
        });

        heroType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                try {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
                } catch (Exception ignored) {}
            }
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
