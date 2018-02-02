package fr.unice.polytech.ccexpert.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.unice.polytech.ccexpert.R;

public class CardAdapter extends ArrayAdapter<String> {
    public CardAdapter(Context context, List<String> titles) {
        super(context, 0, titles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.title_card, null);
        }

        String page = getItem(position);

        TextView viewTitle = convertView.findViewById(R.id.title);
        viewTitle.setText(page);
        viewTitle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Script1Rager.otf"));

        ImageView imageView = convertView.findViewById(R.id.image);
        imageView.setImageResource(findPicture(page));

        return convertView;
    }

    private int findPicture(String title) {
        switch (title) {
            case "Héros":
                return R.drawable.heroes;
            case "Simulateurs":
                return R.drawable.boss;
            case "Donjons":
                return R.drawable.donjons;
            case "Guerre de guilde":
                return R.drawable.guild_wars;
            case "Fragments":
                return R.drawable.shards;
            case "Cristaux bleus":
                return R.drawable.crystal;
            case "Aura-guerrières":
                return R.drawable.aetherock;
            case "Esquive":
                return R.drawable.pierre_de_foudre;
            case "Précision":
                return R.drawable.garuda;
            case "Vitesse d'attaque":
                return R.drawable.blitz;
            default:
                return R.drawable.exorcist;
        }
    }
}
