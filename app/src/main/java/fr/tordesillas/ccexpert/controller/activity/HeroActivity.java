package fr.tordesillas.ccexpert.controller.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.tordesillas.ccexpert.CCExpertMain;
import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Hero;
import fr.tordesillas.ccexpert.model.Sets;

public class HeroActivity extends BaseActivity {
    private int appearance;
    private int resHero;
    private int resHeroEvo;
    private int resHeroSkin;
    private int resHeroSkin2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        Hero hero = Sets.getInstance().getHero(getIntent().getStringExtra("heroName"));

        TextView heroName = findViewById(R.id.heroTitle);
        heroName.setTypeface(Typeface.createFromAsset(getAssets(), "Script1Rager.otf"));

        try {
            heroName.setText(hero.getName());

            appearance = 2;

            resHero = getResources().getIdentifier(hero.getPicture() + "2", "drawable", getPackageName());
            resHeroEvo = getResources().getIdentifier(hero.getPicture() + "3", "drawable", getPackageName());
            resHeroSkin = getResources().getIdentifier(hero.getPicture() + "4", "drawable", getPackageName());
            resHeroSkin2 = getResources().getIdentifier(hero.getPicture() + "5", "drawable", getPackageName());

            changePicture();

            findViewById(R.id.evoHero).setOnClickListener(view -> changePicture());
        } catch (NullPointerException e) {
            Toast.makeText(HeroActivity.this, getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            Intent closeActivitiesIntent = new Intent(this, CCExpertMain.class);
            closeActivitiesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(closeActivitiesIntent);
        }

        try {
            int resPet = getResources().getIdentifier(hero.getPetPicture(), "drawable", getPackageName());
            ((ImageView) findViewById(R.id.pet)).setImageResource(resPet);
        } catch (NullPointerException ignored) {}


        try {
            int resTalent1 = getResources().getIdentifier(hero.getTalentGwAttack(), "drawable", getPackageName());
            int resTalent2 = getResources().getIdentifier(hero.getTalentGwDefense(), "drawable", getPackageName());
            int resTalent3 = getResources().getIdentifier(hero.getTalentDungeon(), "drawable", getPackageName());

            ((ImageView) findViewById(R.id.talent1)).setImageResource(resTalent1);
            ((ImageView) findViewById(R.id.talent2)).setImageResource(resTalent2);
            ((ImageView) findViewById(R.id.talent3)).setImageResource(resTalent3);
        } catch (NullPointerException ignored) {}

        try {
            int resCrest1 = getResources().getIdentifier(hero.getCrestGwAttack(), "drawable", getPackageName());
            int resCrest2 = getResources().getIdentifier(hero.getCrestGwDefense(), "drawable", getPackageName());
            int resCrest3 = getResources().getIdentifier(hero.getCrestDungeon(), "drawable", getPackageName());

            ((ImageView) findViewById(R.id.crest1)).setImageResource(resCrest1);
            ((ImageView) findViewById(R.id.crest2)).setImageResource(resCrest2);
            ((ImageView) findViewById(R.id.crest3)).setImageResource(resCrest3);
        } catch (NullPointerException ignored) {}

        try {
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
        } catch (NullPointerException ignored) {}

    }

    private void changePicture() {
        try {
            switch (appearance) {
                case 2:
                    ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHero);
                    appearance++;
                    break;
                case 3:
                    ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHeroEvo);
                    appearance++;
                    break;
                case 4:
                    appearance++;
                    if (resHeroSkin == 0) {
                        changePicture();
                    } else {
                        ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHeroSkin);
                    }
                    break;
                case 5:
                    appearance++;
                    if (resHeroSkin2 == 0) {
                        changePicture();
                    } else {
                        ((ImageView) findViewById(R.id.heroLargePicture)).setImageResource(resHeroSkin2);
                    }
                    break;
                default:
                    appearance = 2;
                    changePicture();
                    break;
            }
        } catch (Exception ignored) { }
    }
}
