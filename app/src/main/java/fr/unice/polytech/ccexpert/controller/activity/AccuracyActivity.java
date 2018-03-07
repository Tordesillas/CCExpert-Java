package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.AccuracyProcessor;

public class AccuracyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accuracy);

        ((TextView) findViewById(R.id.accuracyTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        final EditText accuracyField = findViewById(R.id.accuracyField);
        final EditText accuracyEquipField = findViewById(R.id.accuracyEquipField);
        final AccuracyProcessor ap = new AccuracyProcessor(getResources());

        ((ImageView) findViewById(R.id.artefactPicture)).setImageResource(R.drawable.eye_of_garuda);

        final Switch switchArtefact = findViewById(R.id.artefactSwitch);

        findViewById(R.id.accuracyButton).setOnClickListener(v -> {
            if (accuracyField.getText().length() <= 1) {
                Toast.makeText(AccuracyActivity.this, getResources().getString(R.string.missingData), Toast.LENGTH_SHORT).show();
            } else {
                int equip = 0;
                if (accuracyEquipField.getText().length() >= 1) {
                    equip = Integer.parseInt(accuracyEquipField.getText().toString());
                }
                createSimulatorDialog(ap.printAccuracyAmount(
                        Integer.parseInt(accuracyField.getText().toString()),
                        switchArtefact.isChecked(),
                        equip
                ));
                accuracyField.setText("");
                accuracyEquipField.setText("");
            }
        });
    }
}
