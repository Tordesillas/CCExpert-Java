package fr.unice.polytech.ccexpert.controller.processor;

import java.text.NumberFormat;
import java.util.Locale;

public class DodgeProcessor {
    private static final double AMOUNTS_TALENT[] = {0, 0.05, 0.06, 0.07, 0.08, 0.09, 0.15, 0.35, 0.5};

    public String printDodgeAmount(int basic, int talentLvl, boolean artefactBool, int extra) {
        double amountTalent = AMOUNTS_TALENT[talentLvl];
        double amountArtefact = (artefactBool) ? 0.15 : 0;
        double amount = amountTalent + (1-amountTalent)*amountArtefact + (1-amountTalent)*(1-amountArtefact)*(basic+extra)/10000;

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        return "Le héros sera capable d'esquiver " + f.format(Math.round(amount*100)) + "% des attaques adverses.\n" +
                "Il esquivera donc environ " + f.format(Math.round(amount*10)) +" coups sur 10.";
    }
}
