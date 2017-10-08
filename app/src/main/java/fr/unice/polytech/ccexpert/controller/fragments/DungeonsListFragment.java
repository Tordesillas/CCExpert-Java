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

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Dungeon;
import fr.unice.polytech.ccexpert.view.DungeonsListAdapter;

public class DungeonsListFragment extends Fragment {
    public static DungeonsListFragment newInstance(int position) {
        DungeonsListFragment fragment = new DungeonsListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        Bundle b = this.getArguments();
        final List<Dungeon> dungeons = filterPlaces(b.getInt("position"));

        ListAdapter la = new DungeonsListAdapter(this.getContext(), dungeons);
        GridView gridView = (GridView) getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("place", dungeons.get(position));
                transaction.replace(R.id.main_fragment, DungeonFragment.newInstance(bundle), "back");
                transaction.addToBackStack("back");
                transaction.commit();
            }
        });

        super.onActivityCreated(bundle);
    }

    private List<Dungeon> filterPlaces(int position) {
        /*List<BookingPlace> places = new ArrayList<>();
        MallDatabase database = new MallDatabase(getContext());
        try {
            database.createDataBase();
            database.openDataBase();
            places.addAll(database.getAllBooking());
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        List<Dungeon> newDungeons = new ArrayList<>();/*
        for (BookingPlace place : places) {
            if ((position == 0 && place.getType().equals(BookingType.RESTAURANT)) ||
                    (position == 1 && place.getType().equals(BookingType.SOIN)) ||
                    (position == 2 && place.getType().equals(BookingType.LOISIR))) {
                newPlaces.add(place);
            }
        }*/
        newDungeons.add(new Dungeon("youtube.com", 1, 2, 1, 2, 3, 4, 5, 6, 1, 1));

        return newDungeons;
    }
}
