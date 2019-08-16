package fr.tordesillas.ccexpert.model;

import java.util.Locale;

public class Pet {
    private String frName;
    private String enName;
    private String deName;
    private String ruName;
    private String esName;

    public Pet(String frName, String enName, String deName, String ruName, String esName) {
        this.frName = frName;
        this.enName = enName;
        this.deName = deName;
        this.ruName = ruName;
        this.esName = esName;
    }

    public String getFrenchName() {
        return frName;
    }

    public String getName() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return frName;
            case "deu":
                return deName;
            case "rus":
                return ruName;
            case "spa":
                return esName;
            default:
                return enName;
        }
    }

    public String getResource() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }
}
