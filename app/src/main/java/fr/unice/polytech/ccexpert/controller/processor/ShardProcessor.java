package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class ShardProcessor {
    private static final int[] EXP_BY_LEVEL = {0, 2000, 10000, 30000, 70000, 120000, 200000, 500000, 800000, 1600000};

    public String printShardAmount(int firstLevel, int secondLevel, String category) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                amount += EXP_BY_LEVEL[i];
            }
        }
        switch (category) {
            case "Élite":
                amount *= 0.75; break;
            case "Ordinaire":
                amount *= 0.5;
        }

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);
        return "Pour acquérir " + f.format(amount) + " points d'expérience, " +
                "il faudra dépenser " + f.format(amount/20) + " fragments.";
    }
}
