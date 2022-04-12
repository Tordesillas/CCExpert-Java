package fr.tordesillas.ccexpert.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Insignia;

public class InsigniasAdapter extends ArrayAdapter<Insignia> {
    public InsigniasAdapter(Context context, List<Insignia> insignias) {
        super(context, 0, insignias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.talent_littlecard, null);
        }

        Insignia insignia = getItem(position);

        assert insignia != null;
        ((ImageView) convertView.findViewById(R.id.imageTalent)).setImageResource(getContext().getResources().getIdentifier(insignia.getInsigniaResource(), "drawable", getContext().getPackageName()));
        ((TextView) convertView.findViewById(R.id.talentName)).setText(insignia.getName());

        return convertView;
    }
}
