package fr.tordesillas.ccexpert;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;

import fr.tordesillas.ccexpert.controller.activity.BaseActivity;
import fr.tordesillas.ccexpert.controller.fragment.MainFragment;
import fr.tordesillas.ccexpert.model.Database;
import fr.tordesillas.ccexpert.service.AdService;

public class CCExpertMain extends BaseActivity {
    private static boolean firstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (firstLaunch) {
            AdService.getInstance().initAds(this);

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
