package fr.tordesillas.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.tordesillas.ccexpert.R;
import fr.tordesillas.ccexpert.controller.activity.AetherockActivity;
import fr.tordesillas.ccexpert.controller.activity.AttackSpeedActivity;
import fr.tordesillas.ccexpert.controller.activity.BreakthroughLevelsActivity;
import fr.tordesillas.ccexpert.controller.activity.InscriptionActivity;
import fr.tordesillas.ccexpert.controller.activity.DestinyActivity;
import fr.tordesillas.ccexpert.controller.activity.DodgeActivity;
import fr.tordesillas.ccexpert.controller.activity.PetLevelActivity;
import fr.tordesillas.ccexpert.controller.activity.ProtectorsActivity;
import fr.tordesillas.ccexpert.controller.activity.RelicActivity;
import fr.tordesillas.ccexpert.controller.activity.RollActivity;
import fr.tordesillas.ccexpert.controller.activity.ShardActivity;
import fr.tordesillas.ccexpert.controller.activity.SkinsActivity;
import fr.tordesillas.ccexpert.view.CardAdapter;

public class SimulatorsFragment extends Fragment {
    public static SimulatorsFragment newInstance() {
        SimulatorsFragment fragment = new SimulatorsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        final List<String> titles = Arrays.asList(
                getResources().getString(R.string.shard),
                getResources().getString(R.string.inscription),
                getResources().getString(R.string.aetherock),
                getResources().getString(R.string.dodge),
                getResources().getString(R.string.attackSpeed),
                getResources().getString(R.string.destiny),
                getResources().getString(R.string.protectors),
                getResources().getString(R.string.skinTitle),
                getResources().getString(R.string.roll),
                getResources().getString(R.string.petLevel),
                getResources().getString(R.string.breakthroughLevels),
                getResources().getString(R.string.relic)
        );

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), ShardActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), InscriptionActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), AetherockActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), DodgeActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getActivity(), AttackSpeedActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(getActivity(), DestinyActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(getActivity(), ProtectorsActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(getActivity(), SkinsActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(getActivity(), RollActivity.class));
                    break;
                case 9:
                    startActivity(new Intent(getActivity(), PetLevelActivity.class));
                    break;
                case 10:
                    startActivity(new Intent(getActivity(), BreakthroughLevelsActivity.class));
                    break;
                case 11:
                    startActivity(new Intent(getActivity(), RelicActivity.class));
                    break;
            }
        });

        super.onActivityCreated(bundle);
    }
}
