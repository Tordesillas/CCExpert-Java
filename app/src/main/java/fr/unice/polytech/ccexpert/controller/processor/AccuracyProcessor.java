package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class AccuracyProcessor {
    private final static int[] PERCENTAGES = {12, 32, 46, 82};

    public String printAccuracyAmount(int basic, boolean artefactBool, int extra) {
        double amountArtefact = (artefactBool) ? 0.2 : 0;
        double amount = amountArtefact + (1-amountArtefact)*(basic+extra)/10000;

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        StringBuilder sb = new StringBuilder();
        sb.append("Le héros a ")
                .append(f.format(100 + amount*100))
                .append("% de chance de toucher le héros adverse.\nIl a donc 100% de chance d'attendre un héros possédant ")
                .append(f.format(Math.round(amount*100)))
                .append("% d'esquive.\n\n");

        for (int nb : PERCENTAGES) {
            double res = Math.round(100 + amount*100 - nb);
            if (res >= 100) {
                sb.append("Contre un héros à ")
                        .append(nb)
                        .append("% d'esquive, le héros va toucher sa cible à coup sûr avec techniquement une probabilité de ")
                        .append(res)
                        .append("%.\n");
            } else {
                sb.append("Contre un héros à ")
                        .append(nb)
                        .append("% d'esquive, le héros a ")
                        .append(res)
                        .append("% de chance de toucher sa cible.\n");
            }
        }
        return sb.toString();
    }
}
