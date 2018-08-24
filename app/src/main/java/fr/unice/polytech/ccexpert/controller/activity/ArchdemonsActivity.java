package fr.unice.polytech.ccexpert.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.view.ArchdemonAdapter;

public class ArchdemonsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archdemons);

        ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(new ArchdemonAdapter(getSupportFragmentManager(), this));

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
