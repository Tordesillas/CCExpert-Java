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
import fr.tordesillas.ccexpert.model.Pet;

public class PetsAdapter extends ArrayAdapter<Pet> {
    public PetsAdapter(Context context, List<Pet> pets) {
        super(context, 0, pets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.talent_littlecard, null);
        }

        Pet pet = getItem(position);

        assert pet != null;
        ((ImageView) convertView.findViewById(R.id.imageTalent)).setImageResource(getContext().getResources().getIdentifier(pet.getResource(), "drawable", getContext().getPackageName()));
        ((TextView) convertView.findViewById(R.id.talentName)).setText(pet.getName());

        return convertView;
    }
}
