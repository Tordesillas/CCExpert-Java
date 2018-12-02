package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class PetProcessor {
    private NumberFormat f;
    private static final int[] EXP_BY_LEVEL = {0, 7500, 22500, 37500, 52500, 75000, 150000, 225000, 675000, 1350000};
    private static final int[] EXP_BY_LEVEL_RARE = {0, 10000, 30000, 50000, 70000, 100000, 200000, 300000, 900000, 1800000};
    private static final int[] EXP_BY_SACRIFICE = {100, 600, 3000, 15000};
    private static final int[] LEVEL_NEEDED = {2, 4, 6, 9, 13, 18, 23, 27, 35};

    public PetProcessor() {
        f = NumberFormat.getNumberInstance(Locale.FRANCE);
    }

    public String computeShard(int firstLevel, int secondLevel, boolean isRare) {
        return f.format(computeAmount(firstLevel, secondLevel, isRare) / 20);
    }

    public String computeExp(int firstLevel, int secondLevel, boolean isRare) {
        return f.format(computeAmount(firstLevel, secondLevel, isRare));
    }

    public String computeLevelNeeded(int secondLevel) {
        return LEVEL_NEEDED[secondLevel-2]+"";
    }

    private int computeAmount(int firstLevel, int secondLevel, boolean isRare) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            if (isRare) {
                for (int i = firstLevel; i < secondLevel; i++) {
                    amount += EXP_BY_LEVEL_RARE[i];
                }
            } else {
                for (int i = firstLevel; i < secondLevel; i++) {
                    amount += EXP_BY_LEVEL[i];
                }
            }
        }

        return amount;
    }

    public int[] computeSacrifices(int firstLevel, int secondLevel, boolean isRare) {
        int amount = computeAmount(firstLevel, secondLevel, isRare);
        int[] sacrifices = new int[EXP_BY_SACRIFICE.length];

        for (int i = EXP_BY_SACRIFICE.length - 1; i >= 0; i--) {
            sacrifices[i] = amount / EXP_BY_SACRIFICE[i];
            amount -= sacrifices[i] * EXP_BY_SACRIFICE[i];
        }

        return sacrifices;
    }
}
