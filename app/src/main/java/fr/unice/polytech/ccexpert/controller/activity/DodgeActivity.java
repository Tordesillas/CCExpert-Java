package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
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
        final DodgeProcessor dp = new DodgeProcessor();

        ((ImageView) findViewById(R.id.talentPicture)).setImageResource(R.drawable.scorch);

        final SeekBar talentBar = findViewById(R.id.talentProgressBar);
        final LinearLayout barLayout = findViewById(R.id.layoutTalentBar);
        barLayout.setVisibility(View.GONE);

        final Switch switchTalent = findViewById(R.id.talentSwitch);
        switchTalent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   barLayout.setVisibility(View.VISIBLE);
               } else {
                   barLayout.setVisibility(View.GONE);
               }
            }
        });
        final TextView talentLvlText = findViewById(R.id.talentLvlText);
        talentLvlText.setText("Niveau du talent : 0/8");
        talentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                talentLvlText.setText("Niveau du talent : " + progress + "/8");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        ((ImageView) findViewById(R.id.artefactPicture)).setImageResource(R.drawable.pierre_de_foudre);

        final Switch switchArtefact = findViewById(R.id.artefactSwitch);

        findViewById(R.id.dodgeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dodgeField.getText().length() <= 1) {
                    Toast.makeText(DodgeActivity.this, "Des données sont manquantes.", Toast.LENGTH_SHORT).show();
                } else {
                    int equip = 0;
                    if (dodgeEquipField.getText().length() >= 1) {
                        equip = Integer.parseInt(dodgeEquipField.getText().toString());
                    }
                    createSimulatorDialog(dp.printDodgeAmount(
                            Integer.parseInt(dodgeField.getText().toString()),
                            (switchTalent.isChecked()) ? talentBar.getProgress() : 0,
                            switchArtefact.isChecked(),
                            equip
                    ));
                }
            }
        });
    }
}
