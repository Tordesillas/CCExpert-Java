package fr.unice.polytech.ccexpert.controller.processor;

public class AccuracyProcessor {/*
    private L10NManager l10n;
    private final static int[] PERCENTAGES = {12, 32, 46, 82};

    public AccuracyProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printAccuracyAmount(int basic, boolean artefactBool, int extra) {
        Container c = new Container();
        double amountArtefact = (artefactBool) ? 0.2 : 0;
        double amount = amountArtefact + (1-amountArtefact)*(basic+extra)/10000;

        c.addComponent(new SpanLabel("Le héros a " + l10n.format(100 + amount*100) + "% de chance de toucher le héros adverse.\n" +
                "Il a donc 100% de chance d'attendre un héros possédant " + l10n.format(Math.round(amount*100)) +"% d'esquive.\n\n"));
        for (int nb : PERCENTAGES) {
            double res = Math.round(100 + amount*100 - nb);
            if (res >= 100) {
                c.addComponent(new SpanLabel("Contre un héros à "+nb+"% d'esquive, le héros va toucher sa cible à coup sûr " +
                        "avec techniquement une probabilité de " + res + "%.\n"));
            } else {
                c.addComponent(new SpanLabel("Contre un héros à "+nb+"% d'esquive, le héros a " + res + "% de chance de toucher sa cible.\n"));
            }
        }
        return c;
    }*/
}
