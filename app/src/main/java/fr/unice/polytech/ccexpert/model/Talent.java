package fr.unice.polytech.ccexpert.model;

import java.util.Locale;

public class Talent {
    private String nom;
    private String name;
    private boolean isEnchantment;
    private String[] desFr;
    private String[] desEn;

    Talent(String nom, String name, boolean isEnchantment, String des1fr, String des2fr, String des3fr, String des4fr, String des5fr,
           String des6fr, String des7fr, String des8fr, String des9fr, String des1en, String des2en, String des3en, String des4en,
           String des5en, String des6en, String des7en, String des8en, String des9en) {
        this.nom = nom;
        this.name = name;
        this.isEnchantment = isEnchantment;
        desFr = new String[]{ des1fr, des2fr, des3fr, des4fr, des5fr, des6fr, des7fr, des8fr, des9fr };
        desEn = new String[]{ des1en, des2en, des3en, des4en, des5en, des6en, des7en, des8en, des9en };
        checkStrings();
    }

    public String getFrenchName() {
        return nom;
    }

    public String getName() {
        return name;
    }

    public String getTalentResource() {
        return name.toLowerCase().trim().replace(' ', '_').replace("-", "_").replace("\'", "");
    }

    public String getCrestResource() {
        return getTalentResource() + "_crest";
    }

    public boolean isEnchantment() {
        return isEnchantment;
    }

    public String[] getDescription() {
        switch (Locale.getDefault().getDisplayLanguage().toLowerCase()) {
            case "french":
            case "français":
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
