package fr.unice.polytech.ccexpert.model;

public class Archdemon {
    private String descriptionFr;
    private String descriptionEn;
    private String[] heroes;
    private String[] talents;
    private String[] crests;

    Archdemon(String descriptionFr, String descriptionEn, String[] heroes, String[] talents, String[] crests) {
        this.descriptionFr = descriptionFr;
        this.descriptionEn = descriptionEn;
        this.heroes = heroes;
        this.talents = talents;
        this.crests = crests;
    }

    public Hero getHero(int position) {
        return Sets.getInstance().getHero(heroes[position]);
    }

    public Talent getTalent(int position) {
        return Sets.getInstance().getTalent(talents[position]);
    }

    public Talent getCrest(int position) {
        return Sets.getInstance().getTalent(crests[position]);
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }
}
