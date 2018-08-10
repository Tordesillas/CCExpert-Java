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

        ((ImageView) convertView.findViewById(R.id.imageHero)).setImageResource(getContext().getResources().getIdentifier(hero.getPicture(), "drawable", getContext().getPackageName()));

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getFrenchName()); break;
            case "german":
            case "deutsch":
                ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getGermanName()); break;
            default:
                ((TextView) convertView.findViewById(R.id.heroName)).setText(hero.getEnglishName());
        }

        return convertView;
    }
}
