package fr.unice.polytech.ccexpert.controller.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Collection;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Dungeon;
import fr.unice.polytech.ccexpert.model.Sets;

public class DungeonsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeons);

        ((TextView) findViewById(R.id.dungeonsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        final NumberPicker doorPicker = findViewById(R.id.door);
        final NumberPicker basePicker = findViewById(R.id.base);
        doorPicker.setMinValue(1);
        basePicker.setMinValue(1);
        doorPicker.setMaxValue(10);
        basePicker.setMaxValue(10);
        doorPicker.setWrapSelectorWheel(true);
        basePicker.setWrapSelectorWheel(true);

        findViewById(R.id.dungeonsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collection<Dungeon> dungeons = Sets.getInstance().getDungeonSet(doorPicker.getValue(), basePicker.getValue());
                switch (dungeons.size()) {
                    case 1:
                        Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                        Dungeon d = dungeons.iterator().next();
                        intent.putExtra("door", d.getDoor());
                        intent.putExtra("base", d.getBase());
                        intent.putExtra("f2p", d.isF2p());
                        startActivity(new Intent(DungeonsActivity.this, DungeonActivity.class));
                        break;
                    case 2:
                        createDialog(dungeons);
                        break;
                    default:
                        createErrorDialog();
                        break;
                }
            }
        });
    }

    private void createDialog(final Collection<Dungeon> dungeons) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Choix de donjon")
                .setMessage("Deux vidéos correspondent à votre demande, quelle version voulez-vous ?")
                .setNegativeButton("F2P", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                        Dungeon d = dungeons.iterator().next();
                        intent.putExtra("door", d.getDoor());
                        intent.putExtra("base", d.getBase());
                        intent.putExtra("f2p", true);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("P2W", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                        Dungeon d = dungeons.iterator().next();
                        intent.putExtra("door", d.getDoor());
                        intent.putExtra("base", d.getBase());
                        intent.putExtra("f2p", false);
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void createErrorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Choix de donjon").setMessage("Aucune vidéo n'a été trouvée.")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .show();
    }
}
