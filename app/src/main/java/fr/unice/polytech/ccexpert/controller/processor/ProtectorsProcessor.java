package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class ProtectorsProcessor {
    private static final int[] MERIT_BY_LEVEL = {0, 4000, 4000, 5500, 5500, 6300, 7000, 7000, 8500, 8500, 9300, 10000, 10000, 11500, 11500, 12800, 14000, 14000, 16500, 16500, 17800, 19000, 19000, 22500, 22500, 24300, 26000, 26000, 29500, 29500, 31800, 34000, 34000, 38500, 38500, 40800, 43000, 43000, 48500, 48500, 51300, 54000, 54000, 59500, 59500, 62800, 66000, 66000};
    private static final int[] BIRTHROCK_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 10, 10, 20, 25, 25, 60, 60, 90, 120, 120, 250, 250, 400, 600, 600, 900, 900, 1200, 1500, 1500, 1800, 1800, 2100, 2400, 2400};
    private static final int[] ATTACK_BY_LEVEL_PROPHET = {0, 16, 36, 36, 60, 60, 60, 88, 88, 120, 120, 120, 156, 156, 196, 196, 196, 240, 240, 288, 288, 288, 340, 340, 396, 396, 396, 456, 456, 520, 520, 520, 588, 588, 660, 660, 660, 736, 736, 816, 816, 816, 900, 900, 988, 988, 988, 1080, 1080};
    private static final int[] ATTACK_BY_LEVEL_SAINT = {0, 10, 23, 23, 39, 39, 39, 57, 57, 78, 78, 78, 101, 101, 127, 127, 127, 156, 156, 187, 187, 187, 221, 221, 257, 257, 257, 296, 296, 338, 338, 338, 382, 382, 429, 429, 429, 478, 478, 530, 530, 530, 585, 585, 642, 642, 642, 702, 702};
    private static final int[] ATTACK_BY_LEVEL_BRAWLER = {0, 25, 56, 56, 93, 93, 93, 136, 136, 185, 185, 185, 240, 240, 302, 302, 302, 370, 370, 444, 444, 444, 524, 524, 610, 610, 610, 702 ,702, 800, 800, 800, 905, 905, 1016, 1016, 1016, 1133, 1133, 1256, 1256, 1256, 1385, 1385, 1520, 1520, 1520, 1662, 1662};
    private static final int[] HP_BY_LEVEL_PROPHET = {0, 400, 900, 900, 1500, 1500, 1500, 2200, 2200, 3000, 3000, 3000, 3900, 3900, 4900, 4900, 4900, 6000, 6000, 7200, 7200, 7200, 8500, 8500, 9900, 9900, 9900, 11400, 11400, 13000, 13000, 13000, 14700, 14700, 16500, 16500, 16500, 18400, 18400, 20400, 20400, 20400, 22500, 22500, 24700, 24700, 24700, 27000, 27000};
    private static final int[] HP_BY_LEVEL_SAINT = {0, 620, 1390, 1390, 2310, 2310, 2310, 3390, 3390, 4620, 4620, 4620, 6000, 6000, 7540, 7540, 7540, 9230, 9230, 11080, 11080, 11080, 13080, 13080, 15230, 15230, 15230, 17540, 17540, 20000, 20000, 20000, 22620, 22620, 25390, 25390, 25390, 28310, 28310, 31390, 31390, 31390, 34620, 34620, 38000, 38000, 38000, 41540, 41540};
    private static final int[] HP_BY_LEVEL_BRAWLER = {0, 260, 590, 590, 980, 980, 980, 1440, 1440, 1960, 1960, 1960, 2550, 2550, 3200, 3200, 3200, 3920, 3920, 4700, 4700, 4700, 5550, 5550, 6460, 6460, 6460, 7440, 7440, 8480, 8480, 8480, 9590, 9590, 10760, 10760, 10760, 12000, 12000, 13300, 13300, 13300, 14670, 14670, 16100, 16100, 16100, 17600, 17600};

    public String computeMerit(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, MERIT_BY_LEVEL);
    }

    public String computeBirthrock(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, BIRTHROCK_BY_LEVEL);
    }

    public String computeAttack(int firstLevel, int secondLevel, int position) {
        switch (position) {
            case 0:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(ATTACK_BY_LEVEL_SAINT[secondLevel] - ATTACK_BY_LEVEL_SAINT[firstLevel]);
            case 1:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(ATTACK_BY_LEVEL_BRAWLER[secondLevel] - ATTACK_BY_LEVEL_BRAWLER[firstLevel]);
            default:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(ATTACK_BY_LEVEL_PROPHET[secondLevel] - ATTACK_BY_LEVEL_PROPHET[firstLevel]);
        }
    }

    public String computeHP(int firstLevel, int secondLevel, int position) {
        switch (position) {
            case 0:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(HP_BY_LEVEL_SAINT[secondLevel] - HP_BY_LEVEL_SAINT[firstLevel]);
            case 1:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(HP_BY_LEVEL_BRAWLER[secondLevel] - HP_BY_LEVEL_BRAWLER[firstLevel]);
            default:
                return NumberFormat.getNumberInstance(Locale.FRANCE).format(HP_BY_LEVEL_PROPHET[secondLevel] - HP_BY_LEVEL_PROPHET[firstLevel]);
        }
    }

    public String computeDodge(int firstLevel, int secondLevel) {
        int dod1 = ((firstLevel + 1) / 6) * 35;
        int dod2 = ((secondLevel + 1) / 6) * 35;
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(dod2 - dod1);
    }

    public String computeAccuracy(int firstLevel, int secondLevel) {
        int acc1 = ((firstLevel + 2) / 6) * 35;
        int acc2 = ((secondLevel + 2) / 6) * 35;
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(acc2 - acc1);
    }

    public String computeTenacity(int firstLevel, int secondLevel) {
        int tena1 = ((firstLevel + 4) / 6) * 6;
        int tena2 = ((secondLevel + 4) / 6) * 6;
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(tena2 - tena1);
    }

    public String computeCrit(int firstLevel, int secondLevel) {
        int crit1 = ((firstLevel + 5) / 6) * 6;
        int crit2 = (secondLevel / 6 + 1) * 6;
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(crit2 - crit1);
    }

    public String computeDmg(int firstLevel, int secondLevel) {
        int dmg1 = (firstLevel / 3) * 75;
        int dmg2 = (secondLevel / 3) * 75;
        return NumberFormat.getNumberInstance(Locale.FRANCE).format(dmg2 - dmg1);
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
