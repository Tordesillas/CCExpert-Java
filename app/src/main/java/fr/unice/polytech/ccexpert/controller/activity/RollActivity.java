package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.HeroRoll;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.view.RollAdapter;

public class RollActivity extends BaseActivity {
    private TextView heroName;
    private TextView gemsAmount;
    private ImageView heroPic;
    private ImageView rollBg;
    private List<HeroRoll> legendaryHeroes;
    private List<HeroRoll> eliteHeroes;
    private List<HeroRoll> ordinaryHeroes;
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
        gemsVal = 0;
        heroPic = findViewById(R.id.heroPicture);
        rollBg = findViewById(R.id.roll_bg);

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
                    roll(rollBg);
                case MotionEvent.ACTION_CANCEL: {
                    ImageView view = (ImageView) v;
                    view.getDrawable().clearColorFilter();
                    view.invalidate();
                    break;
                }
            }
            return false;
        });

        ordinaryHeroes = new ArrayList<>();
        eliteHeroes = new ArrayList<>();
        legendaryHeroes = new ArrayList<>();

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

    private void roll(ImageView image) {
        gemsVal += 150;
        gemsAmount.setText(NumberFormat.getNumberInstance(Locale.FRANCE).format(gemsVal));
        HeroRoll hero = Sets.getInstance().getHeroRoll();

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                heroName.setText(hero.getNameFr()); break;
            case "german":
            case "deutsch":
                heroName.setText(hero.getDeName()); break;
            default:
                heroName.setText(hero.getEnName());
        }

        String pic = hero.getPicture();
        int index;
        switch (hero.getType()) {
            case 3:
                if (!"Gelatinous Champion".equals(hero.getEnName())) {
                    pic += "2";
                }
                image.setImageResource(R.drawable.roll_bg_purple);
                index = addHeroInList(hero, legendaryHeroes);
                legendaryRA.notifyItemChanged(index);
                break;
            case 2:
                image.setImageResource(R.drawable.roll_bg_blue);
                index = addHeroInList(hero, eliteHeroes);
                eliteRA.notifyItemChanged(index);
                break;
            case 1:
                image.setImageResource(R.drawable.roll_bg_green);
                index = addHeroInList(hero, ordinaryHeroes);
                ordinaryRA.notifyItemChanged(index);
                break;
        }
        heroPic.setImageResource(getResources().getIdentifier(pic, "drawable", getPackageName()));
    }

    private int addHeroInList(HeroRoll hero, List<HeroRoll> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getEnName().equals(hero.getEnName())) {
                return i;
            }
        }
        heroes.add(hero);
        return heroes.size() - 1;
    }
}
