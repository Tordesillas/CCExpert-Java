package fr.unice.polytech.ccexpert.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.unice.polytech.ccexpert.controller.fragments.DungeonsListFragment;

public class DungeonsAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "F2P", "P2W" };

    public DungeonsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return DungeonsListFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
