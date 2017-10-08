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

public class MainAdapter extends ArrayAdapter<String> {
    public MainAdapter(Context context, List<String> titles) {
        super(context, 0, titles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.title_card, null);
        }

        String page = getItem(position);
        TextView viewTitle = (TextView) convertView.findViewById(R.id.title);
        viewTitle.setText(page);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.donjons);

        return convertView;
    }
}
