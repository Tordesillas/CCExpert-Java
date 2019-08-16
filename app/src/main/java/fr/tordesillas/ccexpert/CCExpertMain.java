package fr.tordesillas.ccexpert;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import fr.tordesillas.ccexpert.controller.activity.BaseActivity;
import fr.tordesillas.ccexpert.controller.fragment.MainFragment;
import fr.tordesillas.ccexpert.model.Database;

public class CCExpertMain extends BaseActivity {
    private static boolean firstLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,"ca-app-pub-7552728611291260~9498643006"); //for tests: ca-app-pub-3940256099942544~3347511713
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7552728611291260/9825698645"); //for tests: ca-app-pub-3940256099942544/1033173712
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });

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

            mInterstitialAd.loadAd(new AdRequest.Builder().build());

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
