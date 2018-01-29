package fr.unice.polytech.ccexpert.controller.processor;

public class DodgeProcessor {/*
    private L10NManager l10n;

    public DodgeProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printDodgeAmount(int basic, int talentLvl, boolean artefactBool, int extra) {
        Container c = new Container();
        double amountsTalent[] = {0.05, 0.06, 0.07, 0.08, 0.09, 0.15, 0.35, 0.5};
        double amountTalent = amountsTalent[talentLvl-1];
        double amountArtefact = (artefactBool) ? 0.15 : 0;
        double amount = amountTalent + (1-amountTalent)*amountArtefact + (1-amountTalent)*(1-amountArtefact)*(basic+extra)/10000;

        c.addComponent(new SpanLabel("Le héros sera capable d'esquiver " + l10n.format(amount*100) + "% des attaques adverses.\n" +
                "Il esquivera donc environ " + l10n.format(Math.round(amount*10)) +" coups sur 10."));

        return c;
    }*/
}
