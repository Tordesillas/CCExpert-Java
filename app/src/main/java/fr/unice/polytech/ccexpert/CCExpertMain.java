package fr.unice.polytech.ccexpert;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import fr.unice.polytech.ccexpert.controller.activity.BaseActivity;
import fr.unice.polytech.ccexpert.controller.fragment.MainFragment;
import fr.unice.polytech.ccexpert.controller.fragment.SimulatorsFragment;
import fr.unice.polytech.ccexpert.model.Database;
import fr.unice.polytech.ccexpert.model.Sets;

public class CCExpertMain extends BaseActivity {
    private boolean firstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (firstLaunch) {
            Database database = new Database(this);
            try {
                database.createDataBase();
                database.openDataBase();
                Sets sets = database.execute();
                database.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            firstLaunch = false;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, MainFragment.newInstance()).commit();
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
