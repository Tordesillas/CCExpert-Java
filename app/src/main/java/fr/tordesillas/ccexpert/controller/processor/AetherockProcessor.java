package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class AetherockProcessor {
    private NumberFormat f;
    private static final int[] AETHEROCKS_BY_LEVEL = {0, 8410, 12265, 17700, 25075, 34750, 47085, 62440, 81175, 103650, 130225, 161260, 197115, 238150, 284725, 337200, 395935, 461290, 533625, 613300, 700675, 796110, 899965, 1012600, 1134375, 1265650, 1406785, 1558140, 1720075, 1892950, 2077125, 2272960, 2480815, 2701050, 2934025, 3180100, 3439635, 3712990, 4000525, 4302600, 4619575, 4951810, 5299665, 5663500, 6043675, 6440550, 6854485, 7285840, 7734975, 8202250, 8688025, 9192660, 9716515, 10259950, 10823325, 11407000, 12011335, 12636690, 13283425, 13951900};
    private static final int[] EQUIPMENT_TOMES_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] DAMAGES_BY_LEVEL = {0, 20, 44, 72, 104, 140, 180, 224, 272, 324, 380, 440, 504, 572, 644, 720, 800, 884, 972, 1064, 1160, 1260, 1364, 1472, 1584, 1700, 1820, 1944, 2072, 2204, 2340, 2480, 2624, 2772, 2924, 3080, 3240, 3404, 3572, 3744, 3920, 4100, 4284, 4472, 4664, 4860, 5060, 5264, 5472, 5684, 5900, 6120, 6344, 6572, 6804, 7040, 7280, 7524, 7772, 8024, 8280};
    private static final int[] HEALTH_POINTS_BY_LEVEL = {0, 500, 1100, 1800, 2600, 3500, 4500, 5600, 6800, 8100, 9500, 11000, 12600, 14300, 16100, 18000, 20000, 22100, 24300, 26600, 29000, 31500, 34100, 36800, 39600, 42500, 45500, 48600, 51800, 55100, 58500, 62000, 65600, 69300, 73100, 77000, 81000, 85100, 89300, 93600, 98000, 102500, 107100, 111800, 116600, 121500, 126500, 131600, 136800, 142100, 147500, 153000, 158600, 164300, 170100, 176000, 182000, 188100, 194300, 200600, 207000};

    public AetherockProcessor() {
        f = NumberFormat.getNumberInstance(Locale.FRANCE);
    }

    public String computeAetherock(int firstLevel, int secondLevel) {
        int amount = 0;
        for (int i = firstLevel; i < secondLevel; i++) {
            amount += AETHEROCKS_BY_LEVEL[i];
        }
        return f.format(amount);
    }

    public String computeEquipmentTome(int firstLevel, int secondLevel) {
        int amount = 0;
        for (int i = firstLevel; i < secondLevel; i++) {
            amount += EQUIPMENT_TOMES_BY_LEVEL[i];
        }
        return f.format(amount);
    }

    public String computeAttack(int firstLevel, int secondLevel) {
        return f.format(DAMAGES_BY_LEVEL[secondLevel] - DAMAGES_BY_LEVEL[firstLevel]);
    }

    public String computeHealth(int firstLevel, int secondLevel) {
        return f.format(HEALTH_POINTS_BY_LEVEL[secondLevel] - HEALTH_POINTS_BY_LEVEL[firstLevel]);
    }
}
