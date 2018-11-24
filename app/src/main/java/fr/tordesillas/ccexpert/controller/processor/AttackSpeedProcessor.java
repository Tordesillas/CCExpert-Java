package fr.tordesillas.ccexpert.controller.processor;

import android.content.res.Resources;

import fr.tordesillas.ccexpert.R;

public class AttackSpeedProcessor {
    private Resources res;
    private static final double[] skill = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4, 1.45, 1.5, 1.55};
    private static final double[] skillMichael = {1, 1.18, 1.22, 1.26, 1.3, 1.34, 1.38, 1.43, 1.48, 1.54, 1.6};
    private static final double[] ffStats = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.4, 1.5, 1.7};
    private static final double[] furyStats = {1, 1.1, 1.14, 1.19, 1.24, 1.3};

    public AttackSpeedProcessor(Resources res) {
        this.res = res;
    }

    public String printAttackSpeedAmount(int basic, int ff, int fury, int duke, int hits, int mika) {
        double amount = basic / (Math.pow(skill[duke], hits) * skillMichael[mika] * ffStats[ff] * furyStats[fury]);
        return res.getString(R.string.processorText11) + (int)amount + "\n" +
                res.getString(R.string.processorText12) + (int)(Math.ceil(amount/200)*200) + "ms.";
    }
}
