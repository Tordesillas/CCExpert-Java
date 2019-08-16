package fr.tordesillas.ccexpert.controller.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.view.CardAdapter;

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
        final List<String> titles = Arrays.asList(
                getResources().getString(R.string.recommendations),
                getResources().getString(R.string.encyclopedia),
                getResources().getString(R.string.simulators)
        );

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            switch (position) {
                case 0:
                    transaction.replace(R.id.main_fragment, RecommendationsFragment.newInstance(), "main");
                    break;
                case 1:
                    transaction.replace(R.id.main_fragment, EncyclopediaFragment.newInstance(), "main");
                    break;
                case 2:
                    transaction.replace(R.id.main_fragment, SimulatorsFragment.newInstance(), "main");
                    break;
            }
            transaction.addToBackStack("main");
            transaction.commit();
        });

        super.onActivityCreated(bundle);
    }
}
