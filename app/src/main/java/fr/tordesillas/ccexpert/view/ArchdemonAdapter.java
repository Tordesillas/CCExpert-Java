package fr.tordesillas.ccexpert.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.fragment.ArchdemonFragment;
import fr.tordesillas.ccexpert.model.Sets;

public class ArchdemonAdapter extends FragmentPagerAdapter {
    private Context context;

    public ArchdemonAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return Sets.getInstance().getArchdemonsSize();
    }

    @Override
    public Fragment getItem(int position) {
        return ArchdemonFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(R.string.archdemon) + " " + (position + 1);
    }
}
