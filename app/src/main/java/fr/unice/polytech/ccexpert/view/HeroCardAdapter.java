package fr.unice.polytech.ccexpert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Hero;
import fr.unice.polytech.ccexpert.model.HeroFaculties;
import fr.unice.polytech.ccexpert.model.Sets;
import fr.unice.polytech.ccexpert.model.StringToResource;

public class HeroCardAdapter extends ArrayAdapter<HeroFaculties> {
    public HeroCardAdapter(Context context, List<HeroFaculties> heroes) {
        super(context, 0, heroes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.hero_card, null);
            convertView = inflater.inflate(R.layout.hero_littlecard, null);
        }

        HeroFaculties heroFaculties = getItem(position);
        Hero hero = Sets.getInstance().getHero(heroFaculties.getHeroId());

        ((ImageView) convertView.findViewById(R.id.imageHero)).setImageResource(StringToResource.getHeroPicture(hero.getFrenchName()));
        ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getFrenchName());
/*        ((TextView) convertView.findViewById(R.id.heroLvl)).setText("Niveau " + heroFaculties.getLevel());
        ((TextView) convertView.findViewById(R.id.heroPower)).setText("Pouvoir niv. " + heroFaculties.getPowerLevel());
        ((ImageView) convertView.findViewById(R.id.imageTalent)).setImageResource(StringToResource.getTalentPicture(heroFaculties.getTalentName()));
        ((TextView) convertView.findViewById(R.id.heroTalent)).setText("Talent niv. " + heroFaculties.getTalentLevel());
*/
        return convertView;
    }
}
