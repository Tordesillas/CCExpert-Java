package fr.tordesillas.ccexpert.model;

import java.util.Locale;

public class Archdemon {
    private String descriptionFr;
    private String descriptionEn;
    private String[] heroNames;
    private String[] talents;
    private String[] crests;

    Archdemon(String descriptionFr, String descriptionEn, String[] heroes, String[] talents, String[] crests) {
        this.descriptionFr = descriptionFr;
        this.descriptionEn = descriptionEn;
        this.heroNames = heroes;
        this.talents = talents;
        this.crests = crests;
    }

    public Hero getHero(int position) {
        return Sets.getInstance().getHero(heroNames[position]);
    }

    public Talent getTalent(int position) {
        return Sets.getInstance().getTalent(talents[position]);
    }

    public Talent getCrest(int position) {
        return Sets.getInstance().getTalent(crests[position]);
    }

    public String getDescription() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return descriptionFr.replace(" + ", "\n");
            default:
                return descriptionEn.replace(" + ", "\n");
        }
    }
}
