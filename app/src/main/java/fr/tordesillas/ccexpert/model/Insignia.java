package fr.tordesillas.ccexpert.model;

import java.util.Locale;

public class Insignia {
    private String frName;
    private String enName;
    private String[] desFr;
    private String[] desEn;

    Insignia(String frName, String enName, String[] desFr, String[] desEn) {
        this.frName = frName;
        this.enName = enName;
        this.desFr = desFr;
        this.desEn = desEn;
        checkStrings();
    }

    public String getFrenchName() {
        return frName;
    }

    public String getEnglishName() {
        return enName;
    }

    public String getName() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return frName;
            default:
                return enName;
        }
    }

    public String getInsigniaResource() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_").replace("\'", "");
    }

    public String[] getDescription() {
        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                return desFr;
            default:
                return desEn;
        }
    }

    private void checkStrings() {
        for (int i = 0; i < 9; i++) {
            if (desFr[i] != null) {
                desFr[i] = desFr[i].trim();
            }
            if (desEn[i] != null) {
                desEn[i] = desEn[i].trim();
            }
        }
    }
}
