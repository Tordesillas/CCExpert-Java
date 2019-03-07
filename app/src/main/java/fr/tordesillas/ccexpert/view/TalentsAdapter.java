package fr.tordesillas.ccexpert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Talent;

public class TalentsAdapter extends ArrayAdapter<Talent> {
    public TalentsAdapter(Context context, List<Talent> talents) {
        super(context, 0, talents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.talent_littlecard, null);
        }

        Talent talent = getItem(position);

        assert talent != null;
        ((ImageView) convertView.findViewById(R.id.imageTalent)).setImageResource(getContext().getResources().getIdentifier(talent.getTalentResource(), "drawable", getContext().getPackageName()));

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                ((TextView) convertView.findViewById(R.id.talentName)).setText(talent.getFrenchName()); break;
            default:
                ((TextView) convertView.findViewById(R.id.talentName)).setText(talent.getName());
        }

        return convertView;
    }
}
