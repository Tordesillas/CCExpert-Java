package fr.tordesillas.ccexpert.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.model.Archdemon;
import fr.tordesillas.ccexpert.model.Sets;

public class ArchdemonFragment extends Fragment {
    private static final String KEY_POSITION = "position";

    public static ArchdemonFragment newInstance(int position) {
        ArchdemonFragment fragment = new ArchdemonFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_archdemon, container, false);

        int position = getArguments().getInt(KEY_POSITION, 0);

        if (position < 0) {
            position = 0;
        } else if (position >= Sets.getInstance().getArchdemonsSize()) {
            position = Sets.getInstance().getArchdemonsSize() - 1;
        }

        Archdemon archdemon = Sets.getInstance().getArchdemon(position);

        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
                ((TextView) rootView.findViewById(R.id.description)).setText(archdemon.getDescriptionFr().replace(" + ", "\n"));
                break;
            default:
                ((TextView) rootView.findViewById(R.id.description)).setText(archdemon.getDescriptionEn().replace(" + ", "\n"));
        }

        ((ImageView) rootView.findViewById(R.id.hero1)).setImageResource(getResources().getIdentifier(archdemon.getHero(0).getPicture(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.hero2)).setImageResource(getResources().getIdentifier(archdemon.getHero(1).getPicture(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.hero3)).setImageResource(getResources().getIdentifier(archdemon.getHero(2).getPicture(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.hero4)).setImageResource(getResources().getIdentifier(archdemon.getHero(3).getPicture(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.hero5)).setImageResource(getResources().getIdentifier(archdemon.getHero(4).getPicture(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.hero6)).setImageResource(getResources().getIdentifier(archdemon.getHero(5).getPicture(), "drawable", getActivity().getPackageName()));

        ((ImageView) rootView.findViewById(R.id.talent1)).setImageResource(getResources().getIdentifier(archdemon.getTalent(0).getTalentResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.talent2)).setImageResource(getResources().getIdentifier(archdemon.getTalent(1).getTalentResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.talent3)).setImageResource(getResources().getIdentifier(archdemon.getTalent(2).getTalentResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.talent4)).setImageResource(getResources().getIdentifier(archdemon.getTalent(3).getTalentResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.talent5)).setImageResource(getResources().getIdentifier(archdemon.getTalent(4).getTalentResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.talent6)).setImageResource(getResources().getIdentifier(archdemon.getTalent(5).getTalentResource(), "drawable", getActivity().getPackageName()));

        ((ImageView) rootView.findViewById(R.id.crest1)).setImageResource(getResources().getIdentifier(archdemon.getCrest(0).getCrestResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.crest2)).setImageResource(getResources().getIdentifier(archdemon.getCrest(1).getCrestResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.crest3)).setImageResource(getResources().getIdentifier(archdemon.getCrest(2).getCrestResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.crest4)).setImageResource(getResources().getIdentifier(archdemon.getCrest(3).getCrestResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.crest5)).setImageResource(getResources().getIdentifier(archdemon.getCrest(4).getCrestResource(), "drawable", getActivity().getPackageName()));
        ((ImageView) rootView.findViewById(R.id.crest6)).setImageResource(getResources().getIdentifier(archdemon.getCrest(5).getCrestResource(), "drawable", getActivity().getPackageName()));

        return rootView;
    }
}
