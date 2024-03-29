package fr.tordesillas.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class BreakthroughLevelsProcessor {
    private static final int[] EXP_BY_LEVEL = {0, 2060000, 2180000, 2300000, 2420000, 2540000, 0, 2660000, 2780000, 2900000, 3060000, 3220000, 0, 3380000, 3540000, 3700000, 3900000, 4100000, 0, 4300000, 4500000, 4700000, 4940000, 5180000, 0, 5420000, 5660000, 5900000, 6180000, 6460000, 0, 6740000, 7020000, 7300000, 7620000, 7940000, 0, 8260000, 8580000, 8900000, 9260000, 9620000, 0, 9980000, 10340000, 10700000, 11100000, 11500000, 0, 11900000, 12300000, 12700000, 13140000, 13580000, 0, 14020000, 14460000, 14900000, 15380000, 15860000, 0, 16340000, 16820000, 17300000, 17860000, 18420000, 0, 18980000, 19540000, 20100000, 20740000, 21380000, 0, 22020000, 22660000, 23300000, 24100000, 24900000, 0, 25700000, 26500000, 27300000, 28260000, 29220000, 0, 30180000, 31140000, 32100000, 33220000, 34340000, 0, 35460000, 36580000, 37700000, 38980000, 40260000, 0, 41540000, 42820000, 44100000, 45540000, 46980000, 0, 48420000, 49860000, 51300000, 52900000, 54500000, 0, 56100000, 57700000, 59300000, 61060000, 62820000, 0, 64580000, 66340000, 68100000, 70100000, 72100000, 0, 74100000, 76100000, 78100000, 80500000, 82900000, 0 ,85300000, 87700000, 90100000, 92900000, 95700000, 0, 98500000, 101300000, 104100000, 107300000, 110500000, 0, 113700000, 116900000, 120100000, 124100000, 128100000, 0, 132100000, 136100000, 140100000, 144900000, 149700000, 0, 154500000, 159300000, 164100000, 169700000, 175300000, 0, 180900000, 186500000, 192100000, 198500000, 204900000, 0, 211300000, 217700000, 224100000, 231300000, 238500000, 0, 245700000, 252900000, 260100000, 267300000, 274500000, 0, 285700000, 292900000, 300100000, 307300000, 314500000, 0, 329700000, 336900000, 344100000, 351300000, 358500000, 0, 377700000, 384900000, 392100000, 399300000, 406500000, 0, 429700000, 436900000, 444100000, 451300000, 458500000, 0, 485700000, 492900000, 500100000, 507300000, 514500000, 0, 545700000, 552900000, 560100000, 567300000, 574500000, 0, 609700000, 616900000, 624100000, 631300000, 638500000, 0, 677700000, 684900000, 692100000, 699300000, 706500000, 0, 749700000, 756900000, 764100000, 771300000, 778500000, 0, 825700000, 832900000, 840100000, 847300000, 854500000};
    private static final int[] ATK_BY_LEVEL = {6, 6, 6, 6, 6, 90, 9, 9, 9, 9, 9, 0, 12, 12, 12, 12, 12, 180, 15, 15, 15, 15, 15, 0, 18, 18, 18, 18, 18, 270, 21, 21, 21, 21, 21, 315, 24, 24, 24, 24, 24, 0, 27, 27, 27, 27, 27, 405, 30, 30, 30, 30, 30, 0, 33, 33, 33, 33, 33, 495, 36, 36, 36, 36, 36, 540, 39, 39, 39, 39, 39, 0, 42, 42, 42, 42, 42, 630, 45, 45, 45, 45, 45, 0, 48, 48, 48, 48, 48, 720, 51, 51, 51, 51, 51, 765, 54, 54, 54, 54, 54, 0, 57, 57, 57, 57, 57, 855, 60, 60, 60, 60, 60, 0, 70, 70, 70, 70, 70, 1050, 80, 80, 80, 80, 80, 1200, 92, 92, 92, 92, 92, 0, 104, 104, 104, 104, 104, 1560, 118, 118, 118, 118, 118, 0, 132, 132, 132, 132, 132, 1980, 147, 147, 147, 147, 147, 2205, 162, 162, 162, 162, 162, 0, 178, 178, 178, 178, 178, 2670, 194, 194, 194, 194, 194, 0, 211, 211, 211, 211, 211, 3165, 228, 228, 228, 228, 228, 3420, 246, 246, 246, 246, 246, 0, 264, 264, 264, 264, 264, 3960, 283, 283, 283, 283, 283, 0, 302, 302, 302, 302, 302, 4530, 322, 322, 322, 322, 322, 4830, 342, 342, 342, 342, 342, 0, 363, 363, 363, 363, 363, 5445, 384, 384, 384, 384, 384, 0};
    private static final int[] HP_BY_LEVEL = {180, 180, 180, 180, 180, 0, 270, 270, 270, 270, 270, 2700, 360, 360, 360, 360, 360, 0, 450, 450, 450, 450, 450, 5400, 540, 540, 540, 540, 540, 8100, 630, 630, 630, 630, 630, 0, 720, 720, 720, 720, 720, 9450, 810, 810, 810, 810, 810, 0, 900, 900, 900, 900, 900, 12150, 990, 990, 990, 990, 990, 14850, 1080, 1080, 1080, 1080, 1080, 0, 1170, 1170, 1170, 1170, 1170, 16200, 1260, 1260, 1260, 1260, 1260, 0, 1350, 1350, 1350, 1350, 1350, 18900, 1440, 1440, 1440, 1440, 1440, 21600, 1530, 1530, 1530, 1530, 1530, 0, 1620, 1620, 1620, 1620, 1620, 22950, 1710, 1710, 1710, 1710, 1710, 0, 1800, 1800, 1800, 1800, 1800, 25650, 2100, 2100, 2100, 2100, 2100, 31500, 2400, 2400, 2400, 2400, 2400, 0, 2760, 2760, 2760, 2760, 2760, 36000, 3120, 3120, 3120, 3120, 3120, 0, 3540, 3540, 3540, 3540, 3540, 46800, 3960, 3960, 3960, 3960, 3960, 59400, 4410, 4410, 4410, 4410, 4410, 0, 4860, 4860, 4860, 4860, 4860, 66150, 5340, 5340, 5340, 5340, 5340, 0, 5820, 5820, 5820, 5820, 5820, 80100, 6330, 6330, 6330, 6330, 6330, 94950, 6840, 6840, 6840, 6840, 6840, 0, 7380, 7380, 7380, 7380, 7380, 102600, 7920, 7920, 7920, 7920, 7920, 0, 8490, 8490, 8490, 8490, 8490, 118800, 9060, 9060, 9060, 9060, 9060, 135900, 9660, 9660, 9660, 9660, 9660, 0, 10260, 10260, 10260, 10260, 10260, 144900, 10890, 10890, 10890, 10890, 10890, 0, 11520, 11520, 11520, 11520, 11520, 163350};
    private static final int[] ACC_BY_LEVEL = {0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 140, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 350, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 460, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 540, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] DODGE_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 140, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 350, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 460, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 540, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] CRIT_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 110, 0, 0, 0, 0, 0, 0};
    private static final int[] TENA_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 70, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 110};
    private static final int[] IH_BY_LEVEL = {0, 3000, 3000, 3000, 3000, 0, 5000, 5000, 5000, 5000, 5000, 0, 7000, 7000, 7000, 7000, 7000, 0, 9000, 9000, 9000, 9000, 9000, 0, 11000, 11000, 11000, 11000, 11000, 0, 13000, 13000, 13000, 13000, 13000, 0, 15000, 15000, 15000, 15000, 15000, 0, 17000, 17000, 17000, 17000, 17000, 0, 19000, 19000, 19000, 19000, 19000, 0, 21000, 21000, 21000, 21000, 21000, 0, 24000, 24000, 24000, 24000, 24000, 0, 28000, 28000, 28000, 28000, 28000, 0, 32000, 32000, 32000, 32000, 32000, 0, 36000, 36000, 36000, 36000, 36000, 0, 40000, 40000, 40000, 40000, 40000, 0, 45000, 45000, 45000, 45000, 45000, 0, 45000, 45000, 45000, 45000, 45000, 0, 50000, 50000, 50000, 50000, 50000, 0, 60000, 60000, 60000, 60000, 60000, 0, 75000, 75000, 75000, 75000, 75000, 0, 90000, 90000, 90000, 90000, 90000, 0, 105000, 105000, 105000, 105000, 105000, 0, 120000, 120000, 120000, 120000, 120000, 0 ,135000, 135000, 135000, 135000, 135000, 0, 150000, 150000, 150000, 150000, 150000, 0, 165000, 165000, 165000, 165000, 165000, 0, 180000, 180000, 180000, 180000, 180000, 0, 200000, 200000, 200000, 200000, 200000, 0, 220000, 220000, 220000, 220000, 220000, 0, 480000, 480000, 480000, 480000, 480000, 0, 530000, 530000, 530000, 530000, 530000, 0, 600000, 600000, 600000, 600000, 600000, 0, 720000, 720000, 720000, 720000, 720000, 0, 1000000, 1000000, 1000000, 1000000, 1000000, 0, 1269000, 1269000, 1269000, 1269000, 1269000, 0, 1556000, 1556000, 1556000, 1556000, 1556000, 0, 1861000, 1861000, 1861000, 1861000, 1861000, 0, 2184000, 2184000, 2184000, 2184000, 2184000, 0, 2525000, 2525000, 2525000, 2525000, 2525000, 0};
    private static final int[] IS_BY_LEVEL = {0, 2, 2, 2, 2, 0, 5, 5, 5, 5, 5, 0, 10, 10, 10, 10, 10, 0, 15, 15, 15, 15, 15, 0, 20, 20, 20, 20, 20, 0, 25, 25, 25, 25, 25, 0, 30, 30, 30, 30, 30, 0, 35, 35, 35, 35, 35, 0, 40, 40, 40, 40, 40, 0, 45, 45, 45, 45, 45, 0, 50, 50, 50, 50, 50, 0, 55, 55, 55, 55, 55, 0, 60, 60, 60, 60, 60, 0, 65, 65, 65, 65, 65, 0, 70, 70, 70, 70, 70, 0, 75, 75, 75, 75, 75, 0, 80, 80, 80, 80, 80, 0, 85, 85, 85, 85, 85, 0, 90, 90, 90, 90, 90, 0, 100, 100, 100, 100, 100, 0, 110, 110, 110, 110, 110, 0, 125, 125, 125, 125, 125, 0, 140, 140, 140, 140, 140, 0, 160, 160, 160, 160, 160, 0, 180, 180, 180, 180, 180, 0, 210, 210, 210, 210, 210, 0, 240, 240, 240, 240, 240, 0, 270, 270, 270, 270, 270, 0, 300, 300, 300, 300, 300, 0, 400, 400, 400, 400, 400, 0, 500, 500, 500, 500, 500, 0, 650, 650, 650, 650, 650, 0, 800, 800, 800, 800, 800, 0, 1000, 1000, 1000, 1000, 1000, 0, 1400, 1400, 1400, 1400, 1400, 0, 1800, 1800, 1800, 1800, 1800, 0, 2200, 2200, 2200, 2200, 2200, 0, 2600, 2600, 2600, 2600, 2600, 0, 3000, 3000, 3000, 3000, 3000, 0};
    private static final int[] ZS_BY_LEVEL = {0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 35, 0, 0, 0, 0, 0, 45, 0, 0, 0, 0, 0, 55, 0, 0, 0, 0, 0, 65, 0, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 85, 0, 0, 0, 0, 0, 95, 0, 0, 0, 0, 0, 105, 0, 0, 0, 0, 0, 115, 0, 0, 0, 0, 0, 125, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 145, 0, 0, 0, 0, 0, 155, 0, 0, 0, 0, 0, 165, 0, 0, 0, 0, 0, 175, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1200, 0, 0, 0, 0, 0, 2400, 0, 0, 0, 0, 0, 4800, 0, 0, 0, 0, 0, 7200, 0, 0, 0, 0, 0, 8000};
    private static final int[] AC_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 175, 0, 0, 0, 0, 0, 60, 0, 0, 0, 0, 0, 75, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 0, 105, 0, 0, 0, 0, 0, 120, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0, 165, 0, 0, 0, 0, 0, 180, 0, 0, 0, 0, 0, 195, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] CRYSTAL_BY_LEVEL = {0, 0, 0, 0, 0, 1200, 0, 0, 0, 0, 0, 2000, 0, 0, 0, 0, 0, 2800, 0, 0, 0, 0, 0, 3600, 0, 0, 0, 0, 0, 4400, 0, 0, 0, 0, 0, 5200, 0, 0, 0, 0, 0, 6000, 0, 0, 0, 0, 0, 6800, 0, 0, 0, 0, 0, 7600, 0, 0, 0, 0, 0, 8400, 0, 0, 0, 0, 0, 9500, 0, 0, 0, 0, 0, 11000, 0, 0, 0, 0, 0, 12500, 0, 0, 0, 0, 0, 14000, 0, 0, 0, 0, 0, 15500, 0, 0, 0, 0, 0, 17000, 0, 0, 0, 0, 0, 18500, 0, 0, 0, 0, 0, 20000, 0, 0, 0, 0, 0, 24000, 0, 0, 0, 0, 0, 30000, 0, 0, 0, 0, 0, 36000, 0, 0, 0, 0, 0, 42000, 0, 0, 0, 0, 0, 50000, 0, 0, 0, 0, 0, 60000, 0, 0, 0, 0, 0, 70000, 0, 0, 0, 0, 0, 80000, 0, 0, 0, 0, 0, 90000, 0, 0, 0, 0, 0, 100000, 0, 0, 0, 0, 0, 110000, 0, 0, 0, 0, 0, 150000, 0, 0, 0, 0, 0, 200000, 0, 0, 0, 0, 0, 280000, 0, 0, 0, 0, 0, 400000, 0, 0, 0, 0, 0, 600000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] CR_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0, 240, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 600, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] MR_BY_LEVEL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0, 240, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 640, 0, 0, 0, 0, 0, 1040};

    public String computeIgnitingStone(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, IS_BY_LEVEL);
    }

    public String computeZenithStone(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, ZS_BY_LEVEL);
    }

    public String computeCapstoneRuby(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, CR_BY_LEVEL);
    }

    public String computeMagmaticRock(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, MR_BY_LEVEL);
    }

    public String computeApexCrystal(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, AC_BY_LEVEL);
    }

    public String computeIH(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, IH_BY_LEVEL);
    }

    public String computeCrystal(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, CRYSTAL_BY_LEVEL);
    }

    public String computeExp(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, EXP_BY_LEVEL);
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
        return computeEverything(firstLevel, secondLevel, ACC_BY_LEVEL);
    }

    public String computeTenacity(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, TENA_BY_LEVEL);
    }

    public String computeCrit(int firstLevel, int secondLevel) {
        return computeEverything(firstLevel, secondLevel, CRIT_BY_LEVEL);
    }

    private String computeEverything(int firstLevel, int secondLevel, int[] array) {
        long amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                amount += array[i];
            }
        }
        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return f.format(amount);
    }
}
