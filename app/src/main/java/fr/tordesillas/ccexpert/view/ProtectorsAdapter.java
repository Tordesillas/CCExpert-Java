package fr.tordesillas.ccexpert.view;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.fragment.ProtectorsFragment;

public class ProtectorsAdapter extends FragmentPagerAdapter {
    private Context context;

    public ProtectorsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        return ProtectorsFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getString(R.string.saint);
            case 1:
                return context.getResources().getString(R.string.brawler);
            default:
                return context.getResources().getString(R.string.prophet);
        }
    }
}