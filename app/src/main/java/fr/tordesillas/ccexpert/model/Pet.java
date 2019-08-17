package fr.tordesillas.ccexpert.model;

import java.util.Locale;

public class Pet {
    private String frName;
    private String enName;
    private String deName;
    private String ruName;
    private String esName;
    private String[] desFr;
    private String[] desEn;
    private String modes;

    public Pet(String frName, String enName, String deName, String ruName, String esName, String[] desFr, String[] desEn, String modes) {
        this.frName = frName;
        this.enName = enName;
        this.deName = deName;
        this.ruName = ruName;
        this.esName = esName;
        this.desFr = desFr;
        this.desEn = desEn;
        this.modes = modes;
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

    public String getDescription(int descriptionLevel) {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return desFr[descriptionLevel];
            default:
                return desEn[descriptionLevel];
        }
    }

    public String getModes() {
        return modes;
    }

    public String getResource() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }
}
