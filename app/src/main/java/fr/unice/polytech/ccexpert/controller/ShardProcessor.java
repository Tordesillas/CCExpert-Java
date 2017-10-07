package fr.unice.polytech.ccexpert.controller;

public class ShardProcessor {/*
    private static final int[] EXP_BY_LEVEL = {0, 2000, 10000, 30000, 70000, 120000, 200000, 500000, 800000, 1600000};
    private L10NManager l10n;

    public ShardProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printShardAmount(int firstLevel, int secondLevel, String category) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i < secondLevel; i++) {
                amount += EXP_BY_LEVEL[i];
            }
        }
        switch (category) {
            case "Élite":
                amount *= 0.75; break;
            case "Ordinaire":
                amount *= 0.5;
        }

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Expérience à acquérir : " + l10n.format(amount)));
        c.addComponent(new Label("Fragments à dépenser : " + l10n.format(amount/20)));

        return c;
    }*/
}
