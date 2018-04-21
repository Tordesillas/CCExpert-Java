package fr.unice.polytech.ccexpert.model;

public class Talent {
    private String nom;
    private String name;

    public Talent(String nom, String name) {
        this.nom = nom;
        this.name = name;
    }

    public String getFrenchName() {
        return nom;
    }

    public String getName() {
        return name;
    }

    public String getTalentResource() {
        return name.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }

    public String getCrestResource() {
        return getTalentResource() + "_crest";
    }
}
