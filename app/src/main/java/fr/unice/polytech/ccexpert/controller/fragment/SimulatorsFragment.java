package fr.unice.polytech.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.activity.GuildWarActivity;
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
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(), GuildWarActivity.class));
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

            }
        });

        super.onActivityCreated(bundle);
    }
}
