package fr.tordesillas.ccexpert.controller.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.view.ArchdemonAdapter;

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
