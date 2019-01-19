package fr.tordesillas.ccexpert.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.tordesillas.ccexpert.R;

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
        if (title.equals(getContext().getResources().getString(R.string.heroes))) {
            return R.drawable.heroes;
        } else if (title.equals(getContext().getResources().getString(R.string.simulators))) {
            return R.drawable.guild_building2;
        } else if (title.equals(getContext().getResources().getString(R.string.dungeons))) {
            return R.drawable.dungeon;
        } else if (title.equals(getContext().getResources().getString(R.string.guildWar))) {
            return R.drawable.guild_wars;
        } else if (title.equals(getContext().getResources().getString(R.string.shard))) {
            return R.drawable.shards;
        } else if (title.equals(getContext().getResources().getString(R.string.crystal))) {
            return R.drawable.crystal;
        } else if (title.equals(getContext().getResources().getString(R.string.aetherock))) {
            return R.drawable.aetherock;
        } else if (title.equals(getContext().getResources().getString(R.string.dodge))) {
            return R.drawable.lightning_rock;
        } else if (title.equals(getContext().getResources().getString(R.string.accuracy))) {
            return R.drawable.eye_of_garuda;
        } else if (title.equals(getContext().getResources().getString(R.string.attackSpeed))) {
            return R.drawable.blitz_scroll;
        } else if (title.equals(getContext().getResources().getString(R.string.destiny))) {
            return R.drawable.karmic_rock1;
        } else if (title.equals(getContext().getResources().getString(R.string.archdemons))) {
            return R.drawable.archdemon;
        } else if (title.equals(getContext().getResources().getString(R.string.protectors))) {
            return R.drawable.saint;
        } else if (title.equals(getContext().getResources().getString(R.string.talents))) {
            return R.drawable.talents;
        } else if (title.equals(getContext().getResources().getString(R.string.encyclopedia))) {
            return R.drawable.guild_building;
        } else if (title.equals(getContext().getResources().getString(R.string.enchantments))) {
            return R.drawable.enchantments;
        } else if (title.equals(getContext().getResources().getString(R.string.skinTitle))) {
            return R.drawable.dove_keeper4;
        } else if (title.equals(getContext().getResources().getString(R.string.roll))) {
            return R.drawable.roll;
        } else if (title.equals(getContext().getResources().getString(R.string.recommendations))) {
            return R.drawable.armory_building;
        } else if(title.equals(getContext().getResources().getString(R.string.petLevel))) {
            return R.drawable.piblob;
        } else if(title.equals(getContext().getResources().getString(R.string.breakthroughLevels))) {
            return R.drawable.breakthrough_levels;
        } else {
            return R.drawable.ccexpert_logo;
        }
    }
}
