package fr.unice.polytech.ccexpert.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import fr.unice.polytech.ccexpert.R;
import fr.unice.polytech.ccexpert.controller.activity.AccuracyActivity;
import fr.unice.polytech.ccexpert.controller.activity.AetherockActivity;
import fr.unice.polytech.ccexpert.controller.activity.AttackSpeedActivity;
import fr.unice.polytech.ccexpert.controller.activity.CrystalActivity;
import fr.unice.polytech.ccexpert.controller.activity.DestinyActivity;
import fr.unice.polytech.ccexpert.controller.activity.DodgeActivity;
import fr.unice.polytech.ccexpert.controller.activity.GuildWarActivity;
import fr.unice.polytech.ccexpert.controller.activity.ProtectorsActivity;
import fr.unice.polytech.ccexpert.controller.activity.ShardActivity;
import fr.unice.polytech.ccexpert.view.CardAdapter;

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
                getResources().getString(R.string.guildWar),
                getResources().getString(R.string.shard),
                getResources().getString(R.string.crystal),
                getResources().getString(R.string.aetherock),
                getResources().getString(R.string.dodge),
                getResources().getString(R.string.accuracy),
                getResources().getString(R.string.attackSpeed),
                getResources().getString(R.string.destiny),
                getResources().getString(R.string.protectors)
        );

        ListAdapter la = new CardAdapter(this.getContext(), titles);
        GridView gridView = getView().findViewById(R.id.grid);
        gridView.setAdapter(la);

        gridView.setOnItemClickListener((parent, v, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), GuildWarActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), ShardActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), CrystalActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), AetherockActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(getActivity(), DodgeActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(getActivity(), AccuracyActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(getActivity(), AttackSpeedActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(getActivity(), DestinyActivity.class));
                    break;
                case 8:
                    startActivity(new Intent(getActivity(), ProtectorsActivity.class));
            }
        });

        super.onActivityCreated(bundle);
    }
}
