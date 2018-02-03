package fr.unice.polytech.ccexpert.controller.processor;

import android.content.res.Resources;

import java.text.NumberFormat;
import java.util.Locale;

import fr.unice.polytech.ccexpert.R;

public class ShardProcessor {
    private Resources res;
    private static final int[] EXP_BY_LEVEL = {0, 2000, 10000, 30000, 70000, 120000, 200000, 500000, 800000, 1600000};

    public ShardProcessor(Resources res) {
        this.res = res;
    }

    public String printShardAmount(int firstLevel, int secondLevel, String category) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                amount += EXP_BY_LEVEL[i];
            }
        }
        switch (category) {
            case "Élite":
            case "Elite":
                amount *= 0.75;
                break;
            case "Ordinary":
            case "Ordinaire":
                amount *= 0.5;
        }

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return res.getString(R.string.processorText22) + f.format(amount) + res.getString(R.string.processorText23) +
                f.format(amount/20) + res.getString(R.string.processorText24);
    }
}
