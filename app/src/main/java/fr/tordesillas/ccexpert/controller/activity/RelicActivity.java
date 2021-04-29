package fr.tordesillas.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.processor.RelicProcessor;

public class RelicActivity extends BaseActivity {
    private RelicProcessor rp;
    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private SwitchMaterial isEpic;
    private ImageView legendaryHero;
    private ImageView epicHero;
    private TextView shardAmount;
    private TextView heroCardsAmount;
    private TextView vestigeAmount;
    private TextView legendaryMarkAmount;
    private TextView epicMarkAmount;
    private TextView lifeAmount;
    private TextView attackAmount;
    private Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relic);

        ((TextView) findViewById(R.id.relicTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        rp = new RelicProcessor();
        currentLvlPicker = findViewById(R.id.currentLvl);
        aimLvlPicker = findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(59);
        aimLvlPicker.setMaxValue(60);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        isEpic = findViewById(R.id.switchHeroType);
        isEpic.setOnCheckedChangeListener((compoundButton, b) -> {
            changePetBackground();
            updateNumbers();
        });
        legendaryHero = findViewById(R.id.legendaryHero);
        epicHero = findViewById(R.id.epicHero);
        epicHero.setOnClickListener(view -> isEpic.setChecked(true));
        legendaryHero.setOnClickListener(view -> isEpic.setChecked(false));

        shardAmount = findViewById(R.id.shardAmount);
        heroCardsAmount = findViewById(R.id.heroCardsAmount);
        vestigeAmount = findViewById(R.id.vestigeAmount);
        legendaryMarkAmount = findViewById(R.id.legendaryMarkAmount);
        epicMarkAmount = findViewById(R.id.epicMarkAmount);
        lifeAmount = findViewById(R.id.hpAmount);
        attackAmount = findViewById(R.id.attackAmount);

        changePetBackground();
        updateNumbers();
    }

    private void changePetBackground() {
        if (isEpic.isChecked()) {
            legendaryHero.setBackgroundColor(getResources().getColor(R.color.black));
            epicHero.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            legendaryHero.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            epicHero.setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            if (!RelicActivity.this.isFinishing() && (toast == null || !toast.getView().isShown())) {
                toast = Toast.makeText(RelicActivity.this, getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
                toast.show();
            }
            shardAmount.setText("0");
            heroCardsAmount.setText("0");
            vestigeAmount.setText("0");
            legendaryMarkAmount.setText("0");
            epicMarkAmount.setText("0");
            lifeAmount.setText("0");
            attackAmount.setText("0");
        } else {
            if (toast != null) {
                toast.cancel();
            }
            shardAmount.setText(rp.computeShards(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
            heroCardsAmount.setText(rp.computeCards(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
            vestigeAmount.setText(rp.computeVestiges(currentLvlPicker.getValue(), aimLvlPicker.getValue()));
            legendaryMarkAmount.setText(rp.computeLegendaryMarks(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
            epicMarkAmount.setText(rp.computeEpicMarks(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
            lifeAmount.setText(rp.computeLife(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
            attackAmount.setText(rp.computeAttack(currentLvlPicker.getValue(), aimLvlPicker.getValue(), isEpic.isChecked()));
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
