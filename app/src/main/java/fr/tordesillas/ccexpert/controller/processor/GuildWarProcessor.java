package fr.tordesillas.ccexpert.controller.processor;

import android.content.res.Resources;
import java.text.NumberFormat;
import java.util.Locale;

import fr.tordesillas.ccexpert.R;

public class GuildWarProcessor {
    private Resources res;

    public GuildWarProcessor(Resources res) {
        this.res = res;
    }

    public String printStats(int power, int score) {
        double scoreOnPower = (power == 0) ? 0 : score / (double)power;
        int averagePower = (scoreOnPower < 0.005) ? 0 : Math.round(240 * score - 120000 - (float)1.5 * power);

        if (averagePower <= 0) {
            return res.getString(R.string.incorrectData);
        }

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return res.getString(R.string.processorText25) + f.format(averagePower) + res.getString(R.string.processorText26);
    }
}
