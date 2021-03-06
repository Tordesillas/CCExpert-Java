package fr.tordesillas.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.activity.ArchdemonsActivity;
import fr.tordesillas.ccexpert.controller.activity.DungeonsActivity;
import fr.tordesillas.ccexpert.controller.activity.HeroesActivity;
import fr.tordesillas.ccexpert.view.CardAdapter;

public class RecommendationsFragment extends Fragment {
    public static RecommendationsFragment newInstance() {
        RecommendationsFragment fragment = new RecommendationsFragment();
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
                getResources().getString(R.string.heroes),
                getResources().getString(R.string.dungeons),
                getResources().getString(R.string.archdemons)
        );

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), HeroesActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), DungeonsActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), ArchdemonsActivity.class));
                    break;
            }
        });

        super.onActivityCreated(bundle);
    }
}
