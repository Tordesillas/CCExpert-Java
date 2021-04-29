package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class RelicProcessor {
    private NumberFormat f;
    private static final int[] ATTACK_BY_LEVEL = {0, 8, 24, 48, 80, 120, 168, 224, 288, 360, 440, 528, 624, 728, 840, 960, 1088, 1224, 1368, 1520, 1680, 1848, 2024, 2208, 2400, 2600, 2808, 3024, 3248, 3480, 3720, 3968, 4224, 4488, 4760, 5040, 5328, 5624, 5928, 6240, 6560, 6888, 7224, 7568, 7920, 8280, 8648, 9024, 9408, 9800, 10200, 10608, 11024, 11448, 11880, 12320, 12768, 13224, 13688, 14160, 14640};
    private static final int[] ATTACK_BY_LEVEL_EPIC = {0, 15, 47, 95, 159, 239, 335, 447, 575, 719, 879, 1055, 1247, 1455, 1679, 1919, 2175, 2447, 2735, 3039, 3359, 3695, 4047, 4415, 4799, 5199, 5615, 6047, 6495, 6959, 7439, 7935, 8447, 8975, 9519, 10079, 10655, 11247, 11855, 12479, 13119, 13775, 14447, 15135, 15839, 16559, 17295, 18047, 18815, 19599, 20399, 21215, 22047, 22895, 23759, 24639, 25535, 26447, 27375, 28319, 29279};
    private static final int[] LIFE_BY_LEVEL = {0, 240, 720, 1440, 2400, 3600, 5040, 6720, 8640, 10800, 13200, 15840, 18720, 21840, 25200, 28800, 32640, 36720, 41040, 45600, 50400, 55440, 60720, 66240, 72000, 78000, 84240, 90720, 97440, 104400, 111600, 119040, 126720, 134640, 142800, 151200, 159840, 168720, 177840, 187200, 196800, 206640, 216720, 227040, 237600, 248400, 259440, 270720, 282240, 294000, 306000, 318240, 330720, 343440, 356400, 369600, 383040, 396720, 410640, 424800, 439200};
    private static final int[] LIFE_BY_LEVEL_EPIC = {0, 450, 1410, 2850, 4770, 7170, 10050, 13410, 17250, 21570, 26370, 31650, 37410, 43650, 50370, 57570, 65250, 73410, 82050, 91170, 100770, 110850, 121410, 132450, 143970, 155970, 168450, 181410, 194850, 208770, 223170, 238050, 253410, 269250, 285570, 302370, 319650, 337410, 355650, 374370, 393570, 413250, 433410, 454050, 475170, 496770, 518850, 541410, 564450, 587970, 611970, 636450, 661410, 686850, 712770, 739170, 766050, 793410, 821250, 849570, 878370};
    private static final int[] SHARDS_BY_LEVEL = {0, 300, 600, 900, 1200, 1500, 1800, 2100, 2400, 2700, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10300, 11100, 11900, 12700, 13500, 14300, 15100, 16100, 17200, 18500, 20000, 21500, 23000, 24500, 26000, 27000, 29000, 31000, 33000, 35000, 37000, 39000, 41000, 43000, 45000, 47000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] CARDS_BY_LEVEL = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 2};
    private static final int[] CARDS_BY_LEVEL_EPIC = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 3};
    private static final int[] VESTIGES_BY_LEVEL = {0, 0, 0, 0, 0, 0, 5, 10, 15, 20, 0, 25, 30, 35, 40, 0, 45, 50, 55, 60, 0, 65, 70, 75, 80, 0, 85, 90, 95, 100, 0, 110, 120, 130, 140, 0, 150, 160, 170, 180, 0, 195, 210, 225, 240, 0, 255, 270, 285, 300, 0, 320, 340, 360, 380, 0, 400, 420, 440, 460, 0, 0};
    private static final int[] MARKS_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 30, 40, 50, 60, 80, 100, 120, 140, 160, 160};

    public RelicProcessor() {
        f = NumberFormat.getNumberInstance(Locale.FRANCE);
    }

    public String computeAttack(int firstLevel, int secondLevel, boolean isEpic) {
        if (isEpic) {
            return f.format(ATTACK_BY_LEVEL_EPIC[secondLevel] - ATTACK_BY_LEVEL_EPIC[firstLevel]);
        }
        return f.format(ATTACK_BY_LEVEL[secondLevel] - ATTACK_BY_LEVEL[firstLevel]);
    }

    public String computeLife(int firstLevel, int secondLevel, boolean isEpic) {
        if (isEpic) {
            return f.format(LIFE_BY_LEVEL_EPIC[secondLevel] - LIFE_BY_LEVEL_EPIC[firstLevel]);
        }
        return f.format(LIFE_BY_LEVEL[secondLevel] - LIFE_BY_LEVEL[firstLevel]);
    }

    public String computeShards(int firstLevel, int secondLevel, boolean isEpic) {
        int shards = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                shards += SHARDS_BY_LEVEL[i];
            }
        }
        if (!isEpic) {
            shards /= 2;
        }

        return f.format(shards);
    }

    public String computeCards(int firstLevel, int secondLevel, boolean isEpic) {
        int cards = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            cards += isEpic ? CARDS_BY_LEVEL_EPIC[i] : CARDS_BY_LEVEL[i];
        }
        return f.format(cards);
    }

    public String computeVestiges(int firstLevel, int secondLevel) {
        int vestiges = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            vestiges += VESTIGES_BY_LEVEL[i];
        }
        return f.format(vestiges);
    }

    public String computeLegendaryMarks(int firstLevel, int secondLevel, boolean isEpic) {
        if (isEpic) {
            return "0";
        }
        int marks = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            marks += MARKS_BY_LEVEL[i];
        }
        return f.format(marks);
    }

    public String computeEpicMarks(int firstLevel, int secondLevel, boolean isEpic) {
        if (!isEpic) {
            return "0";
        }
        int marks = 0;
        for (int i = firstLevel + 1; i <= secondLevel; i++) {
            marks += MARKS_BY_LEVEL[i];
        }
        return f.format(marks);
    }
}
