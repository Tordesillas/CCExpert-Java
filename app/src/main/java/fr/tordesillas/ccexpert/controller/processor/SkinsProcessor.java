package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class SkinsProcessor {
    private static final int[] HP_BY_LEVEL = {10000, 2625, 0, 0, 3250, 0, 0, 4125, 0, 0, 5250, 0, 0, 6625, 0, 0, 8250, 0, 0, 10125, 0, 0, 12250, 0, 0, 14625, 0, 0, 17250, 0};
    private static final int[] ATK_BY_LEVEL = {0, 105, 0, 0, 130, 0, 0, 165, 0, 0, 210, 0, 0, 265, 0, 0, 330, 0, 0, 405, 0, 0, 490, 0, 0, 585, 0, 0, 690, 0};
    private static final int[] TENA_BY_LEVEL = {0, 0, 15, 0, 0, 25, 0, 0, 35, 0, 0, 45, 0, 0, 55, 0, 0, 55, 0, 0, 55, 0, 0, 55, 0, 0, 55, 0, 0, 55};
    private static final int[] DODGE_BY_LEVEL = {0, 0, 0, 75, 0, 0, 105, 0, 0, 135, 0, 0, 165, 0, 0, 195, 0, 0, 195, 0, 0, 195, 0, 0, 195, 0, 0, 195, 0, 0};
    private static final int[] SHARD_BY_LEVEL = {0, 560, 840, 1120, 1400, 1680, 1960, 2240, 2520, 2800, 3080, 3360, 3640, 3920, 4200, 4480, 4760, 5040, 5320, 5600, 6160, 6720, 7280, 7840, 8400, 8960, 9520, 10080, 10640, 11200};
    private static final int SCRAP_FOR_A_LEVEL = 5;

    public String computeShard(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, SHARD_BY_LEVEL);
    }

    public String computeScrap(int firstLevel, int secondLevel) {
        int amount = (firstLevel == 0) ?
                SCRAP_FOR_A_LEVEL * sum(secondLevel) - SCRAP_FOR_A_LEVEL :
                SCRAP_FOR_A_LEVEL * sum(secondLevel) - SCRAP_FOR_A_LEVEL * sum(firstLevel);
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(amount);
    }

    public String computeAttack(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, ATK_BY_LEVEL);
    }

    public String computeHP(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, HP_BY_LEVEL);
    }

    public String computeDodge(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, DODGE_BY_LEVEL);
    }

    public String computeAccuracy(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, DODGE_BY_LEVEL);
    }

    public String computeTenacity(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, TENA_BY_LEVEL);
    }

    public String computeCrit(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, TENA_BY_LEVEL);

    }

    private String computeEverything(int firstLevel, int secondLevel, int[] array) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                amount += array[i];
            }
        }
        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return f.format(amount);
    }

    private int sum(int n) {
        return n * (n + 1) / 2;
    }
}
