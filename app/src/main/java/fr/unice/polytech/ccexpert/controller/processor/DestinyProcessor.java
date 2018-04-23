package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class DestinyProcessor {
    private static final int[] GOLD_BY_LEVEL = {0, 0, 100000, 100000, 0, 300000, 300000, 300000, 300000, 0, 600000, 600000, 600000, 600000, 0, 1200000, 1200000, 1200000, 1200000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] IH_BY_LEVEL = {0, 0, 3000, 3000, 0, 6500, 6500, 6500, 6500, 0, 10500, 10500, 10500, 10500, 0, 15000, 15000, 15000, 15000, 0, 20000, 20000, 20000, 20000, 0, 25500, 25500, 25500, 25500, 0, 31500, 31500, 31500, 31500, 0, 38000, 38000, 38000, 38000, 0};
    private static final int[] FAME_BY_LEVEL = {0, 0, 0, 0, 4800, 0, 0, 0, 0, 4800, 0, 0, 0, 0, 5400, 0, 0, 0, 0, 18400, 0, 0, 0, 0, 6000, 0, 0, 0, 0, 6000, 0, 0, 0, 0, 8400, 0, 0, 0, 0, 24000};
    private static final int[] HERO_CARDS_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2};
    private static final int[] BLUE_CRYSTALS_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5970, 5970, 5970, 5970, 0, 27420, 27420, 27420, 27420, 0, 57050, 57050, 57050, 57050, 0, 92250, 92250, 92250, 92250, 0};

    public String computeGold(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, GOLD_BY_LEVEL);
    }

    public String computeIH(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, IH_BY_LEVEL);
    }

    public String computeFame(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, FAME_BY_LEVEL);
    }

    public String computeHeroCards(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, HERO_CARDS_BY_LEVEL);
    }

    public String computeCrystals(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, BLUE_CRYSTALS_BY_LEVEL);
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
}
