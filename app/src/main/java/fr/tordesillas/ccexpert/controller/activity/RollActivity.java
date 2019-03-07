package fr.tordesillas.ccexpert.controller.activity;

import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.HeroRoll;
import fr.tordesillas.ccexpert.model.Sets;
import fr.tordesillas.ccexpert.view.RollAdapter;

public class RollActivity extends BaseActivity {
    private TextView heroName;
    private TextView heroName2;
    private TextView heroName3;
    private TextView gemsAmount;
    private ImageView heroPic;
    private ImageView rollBg;
    private ImageView heroPic2;
    private ImageView rollBg2;
    private ImageView heroPic3;
    private ImageView rollBg3;
    private ArrayList<HeroRoll> legendaryHeroes;
    private ArrayList<HeroRoll> eliteHeroes;
    private ArrayList<HeroRoll> ordinaryHeroes;
    private RollAdapter legendaryRA;
    private RollAdapter eliteRA;
    private RollAdapter ordinaryRA;
    private int gemsVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);

        ((TextView) findViewById(R.id.rollTitle)).setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        heroName = findViewById(R.id.heroName);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "igg.ttf"));

        gemsAmount = findViewById(R.id.gemsAmount);
        heroPic = findViewById(R.id.heroPicture);
        rollBg = findViewById(R.id.roll_bg);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            heroPic2 = findViewById(R.id.heroPicture2);
            rollBg2 = findViewById(R.id.roll_bg2);
            heroPic3 = findViewById(R.id.heroPicture3);
            rollBg3 = findViewById(R.id.roll_bg3);
            heroName2 = findViewById(R.id.heroName2);
            heroName2.setTypeface(Typeface.createFromAsset(getAssets(), "igg.ttf"));
            heroName3 = findViewById(R.id.heroName3);
            heroName3.setTypeface(Typeface.createFromAsset(getAssets(), "igg.ttf"));
        }

        findViewById(R.id.rollButton).setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
                    view.invalidate();
                    break;
                }
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        roll450(heroPic, rollBg, heroName, heroPic2, rollBg2, heroName2, heroPic3, rollBg3, heroName3);
                    } else {
                        roll150(heroPic, rollBg, heroName);
                    }
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }
            return false;
        });

        if (savedInstanceState != null) {
            ordinaryHeroes = savedInstanceState.getParcelableArrayList("ordinary");
            eliteHeroes = savedInstanceState.getParcelableArrayList("elite");
            legendaryHeroes = savedInstanceState.getParcelableArrayList("legendary");
            gemsVal = savedInstanceState.getInt("gems");
            gemsAmount.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(gemsVal));
        } else {
            Sets.getInstance().zeroRollCounts();
            ordinaryHeroes = new ArrayList<>();
            eliteHeroes = new ArrayList<>();
            legendaryHeroes = new ArrayList<>();
            gemsVal = 0;
        }

        RecyclerView ordinaryRecyclerView = findViewById(R.id.ordinaryScroll);
        RecyclerView eliteRecyclerView = findViewById(R.id.eliteScroll);
        RecyclerView legendaryRecyclerView = findViewById(R.id.legendaryScroll);

        ordinaryRA = new RollAdapter(ordinaryHeroes, getApplicationContext());
        eliteRA = new RollAdapter(eliteHeroes, getApplicationContext());
        legendaryRA = new RollAdapter(legendaryHeroes, getApplicationContext());

        ordinaryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ordinaryRecyclerView.setAdapter(ordinaryRA);
        eliteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        eliteRecyclerView.setAdapter(eliteRA);
        legendaryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        legendaryRecyclerView.setAdapter(legendaryRA);
    }

    private void roll150(ImageView heroImg, ImageView bgImg, TextView heroName) {
        gemsVal += 150;
        gemsAmount.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(gemsVal));
        HeroRoll hero = Sets.getInstance().getHeroRoll();

        heroName.setText(hero.getName());

        String pic = hero.getPicture();
        int index;
        switch (hero.getType()) {
            case 3:
                pic += "2";
                bgImg.setImageResource(R.drawable.roll_bg_purple);
                index = addHeroInList(hero, legendaryHeroes);
                legendaryRA.notifyItemChanged(index);
                break;
            case 2:
                bgImg.setImageResource(R.drawable.roll_bg_blue);
                index = addHeroInList(hero, eliteHeroes);
                eliteRA.notifyItemChanged(index);
                break;
            case 1:
                bgImg.setImageResource(R.drawable.roll_bg_green);
                index = addHeroInList(hero, ordinaryHeroes);
                ordinaryRA.notifyItemChanged(index);
                break;
        }
        heroImg.setImageResource(getResources().getIdentifier(pic, "drawable", getPackageName()));

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        heroImg.startAnimation(fadeInAnimation);
    }

    private void roll450(ImageView heroImg1, ImageView bgImg1, TextView heroName1, ImageView heroImg2, ImageView bgImg2, TextView heroName2,
                         ImageView heroImg3, ImageView bgImg3, TextView heroName3) {
        roll150(heroImg1, bgImg1, heroName1);
        roll150(heroImg2, bgImg2, heroName2);
        roll150(heroImg3, bgImg3, heroName3);
    }

    private int addHeroInList(HeroRoll hero, List<HeroRoll> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getName().equals(hero.getName())) {
                return i;
            }
        }
        heroes.add(hero);
        return heroes.size() - 1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("ordinary", ordinaryHeroes);
        outState.putParcelableArrayList("elite", eliteHeroes);
        outState.putParcelableArrayList("legendary", legendaryHeroes);
        outState.putInt("gems", gemsVal);
    }
}
