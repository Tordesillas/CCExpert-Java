package fr.unice.polytech.ccexpert.controller.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.NumberPicker;

import fr.unice.polytech.ccexpert.R;

public class DungeonsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dungeons);

        NumberPicker doorPicker = findViewById(R.id.door);
        NumberPicker basePicker = findViewById(R.id.base);
        doorPicker.setMinValue(1);
        basePicker.setMinValue(1);
        doorPicker.setMaxValue(10);
        basePicker.setMaxValue(10);
        doorPicker.setWrapSelectorWheel(true);
        basePicker.setWrapSelectorWheel(true);

        findViewById(R.id.dungeonsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    private void createDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Choix de donjon")
                .setMessage("Deux vidéos correspondent à votre demande, quelle version voulez-vous ?")
                .setPositiveButton("P2W", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                })
                .setNegativeButton("F2P", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
