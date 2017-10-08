package fr.unice.polytech.ccexpert.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class DungeonFragment extends Fragment {
    public static DungeonFragment newInstance(Bundle bundle) {
        DungeonFragment fragment = new DungeonFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
