package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class ShardProcessor {
    private NumberFormat f;
    private static final int[] EXP_BY_LEVEL = {0, 2000, 10000, 30000, 70000, 120000, 200000, 500000, 800000, 1600000};
    private static final int[] EXP_BY_SACRIFICE = {100, 600, 3000, 15000};

    public ShardProcessor() {
        f = NumberFormat.getNumberInstance(Locale.FRANCE);
    }

    public String computeShard(int firstLevel, int secondLevel, String category) {
        return f.format(computeAmount(firstLevel, secondLevel, category) / 20);
    }

    public String computeExp(int firstLevel, int secondLevel, String category) {
        return f.format(computeAmount(firstLevel, secondLevel, category));
    }

    private int computeAmount(int firstLevel, int secondLevel, String category) {
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

        return amount;
    }

    public int[] computeSacrifices(int firstLevel, int secondLevel, String category) {
        int amount = computeAmount(firstLevel, secondLevel, category);
        int[] sacrifices = new int[EXP_BY_SACRIFICE.length];

        for (int i = EXP_BY_SACRIFICE.length - 1; i >= 0; i--) {
            sacrifices[i] = amount / EXP_BY_SACRIFICE[i];
            amount -= sacrifices[i] * EXP_BY_SACRIFICE[i];
        }

        return sacrifices;
    }
}
