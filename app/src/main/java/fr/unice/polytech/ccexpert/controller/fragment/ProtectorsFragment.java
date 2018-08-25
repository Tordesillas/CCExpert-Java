package fr.unice.polytech.ccexpert.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.processor.ProtectorsProcessor;

public class ProtectorsFragment extends Fragment {
    private static final String KEY_POSITION = "position";

    private NumberPicker currentLvlPicker;
    private NumberPicker aimLvlPicker;
    private ProtectorsProcessor pp;
    private TextView meritAmount;
    private TextView birthrockAmount;
    private TextView attackAmount;
    private TextView hpAmount;
    private TextView dodgeAmount;
    private TextView accuracyAmount;
    private TextView tenacityAmount;
    private TextView critAmount;
    private TextView dmgAmount;
    private Toast toast;
    private int position;

    public static ProtectorsFragment newInstance(int position) {
        ProtectorsFragment fragment = new ProtectorsFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_protectors, container, false);

        position = getArguments().getInt(KEY_POSITION, -1);

        switch (position) {
            case 0:
                ((ImageView) rootView.findViewById(R.id.protector)).setImageResource(R.drawable.saint);
                ((ImageView) rootView.findViewById(R.id.birthrock)).setImageResource(R.drawable.birthrock_saint);
                break;
            case 1:
                ((ImageView) rootView.findViewById(R.id.protector)).setImageResource(R.drawable.brawler);
                ((ImageView) rootView.findViewById(R.id.birthrock)).setImageResource(R.drawable.birthrock_brawler);
                break;
            default:
                ((ImageView) rootView.findViewById(R.id.protector)).setImageResource(R.drawable.prophet);
                ((ImageView) rootView.findViewById(R.id.birthrock)).setImageResource(R.drawable.birthrock_prophet);
        }

        toast = Toast.makeText(getContext(), getResources().getString(R.string.loseLevel), Toast.LENGTH_SHORT);
        pp = new ProtectorsProcessor();

        currentLvlPicker = rootView.findViewById(R.id.currentLvl);
        aimLvlPicker = rootView.findViewById(R.id.aimLvl);
        currentLvlPicker.setMinValue(0);
        aimLvlPicker.setMinValue(1);
        currentLvlPicker.setMaxValue(47);
        aimLvlPicker.setMaxValue(48);
        currentLvlPicker.setWrapSelectorWheel(true);
        aimLvlPicker.setWrapSelectorWheel(true);

        meritAmount = rootView.findViewById(R.id.meritAmount);
        birthrockAmount = rootView.findViewById(R.id.birthrockAmount);
        attackAmount = rootView.findViewById(R.id.attackAmount);
        hpAmount = rootView.findViewById(R.id.hpAmount);
        dodgeAmount = rootView.findViewById(R.id.dodgeAmount);
        accuracyAmount = rootView.findViewById(R.id.accuracyAmount);
        tenacityAmount = rootView.findViewById(R.id.tenacityAmount);
        critAmount = rootView.findViewById(R.id.critAmount);
        dmgAmount = rootView.findViewById(R.id.dmgAmount);

        updateNumbers();

        currentLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());
        aimLvlPicker.setOnValueChangedListener((numberPicker, i, i1) -> updateNumbers());

        return rootView;
    }

    private void updateNumbers() {
        if (currentLvlPicker.getValue() > aimLvlPicker.getValue()) {
            toast.show();
            meritAmount.setText("0");
            birthrockAmount.setText("0");
            attackAmount.setText("0");
            hpAmount.setText("0");
            dodgeAmount.setText("0");
            accuracyAmount.setText("0");
            tenacityAmount.setText("0");
            critAmount.setText("0");
            dmgAmount.setText("0");
        } else {
            toast.cancel();
            int firstLevel = currentLvlPicker.getValue();
            int secondLevel = aimLvlPicker.getValue();
            meritAmount.setText(pp.computeMerit(firstLevel, secondLevel));
            birthrockAmount.setText(pp.computeBirthrock(firstLevel, secondLevel));
            attackAmount.setText(pp.computeAttack(firstLevel, secondLevel, position));
            hpAmount.setText(pp.computeHP(firstLevel, secondLevel, position));
            dodgeAmount.setText(pp.computeDodge(firstLevel, secondLevel));
            accuracyAmount.setText(pp.computeAccuracy(firstLevel, secondLevel));
            tenacityAmount.setText(pp.computeTenacity(firstLevel, secondLevel));
            critAmount.setText(pp.computeCrit(firstLevel, secondLevel));
            dmgAmount.setText(pp.computeDmg(firstLevel, secondLevel));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        toast.cancel();
    }
}
