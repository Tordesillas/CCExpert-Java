package fr.tordesillas.ccexpert.service;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdService {
    private static AdService instance = null;

    private InterstitialAd mInterstitialAd;

    private AdService() {}

    public static AdService getInstance() {
        if (instance == null) {
            instance = new AdService();
        }
        return instance;
    }

    public void initAds(Context context) {
        MobileAds.initialize(context);
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-7552728611291260/9825698645");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
