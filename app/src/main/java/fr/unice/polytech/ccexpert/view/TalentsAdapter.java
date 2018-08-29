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
import fr.unice.polytech.ccexpert.model.Talent;

public class TalentsAdapter extends ArrayAdapter<Talent> {
    public TalentsAdapter(Context context, List<Talent> talents) {
        super(context, 0, talents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.hero_littlecard, null);
        }

        Talent talent = getItem(position);

        assert talent != null;
        ((ImageView) convertView.findViewById(R.id.imageHero)).setImageResource(getContext().getResources().getIdentifier(talent.getTalentResource(), "drawable", getContext().getPackageName()));

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                ((TextView) convertView.findViewById(R.id.heroName)).setText(talent.getFrenchName()); break;
            default:
                ((TextView) convertView.findViewById(R.id.heroName)).setText(talent.getName());
        }

        return convertView;
    }
}
