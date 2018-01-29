package fr.unice.polytech.ccexpert.controller.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.view.DungeonsAdapter;

public class DungeonsFragment extends Fragment {
    public static DungeonsFragment newInstance() {
        DungeonsFragment fragment = new DungeonsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dungeons, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        ViewPager viewPager = getView().findViewById(R.id.viewpager);
        viewPager.setAdapter(new DungeonsAdapter(getChildFragmentManager()));

        TabLayout tabLayout = getView().findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        super.onActivityCreated(bundle);
    }
}
