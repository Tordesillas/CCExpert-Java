package fr.unice.polytech.ccexpert;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import fr.unice.polytech.ccexpert.controller.activity.BaseActivity;
import fr.unice.polytech.ccexpert.controller.fragment.MainFragment;
import fr.unice.polytech.ccexpert.model.Database;

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
                database.execute();
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
        if (item.getItemId() == R.id.nav_home) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment, MainFragment.newInstance()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
