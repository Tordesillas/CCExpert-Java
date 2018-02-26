package fr.unice.polytech.ccexpert.controller.processor;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import fr.unice.polytech.ccexpert.R;

public class StatsGuildWar {
    private Resources res;
    private static final double[] fameTab = {0.45, 0.39, 0.36, 0.33, 0.30};

    public StatsGuildWar(Resources res) {
        this.res = res;
    }

    public String printStats(Context context, int power, int score) {
        double scoreOnPower = (power == 0) ? 0 : score / (double)power;
        int averagePower = (scoreOnPower < 0.005) ? 0 : Math.round(240 * score - 120000 - (float)1.5 * power);

        if (averagePower <= 0) {
            Toast.makeText(context, res.getString(R.string.incorrectData), Toast.LENGTH_SHORT).show();
            return null;
        }

        return res.getString(R.string.processorText25) + averagePower + res.getString(R.string.processorText26);
    }

    public String printFameStats(int score, int position) {
        int fame = (int)(score * (float)fameTab[position]);

        return res.getString(R.string.processorText27) + fame + res.getString(R.string.processorText28);
    }
}
