package fr.unice.polytech.ccexpert.controller.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import fr.unice.polytech.ccexpert.CCExpertMain;
import fr.unice.polytech.ccexpert.R;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout fullLayout;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        fullLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = fullLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(fullLayout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        NavigationView navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, fullLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        fullLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fullLayout.closeDrawer(GravityCompat.START);
        return onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home :
                startActivity(new Intent(this, CCExpertMain.class));
                return true;
            case R.id.nav_dungeons :
                startActivity(new Intent(this, DungeonsActivity.class));
                return true;
            case R.id.nav_heroes :
                startActivity(new Intent(this, HeroesActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (fullLayout.isDrawerOpen(GravityCompat.START)) {
            fullLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void createSimulatorDialog(String message) {
        if (message == null) {
            return;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Simulation terminée")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .show();
    }
}