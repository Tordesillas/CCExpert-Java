package fr.unice.polytech.ccexpert.controller.fragments;

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
        GridView gridView = (GridView) getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        transaction.replace(R.id.main_fragment, SimulatorsFragment.newInstance(), "main");
                        break;
                    case 2:
                        transaction.replace(R.id.main_fragment, DungeonsFragment.newInstance(), "main");
                        break;
                    default:
                        break;
                }
                transaction.addToBackStack("main");
                transaction.commit();
            }
        });

        super.onActivityCreated(bundle);
    }
}
