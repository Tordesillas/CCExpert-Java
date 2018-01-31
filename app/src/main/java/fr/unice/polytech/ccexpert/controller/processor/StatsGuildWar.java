package fr.unice.polytech.ccexpert.controller.processor;

import android.content.Context;
import android.widget.Toast;

public class StatsGuildWar {
    private static final double[] fameTab = {0.45, 0.39, 0.36, 0.33, 0.30};

    public String printStats(Context context, int power, int score) {
        double scoreOnPower = (power == 0) ? 0 : score / (double)power;
        int averagePower = (scoreOnPower < 0.005) ? 0 : Math.round((float)(0.14*(score-50-2.5*Math.sqrt(power))));

        if (averagePower <= 0) {
            Toast.makeText(context, "Il semble que les données saisies sont incorrectes.", Toast.LENGTH_SHORT).show();
            return null;
        }

        return "Le joueur attaque en moyenne des adversaires à " + averagePower +"k de puissance.";
    }

    public String printFameStats(int score, int position) {
        int fame = (int)(score * (float)fameTab[position]);

        return "Avec un tel score, le joueur obtiendra " + fame + " gloires.";
    }
}
