package fr.tordesillas.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.activity.EnchantmentsActivity;
import fr.tordesillas.ccexpert.controller.activity.TalentsActivity;
import fr.tordesillas.ccexpert.view.CardAdapter;

public class EncyclopediaFragment extends Fragment {
    public static EncyclopediaFragment newInstance() {
        EncyclopediaFragment fragment = new EncyclopediaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        final List<String> titles = Arrays.asList(
                getResources().getString(R.string.talents),
                getResources().getString(R.string.enchantments)
        );

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), TalentsActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), EnchantmentsActivity.class));
                    break;
            }
        });

        super.onActivityCreated(bundle);
    }
}
