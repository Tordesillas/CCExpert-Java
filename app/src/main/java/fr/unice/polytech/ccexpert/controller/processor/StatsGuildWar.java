package fr.unice.polytech.ccexpert.controller.processor;

public class StatsGuildWar {
    private static final double[] fameTab = {0.45, 0.39, 0.36, 0.33, 0.30};
/*
    public Container printStats(int power, int score) {
        double scoreOnPower = (power == 0) ? 0 : score / (double)power;
        int averagePower = (scoreOnPower < 0.005) ? 0 : Math.round((float)(0.14*(score-50-2.5*Math.sqrt(power))));

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Puissance moyenne attaquée : " + averagePower +" 000"));

        if (averagePower == 0) {
            Dialog.show("Erreur", "Les données saisies sont incorrectes.", "ok", null);
        }
        return c;
    }

    public Container printFameStats(int score, int position) {
        int fame = (int)(score * (float)fameTab[position]);

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Gloire obtenue : " + fame));

        return c;
    }*/
}
