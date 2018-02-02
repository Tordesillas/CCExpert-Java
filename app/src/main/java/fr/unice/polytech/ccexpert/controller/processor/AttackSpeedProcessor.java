package fr.unice.polytech.ccexpert.controller.processor;

public class AttackSpeedProcessor {
    private double[] skill = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.35, 1.4, 1.45, 1.5, 1.55};
    private double[] skillMichael = {1, 1.18, 1.22, 1.26, 1.3, 1.34, 1.38, 1.43, 1.48, 1.54, 1.6};
    private double[] ffStats = {1, 1.1, 1.15, 1.2, 1.25, 1.3, 1.4, 1.5, 1.7};
    private double[] furyStats = {1, 1.1, 1.14, 1.19, 1.24, 1.3};

    public String printAttackSpeedAmount(int basic, int ff, boolean blitz, int fury, int duke, int hits, int mika) {
        double artifact = (blitz) ? 1.3 : 1;
        double res = basic / (Math.pow(skill[duke], hits) * skillMichael[mika] * ffStats[ff] * artifact * furyStats[fury]);
        return "Nouvelle vitesse d'attaque : " + (int)res + "\n" +
                "Le héros attaquera donc une fois toutes les " + (int)(Math.ceil(res/200)*200) + "ms.";
    }
}
