package fr.unice.polytech.ccexpert.model;

public class Hero {
    private String nom;
    private String name;

    public Hero(String nom, String name) {
        this.nom = nom;
        this.name = name;
    }

    public String getFrenchName() {
        return nom;
    }

    public String getEnglishName() {
        return name;
    }

    public String getPicture() {
        return name.toLowerCase().replace(' ', '_').replace("-", "_");
    }
}
