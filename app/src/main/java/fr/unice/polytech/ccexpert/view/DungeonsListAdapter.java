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
import fr.unice.polytech.ccexpert.model.Dungeon;

public class DungeonsListAdapter extends ArrayAdapter<Dungeon> {
    public DungeonsListAdapter(Context context, List<Dungeon> dungeons) {
        super(context, 0, dungeons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_dungeon, null);
        }

        Dungeon dungeon = getItem(position);

        TextView viewTitle = convertView.findViewById(R.id.text);
        viewTitle.setText(dungeon.getDoor() + " - " + dungeon.getBase());

        ImageView imageView = convertView.findViewById(R.id.logo);
        imageView.setImageResource(R.drawable.donjons);

        return convertView;
    }
}
