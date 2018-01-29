package fr.unice.polytech.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.activity.DungeonsActivity;
import fr.unice.polytech.ccexpert.view.CardAdapter;

public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        final List<String> titles = Arrays.asList("Héros", "Simulateurs", "Donjons");

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_fragment, SimulatorsFragment.newInstance(), "main");
                        transaction.addToBackStack("main");
                        transaction.commit();
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), DungeonsActivity.class));
                        break;
                }

            }
        });

        super.onActivityCreated(bundle);
    }
}
