package fr.tordesillas.ccexpert.controller.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.view.ProtectorsAdapter;

public class ProtectorsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protectors);

        ViewPager pager = findViewById(R.id.viewpager_protectors);
        pager.setAdapter(new ProtectorsAdapter(getSupportFragmentManager(), this));

        TabLayout tabs = findViewById(R.id.tabs_protectors);
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
