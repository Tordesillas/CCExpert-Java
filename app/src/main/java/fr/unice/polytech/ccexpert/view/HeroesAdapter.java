package fr.unice.polytech.ccexpert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.model.Hero;
import fr.unice.polytech.ccexpert.model.StringToResource;

public class HeroesAdapter extends ArrayAdapter<Hero> {
    public HeroesAdapter(Context context, List<Hero> heroes) {
        super(context, 0, heroes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.hero_littlecard, null);
        }

        Hero hero = getItem(position);

        ((ImageView) convertView.findViewById(R.id.imageHero)).setImageResource(StringToResource.getHeroPicture(hero.getFrenchName()));
        if ("french".equals(Locale.getDefault().getDisplayLanguage().toLowerCase()) ||
                "français".equals(Locale.getDefault().getDisplayLanguage().toLowerCase())) {
            ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getFrenchName());
        } else {
            ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getEnglishName());
        }

        return convertView;
    }
}
