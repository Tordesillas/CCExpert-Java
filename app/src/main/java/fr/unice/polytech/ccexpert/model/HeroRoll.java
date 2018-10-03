package fr.unice.polytech.ccexpert.model;

public class HeroRoll {
    private String nameFr;
    private String enName;
    private String deName;
    private int proba;
    private int type;

    public HeroRoll(String nameFr, String enName, String deName, int proba, int type) {
        this.nameFr = nameFr;
        this.enName = enName;
        this.deName = deName;
        this.proba = proba;
        this.type = type;
    }

    public String getNameFr() {
        return nameFr;
    }

    public String getEnName() {
        return enName;
    }

    public String getDeName() {
        return deName;
    }

    public int getProba() {
        return proba;
    }

    public int getType() {
        return type;
    }

    public String getPicture() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }
}
