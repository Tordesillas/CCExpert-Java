package fr.tordesillas.ccexpert.controller.activity;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.PetProcessor;

public class PetLevelActivity extends BaseActivity {
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private Switch isRare;
    private ImageView classicPet;
    private ImageView rarePet;
    private TextView shardAmount;
    private TextView expAmount;
    private TextView levelNeeded;
    private TextView sacrifice1Amount;
    private TextView sacrifice2Amount;
    private TextView sacrifice3Amount;
    private TextView sacrifice4Amount;
    private PetProcessor pp;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_level);

        ((TextView) findViewById(R.id.petTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        pp = new PetProcessor();

        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(1);
        aimLvlPicker.setMinValue(2);
        currentLvlPicker.setMaxValue(9);
        aimLvlPicker.setMaxValue(10);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        isRare = findViewById(R.id.switchPetType);
        isRare.setOnCheckedChangeListener((compoundButton, b) -> {
            changePetBackground();
            updateNumbers();
        });
        classicPet = findViewById(R.id.classic_pet);
        rarePet = findViewById(R.id.rare_pet);
        rarePet.setOnClickListener(view -> isRare.setChecked(true));
        classicPet.setOnClickListener(view -> isRare.setChecked(false));

        expAmount = findViewById(R.id.expAmount);
        shardAmount = findViewById(R.id.shardAmount);
        levelNeeded = findViewById(R.id.levelNeeded);
        sacrifice1Amount = findViewById(R.id.s1Amount);
        sacrifice2Amount = findViewById(R.id.s2Amount);
        sacrifice3Amount = findViewById(R.id.s3Amount);
        sacrifice4Amount = findViewById(R.id.s4Amount);

        changePetBackground();
        updateNumbers();
    }

    private void changePetBackground() {
        if (isRare.isChecked()) {
            classicPet.setBackgroundColor(getResources().getColor(R.color.black));
            rarePet.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            classicPet.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            rarePet.setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!PetLevelActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(PetLevelActivity.this, getResources().getString(R.string.petLoseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            expAmount.setText("0");
            shardAmount.setText("0");
            levelNeeded.setText("0");
            sacrifice1Amount.setText("0");
            sacrifice2Amount.setText("0");
            sacrifice3Amount.setText("0");
            sacrifice4Amount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            expAmount.setText(pp.computeExp(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isRare.isChecked()));
            shardAmount.setText(pp.computeShard(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isRare.isChecked()));
            levelNeeded.setText(pp.computeLevelNeeded(aimLvlPicker.getValue()));
            int[] sacrifices = pp.computeSacrifices(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isRare.isChecked());
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
