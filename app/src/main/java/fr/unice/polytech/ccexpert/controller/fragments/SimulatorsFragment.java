package fr.unice.polytech.ccexpert.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.view.CardAdapter;

public class SimulatorsFragment extends Fragment {
    public static SimulatorsFragment newInstance() {
        SimulatorsFragment fragment = new SimulatorsFragment();
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
        final List<String> titles = Arrays.asList("Guerre de guilde", "Cristaux bleus", "Esquive");

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = (GridView) getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        super.onActivityCreated(bundle);
    }
}
