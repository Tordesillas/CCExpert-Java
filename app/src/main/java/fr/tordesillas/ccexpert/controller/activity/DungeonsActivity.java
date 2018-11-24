package fr.tordesillas.ccexpert.controller.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Collection;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Dungeon;
import fr.tordesillas.ccexpert.model.Sets;

public class DungeonsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeons);

        ((TextView) findViewById(R.id.dungeonsTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        NumberPicker doorPicker = findViewById(R.id.door);
        NumberPicker basePicker = findViewById(R.id.base);
        doorPicker.setMinValue(1);
        basePicker.setMinValue(1);
        doorPicker.setMaxValue(8);
        basePicker.setMaxValue(10);
        doorPicker.setWrapSelectorWheel(true);
        basePicker.setWrapSelectorWheel(true);

        findViewById(R.id.dungeonsButton).setOnClickListener(v -> {
            Collection<Dungeon> dungeons = Sets.getInstance().getDungeonSet(doorPicker.getValue(), basePicker.getValue());
            switch (dungeons.size()) {
                case 1:
                    createDialogNoChoice(dungeons.iterator().next());
                    break;
                case 2:
                    createDialogWithChoices(dungeons);
                    break;
                default:
                    createErrorDialog();
                    break;
            }
        });

        ImageView dungeonDoor = findViewById(R.id.dungeonDoor);
        dungeonDoor.setImageResource(R.drawable.dungeon1);
        doorPicker.setOnValueChangedListener((numberPicker, i, i1) ->
                dungeonDoor.setImageResource(getResources().getIdentifier("dungeon" + i1, "drawable", getPackageName())));
    }

    private void createDialogWithChoices(final Collection<Dungeon> dungeons) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.dungeonChoice))
                .setMessage(getResources().getString(R.string.twoVideos))
                .setNegativeButton("F2P", (dialog, which) -> {
                    Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                    Dungeon d = dungeons.iterator().next();
                    intent.putExtra("door", d.getDoor());
                    intent.putExtra("base", d.getBase());
                    intent.putExtra("f2p", true);
                    startActivity(intent);
                })
                .setPositiveButton("P2W", (arg0, arg1) -> {
                    Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                    Dungeon d = dungeons.iterator().next();
                    intent.putExtra("door", d.getDoor());
                    intent.putExtra("base", d.getBase());
                    intent.putExtra("f2p", false);
                    startActivity(intent);
                })
                .show();
    }

    private void createDialogNoChoice(Dungeon dungeon) {
        String textDialog = (dungeon.isF2p()) ? getResources().getString(R.string.onlyF2P) : getResources().getString(R.string.onlyP2W);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.dungeonChoice))
                .setMessage(textDialog)
                .setNegativeButton(getResources().getString(R.string.dungeonNegativeButton), (dialog, which) -> {})
                .setPositiveButton(getResources().getString(R.string.dungeonPositiveButton), (arg0, arg1) -> {
                    Intent intent = new Intent(DungeonsActivity.this, DungeonActivity.class);
                    intent.putExtra("door", dungeon.getDoor());
                    intent.putExtra("base", dungeon.getBase());
                    intent.putExtra("f2p", dungeon.isF2p());
                    startActivity(intent);
                })
                .show();
    }

    private void createErrorDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.dungeonChoice)).setMessage(getResources().getString(R.string.noVideo))
                .setNegativeButton("OK", (dialog, which) -> {})
                .show();
    }
}
