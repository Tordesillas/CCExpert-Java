package fr.unice.polytech.ccexpert.model;

public class Artifact {
    private String nom;
    private String name;

    public Artifact(String nom, String name) {
        this.nom = nom;
        this.name = name;
    }

    public String getFrenchName() {
        return nom;
    }

    public String getName() {
        return name;
    }

    public String getResource() {
        return name.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }

}
