package fr.tordesillas.ccexpert.controller.processor;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import fr.tordesillas.ccexpert.R;

public class GuildWarProcessor {
    private Resources res;
    private static final double[] FAME_COEFF = {0.45, 0.39, 0.36, 0.33, 0.30};
    private static final int[] MAX_FAME = {3200, 2750, 2400, 2100, 1800};

    public GuildWarProcessor(Resources res) {
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
        int fame = Math.min((int)(score * (float) FAME_COEFF[position]), MAX_FAME[position]);

        return res.getString(R.string.processorText27) + fame + res.getString(R.string.processorText28);
    }
}
