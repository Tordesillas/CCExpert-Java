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
import fr.unice.polytech.ccexpert.controller.processor.AttackSpeedProcessor;

public class AttackSpeedActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attackspeed);

        ((TextView) findViewById(R.id.speedTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        final AttackSpeedProcessor asp = new AttackSpeedProcessor();
        final EditText speedField = findViewById(R.id.speedField);

        ((ImageView) findViewById(R.id.talentPicture)).setImageResource(R.drawable.berserk);

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

        ((ImageView) findViewById(R.id.blitzPicture)).setImageResource(R.drawable.blitz);
        final Switch blitzSwitch = findViewById(R.id.blitzSwitch);

        ((ImageView) findViewById(R.id.furyPicture)).setImageResource(R.drawable.fury);
        final Switch furySwitch = findViewById(R.id.furySwitch);
        final LinearLayout furyLayout = findViewById(R.id.layoutFuryBar);
        furyLayout.setVisibility(View.GONE);
        furySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    furyLayout.setVisibility(View.VISIBLE);
                } else {
                    furyLayout.setVisibility(View.GONE);
                }
            }
        });

        final TextView furyText = findViewById(R.id.furyText);
        furyText.setText("Niveau du talent : 0/5");
        final SeekBar furyBar = findViewById(R.id.furyProgressBar);
        furyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                furyText.setText("Niveau du talent : " + progress + "/5");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        ((ImageView) findViewById(R.id.dukePicture)).setImageResource(R.drawable.pumpkin_duke);
        final Switch dukeSwitch = findViewById(R.id.dukeSwitch);
        final LinearLayout dukeLayout = findViewById(R.id.layoutDukeBar);
        dukeLayout.setVisibility(View.GONE);
        dukeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dukeLayout.setVisibility(View.VISIBLE);
                } else {
                    dukeLayout.setVisibility(View.GONE);
                }
            }
        });

        final TextView dukeText = findViewById(R.id.dukeText);
        dukeText.setText("Niveau du pouvoir du duc : 0/10");
        final SeekBar dukeProgressBar = findViewById(R.id.dukeProgressBar);
        dukeProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dukeText.setText("Niveau du pouvoir du duc : " + progress + "/10");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        final TextView dukeProcText = findViewById(R.id.dukeProcText);
        dukeProcText.setText("Coups que le duc a effectué : 0/4");
        final SeekBar dukeProcBar = findViewById(R.id.dukeProcBar);
        dukeProcBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dukeProcText.setText("Coups que le duc a effectué : " + progress + "/4");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        findViewById(R.id.speedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (speedField.getText().length() <= 1) {
                    Toast.makeText(AttackSpeedActivity.this, "Des données sont manquantes.", Toast.LENGTH_SHORT).show();
                } else {
                    createSimulatorDialog(asp.printAttackSpeedAmount(
                            Integer.parseInt(speedField.getText().toString()),
                            (switchTalent.isChecked()) ? talentBar.getProgress() : 0,
                            blitzSwitch.isChecked(),
                            (furySwitch.isChecked()) ? furyBar.getProgress() : 0,
                            (dukeSwitch.isChecked()) ? dukeProgressBar.getProgress() : 0,
                            (dukeSwitch.isChecked()) ? dukeProcBar.getProgress() : 0,
                            5
                    ));
                    speedField.setText("");
                }
            }
        });
    }
}
