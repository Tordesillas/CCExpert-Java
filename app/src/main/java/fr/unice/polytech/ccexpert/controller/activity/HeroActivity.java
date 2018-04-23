package fr.unice.polytech.ccexpert.controller.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Hero;
import fr.unice.polytech.ccexpert.model.Sets;

public class HeroActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Hero hero = Sets.getInstance().getHero(getIntent().getStringExtra("heroName"));

        TextView heroName = findViewById(R.id.heroTitle);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));
        if ("french".equals(Locale.getDefault().getDisplayLanguage().toLowerCase()) ||
                "français".equals(Locale.getDefault().getDisplayLanguage().toLowerCase())) {
            heroName.setText(hero.getFrenchName());
        } else {
            heroName.setText(hero.getEnglishName());
        }

        final boolean[] evo = {false};

        int resHero = getResources().getIdentifier(hero.getPicture() + "2", "drawable", getPackageName());
        int resHeroEvo = getResources().getIdentifier(hero.getPicture() + "3", "drawable", getPackageName());

        ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHero);

        findViewById(R.id.evoHero).setOnClickListener(view -> {
            if (evo[0]) {
                ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHero);
                evo[0] = false;
            } else {
                ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHeroEvo);
                evo[0] = true;
            }
        });

        int resPet = getResources().getIdentifier(hero.getPetPicture(), "drawable", getPackageName());
        ((ImageView) findViewById(R.id.pet)).setImageResource(resPet);

        int resTalent1 = getResources().getIdentifier(hero.getTalentGwAttack(), "drawable", getPackageName());
        int resTalent2 = getResources().getIdentifier(hero.getTalentGwDefense(), "drawable", getPackageName());
        int resTalent3 = getResources().getIdentifier(hero.getTalentDungeon(), "drawable", getPackageName());

        ((ImageView) findViewById(R.id.talent1)).setImageResource(resTalent1);
        ((ImageView) findViewById(R.id.talent2)).setImageResource(resTalent2);
        ((ImageView) findViewById(R.id.talent3)).setImageResource(resTalent3);

        int resCrest1 = getResources().getIdentifier(hero.getCrestGwAttack(), "drawable", getPackageName());
        int resCrest2 = getResources().getIdentifier(hero.getCrestGwDefense(), "drawable", getPackageName());
        int resCrest3 = getResources().getIdentifier(hero.getCrestDungeon(), "drawable", getPackageName());

        ((ImageView) findViewById(R.id.crest1)).setImageResource(resCrest1);
        ((ImageView) findViewById(R.id.crest2)).setImageResource(resCrest2);
        ((ImageView) findViewById(R.id.crest3)).setImageResource(resCrest3);

        int resArtifact1 = getResources().getIdentifier(hero.getArtifactGwAttack(), "drawable", getPackageName());
        int resArtifact2 = getResources().getIdentifier(hero.getArtifactGwDefense(), "drawable", getPackageName());
        int resArtifact3 = getResources().getIdentifier(hero.getArtifactDungeon(), "drawable", getPackageName());

        ((ImageView) findViewById(R.id.artifact1)).setImageResource(resArtifact1);
        ((ImageView) findViewById(R.id.artifact2)).setImageResource(resArtifact2);
        ((ImageView) findViewById(R.id.artifact3)).setImageResource(resArtifact3);

        List<String> enchantments = hero.getEnchantments();
        switch (enchantments.size()) {
            case 0:
                findViewById(R.id.enchantmentsLayout).setVisibility(View.INVISIBLE);
                break;
            case 3:
                int resEnchantment3 = getResources().getIdentifier(enchantments.get(2), "drawable", getPackageName());
                ((ImageView) findViewById(R.id.enchantment3)).setImageResource(resEnchantment3);
            case 2:
                int resEnchantment2 = getResources().getIdentifier(enchantments.get(1), "drawable", getPackageName());
                ((ImageView) findViewById(R.id.enchantment2)).setImageResource(resEnchantment2);
            case 1:
                int resEnchantment1 = getResources().getIdentifier(enchantments.get(0), "drawable", getPackageName());
                ((ImageView) findViewById(R.id.enchantment1)).setImageResource(resEnchantment1);
        }
    }
}
