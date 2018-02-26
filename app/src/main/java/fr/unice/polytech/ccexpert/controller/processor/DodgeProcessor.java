package fr.unice.polytech.ccexpert.controller.processor;

import android.content.res.Resources;

import java.text.NumberFormat;
import java.util.Locale;

import fr.unice.polytech.ccexpert.R;

public class DodgeProcessor {
    private Resources res;
    private static final double AMOUNTS_TALENT[] = {0, 0.05, 0.06, 0.07, 0.08, 0.09, 0.15, 0.35, 0.5};

    public DodgeProcessor(Resources res) {
        this.res = res;
    }

    public String printDodgeAmount(int basic, int talentLvl, boolean artefactBool, int extra) {
        double amountTalent = AMOUNTS_TALENT[talentLvl];
        double amountArtefact = (artefactBool) ? 0.15 : 0;
        double amount = amountTalent + (1-amountTalent)*amountArtefact + (1-amountTalent)*(1-amountArtefact)*(basic+extra)/10000;

        NumberFormat f = NumberFormat.getNumberInstance(Locale.FRANCE);

        if (amount > 1) {
            amount = 1;
        }

        return res.getString(R.string.processorText18) + f.format(Math.round(amount*100)) + res.getString(R.string.processorText19) +
                "\n" + res.getString(R.string.processorText20) + f.format(Math.round(amount*10)) + res.getString(R.string.processorText21);
    }
}
