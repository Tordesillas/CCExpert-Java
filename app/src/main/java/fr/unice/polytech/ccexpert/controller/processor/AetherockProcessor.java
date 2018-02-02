package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class AetherockProcessor {
    private static final int[] AETHEROCKS_BY_LEVEL = {0, 0, 8410, 12265, 17700, 25075, 34750, 47085, 62440, 81175, 103650, 130225, 161260, 197115, 238150, 284725, 337200, 395935, 461290, 533625, 613300};
    private static final int[] DAMAGES_BY_LEVEL = {0, 20, 44, 72, 104, 140, 180, 224, 272, 324, 380, 440, 504, 572, 644, 720, 800, 884, 972, 1064, 1160};
    private static final int[] HEALTH_POINTS_BY_LEVEL = {0, 500, 1100, 1800, 2600, 3500, 4500, 5600, 6800, 8100, 9500, 11000, 12600, 14300, 16100, 18000, 20000, 22100, 24300, 26600, 29000};

    public String printAetherockAmount(int firstLevel, int secondLevel) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i <= secondLevel; i++) {
                amount += AETHEROCKS_BY_LEVEL[i];
            }
        }

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return "Aura-guerrières à dépenser : " + f.format(amount) +
                "\nDégats supplémentaires : " + f.format(DAMAGES_BY_LEVEL[secondLevel]-DAMAGES_BY_LEVEL[firstLevel]) +
                "\nPoints de vie obtenus : " + f.format(HEALTH_POINTS_BY_LEVEL[secondLevel]-HEALTH_POINTS_BY_LEVEL[firstLevel]);
    }
}
