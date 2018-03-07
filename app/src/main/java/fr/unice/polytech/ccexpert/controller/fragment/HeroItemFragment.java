package fr.unice.polytech.ccexpert.controller.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fr.unice.polytech.ccexpert.R;

public class HeroItemFragment extends Fragment {
    public static HeroItemFragment newInstance() {
        HeroItemFragment fragment = new HeroItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hero_item_card, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        ((TextView) getView().findViewById(R.id.itemName)).setText("Miaou");
        ((ImageView) getView().findViewById(R.id.itemImage)).setImageResource(R.drawable.berserk);
        super.onActivityCreated(bundle);
    }
}
