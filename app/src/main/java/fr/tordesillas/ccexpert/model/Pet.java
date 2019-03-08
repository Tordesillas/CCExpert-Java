package fr.tordesillas.ccexpert.model;

import java.util.Locale;

public class Pet {
    private String frName;
    private String enName;

    public Pet(String frName, String enName) {
        this.frName = frName;
        this.enName = enName;
    }

    public String getFrenchName() {
        return frName;
    }

    public String getName() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return frName;
            default:
                return enName;
        }    }

    public String getResource() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }
}
