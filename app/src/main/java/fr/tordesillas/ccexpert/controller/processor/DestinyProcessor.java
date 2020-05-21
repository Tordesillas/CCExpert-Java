package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class DestinyProcessor {
    private static final int[] GOLD_BY_LEVEL = {0, 0, 100000, 100000, 0, 300000, 300000, 300000, 300000, 0, 600000, 600000, 600000, 600000, 0, 1200000, 1200000, 1200000, 1200000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] IH_BY_LEVEL = {0, 0, 3000, 3000, 0, 6500, 6500, 6500, 6500, 0, 10500, 10500, 10500, 10500, 0, 15000, 15000, 15000, 15000, 0, 20000, 20000, 20000, 20000, 0, 25500, 25500, 25500, 25500, 0, 31500, 31500, 31500, 31500, 0, 38000, 38000, 38000, 38000, 0, 45000, 45000, 45000, 45000, 0, 52500, 52500, 52500, 52500, 0, 60500, 60500, 60500, 60500, 0, 69000, 69000, 69000, 69000, 0, 78000, 78000, 78000, 78000, 0, 88000, 88000, 88000, 88000, 0, 100000, 100000, 100000, 100000, 0, 115000, 115000, 115000, 115000, 0, 135000, 135000, 135000, 135000, 0, 165000, 165000, 165000, 165000, 0, 210000, 210000, 210000, 210000, 0, 270000, 270000, 270000, 270000, 0};
    private static final int[] BLUE_CRYSTALS_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5970, 5970, 5970, 5970, 0, 27420, 27420, 27420, 27420, 0, 57050, 57050, 57050, 57050, 0, 92250, 92250, 92250, 92250, 0, 125000, 125000, 125000, 125000, 0, 179000, 179000, 179000, 179000, 0, 239000, 239000, 239000, 239000, 0, 304000, 304000, 304000, 304000, 0, 380000, 380000, 380000, 380000, 0, 470000, 470000, 470000, 470000, 0, 570000, 570000, 570000, 570000, 0, 680000, 680000, 680000, 680000, 0, 800000, 800000, 800000, 800000, 0, 1000000, 1000000, 1000000, 1000000, 0, 1280000, 1280000, 1280000, 1280000, 0, 1600000, 1600000, 1600000, 1600000, 0};
    private static final int[] HERO_CARDS_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8};
    private static final int[] KARMIC1_BY_LEVEL = {0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 0, 0, 0, 18, 0, 0, 0, 0, 24, 0, 0, 0, 0, 20, 0, 0, 0, 0, 20, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] KARMIC2_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 16, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] KARMIC3_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 15, 0, 0, 0, 0, 6, 0, 0, 0, 0, 6, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0};
    private static final int[] KARMIC4_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 10, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4, 0, 0, 0, 0, 12};
    private static final int[] KARMIC5_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6};

    public String computeGold(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, GOLD_BY_LEVEL);
    }

    public String computeIH(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, IH_BY_LEVEL);
    }

    public String computeCrystals(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, BLUE_CRYSTALS_BY_LEVEL);
    }

    public String computeHeroCards(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, HERO_CARDS_BY_LEVEL);
    }

    public String computeKarmic1(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, KARMIC1_BY_LEVEL);
    }

    public String computeKarmic2(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, KARMIC2_BY_LEVEL);
    }

    public String computeKarmic3(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, KARMIC3_BY_LEVEL);
    }

    public String computeKarmic4(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, KARMIC4_BY_LEVEL);
    }

    public String computeKarmic5(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, KARMIC5_BY_LEVEL);
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
