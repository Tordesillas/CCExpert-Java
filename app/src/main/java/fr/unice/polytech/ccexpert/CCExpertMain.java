package fr.unice.polytech.ccexpert;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import fr.unice.polytech.ccexpert.controller.activity.BaseActivity;
import fr.unice.polytech.ccexpert.controller.fragment.MainFragment;
import fr.unice.polytech.ccexpert.controller.fragment.SimulatorsFragment;

public class CCExpertMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, MainFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_home :
                transaction.replace(R.id.main_fragment, MainFragment.newInstance()).commit();
                return true;
            case R.id.nav_simulator :
                transaction.replace(R.id.main_fragment, SimulatorsFragment.newInstance(), "main");
                transaction.addToBackStack("main");
                transaction.commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
