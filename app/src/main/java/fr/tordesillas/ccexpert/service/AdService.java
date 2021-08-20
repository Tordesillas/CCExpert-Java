package fr.tordesillas.ccexpert.service;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
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
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context,"ca-app-pub-7552728611291260/9825698645", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });
    }

    public void showAd(Activity activity) {
        try {
            if (mInterstitialAd != null) {
                mInterstitialAd.show(activity);
            }
        } catch (NullPointerException ignored) {}
    }
}
