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
            case "Heroes":
                return R.drawable.heroes;
            case "Simulateurs":
            case "Simulators":
                return R.drawable.boss;
            case "Donjons":
            case "Dungeons":
                return R.drawable.donjons;
            case "Guerre de guilde":
            case "Guild war":
                return R.drawable.guild_wars;
            case "Niveau du talent":
            case "Talent level":
                return R.drawable.shards;
            case "Cristaux bleus":
            case "Blue crystals":
                return R.drawable.crystal;
            case "Aura-guerrières":
            case "Aetherocks":
                return R.drawable.aetherock;
            case "Esquive":
            case "Dodge":
                return R.drawable.lightning_rock;
            case "Précision":
            case "Accuracy":
                return R.drawable.eye_of_garuda;
            case "Vitesse d'attaque":
            case "Attack speed":
                return R.drawable.blitz_scroll;
            case "Destinée":
            case "Destiny":
                return R.drawable.karmic_rock1;
            case "Archidémons":
            case "Archdemons":
                return R.drawable.archdemon;
            case "Gardiens":
            case "Protectors":
                return R.drawable.brawler;
            case "Talents":
                return R.drawable.talents;
            case "Items":
                return R.drawable.mana;
            case "Enchantements":
            case "Enchantments":
                return R.drawable.enchantments;
            default:
                return R.drawable.ccexpert_rounded;
        }
    }
}
