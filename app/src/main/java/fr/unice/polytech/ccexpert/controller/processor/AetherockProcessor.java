package fr.unice.polytech.ccexpert.controller.processor;

public class AetherockProcessor {/*
    private static final int[] AETHEROCKS_BY_LEVEL = {0, 0, 8410, 12265, 17700, 25075, 34750, 47085, 62440, 81175, 103650, 130225, 161260, 197115, 238150, 284725, 337200, 395935, 461290, 533625, 613300};
    private static final int[] DAMAGES_BY_LEVEL = {0, 20, 44, 72, 104, 140, 180, 224, 272, 324, 380, 440, 504, 572, 644, 720, 800, 884, 972, 1064, 1160};
    private static final int[] HEALTH_POINTS_BY_LEVEL = {0, 500, 1100, 1800, 2600, 3500, 4500, 5600, 6800, 8100, 9500, 11000, 12600, 14300, 16100, 18000, 20000, 22100, 24300, 26600, 29000};
    private L10NManager l10n;

    public AetherockProcessor() {
        l10n = L10NManager.getInstance();
    }

    public Container printAetherockAmount(int firstLevel, int secondLevel) {
        int amount = 0;
        if (secondLevel > firstLevel) {
            for (int i = firstLevel; i <= secondLevel; i++) {
                amount += AETHEROCKS_BY_LEVEL[i];
            }
        }

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.addComponent(new Label("Aura-guerrières à dépenser : " + l10n.format(amount)));
        c.addComponent(new Label("Dégats supplémentaires : " + l10n.format(DAMAGES_BY_LEVEL[secondLevel]-DAMAGES_BY_LEVEL[firstLevel])));
        c.addComponent(new Label("Points de vie obtenus : " + l10n.format(HEALTH_POINTS_BY_LEVEL[secondLevel]-HEALTH_POINTS_BY_LEVEL[firstLevel])));

        return c;
    }*/
}
