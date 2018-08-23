package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.DodgeProcessor;

public class DodgeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodge);

        ((TextView) findViewById(R.id.dodgeTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        final EditText dodgeField = findViewById(R.id.dodgeField);
        final EditText dodgeEquipField = findViewById(R.id.dodgeEquipField);
        final DodgeProcessor dp = new DodgeProcessor(getResources());

        ((ImageView) findViewById(R.id.talentPicture)).setImageResource(R.drawable.scorch);

        final SeekBar talentBar = findViewById(R.id.talentProgressBar);
        final LinearLayout barLayout = findViewById(R.id.layoutTalentBar);
        barLayout.setVisibility(View.GONE);

        final Switch switchTalent = findViewById(R.id.talentSwitch);
        switchTalent.setOnCheckedChangeListener((buttonView, isChecked) -> {
           if (isChecked) {
               barLayout.setVisibility(View.VISIBLE);
           } else {
               barLayout.setVisibility(View.GONE);
           }
        });
        final TextView talentLvlText = findViewById(R.id.talentLvlText);
        talentLvlText.setText(getResources().getString(R.string.talentLevel) + "0/8");
        talentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                talentLvlText.setText(getResources().getString(R.string.talentLevel) + progress + "/8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.dodgeButton).setOnClickListener(v -> {
            if (dodgeField.getText().length() <= 1) {
                Toast.makeText(DodgeActivity.this, getResources().getString(R.string.missingData), Toast.LENGTH_SHORT).show();
            } else {
                int equip = 0;
                if (dodgeEquipField.getText().length() >= 1) {
                    equip = Integer.parseInt(dodgeEquipField.getText().toString());
                }
                createSimulatorDialog(dp.printDodgeAmount(
                        Integer.parseInt(dodgeField.getText().toString()),
                        (switchTalent.isChecked()) ? talentBar.getProgress() : 0,
                        equip
                ));
                dodgeField.setText("");
                dodgeEquipField.setText("");
            }
        });
    }
}
