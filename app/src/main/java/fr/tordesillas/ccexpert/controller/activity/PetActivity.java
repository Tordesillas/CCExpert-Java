package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.NoSuchElementException;

import fr.tordesillas.ccexpert.CCExpertMain;
import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Sets;
import fr.tordesillas.ccexpert.model.Pet;

public class PetActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        try {
            Pet pet = Sets.getInstance().getPet(getIntent().getStringExtra("petName"));
            TextView title = findViewById(R.id.petName);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
            title.setText(pet.getName());

            ((ImageView) findViewById(R.id.petPicture)).setImageResource(getResources().getIdentifier(pet.getResource(), "drawable", getPackageName()));
            TextView des = findViewById(R.id.description);
            des.setText(pet.getDescription(0));

            ((com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.pickerLevel))
                    .setOnValueChangedListener(
                            (numPicker, oldVal, newVal) -> des.setText(pet.getDescription(newVal-1))
                    );

            String modes = pet.getModes();
            for (int i = 0; i < modes.length(); i++) {
                if ('0' == modes.charAt(i)) {
                    findViewById(getResources().getIdentifier("mode" + (i+1), "id", getPackageName())).setVisibility(View.INVISIBLE);
                }
            }
        }
        catch (NullPointerException | NoSuchElementException e) {
            Toast.makeText(PetActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            Intent closeActivitiesIntent = new Intent(this, CCExpertMain.class);
            closeActivitiesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(closeActivitiesIntent);
        }
    }
}
