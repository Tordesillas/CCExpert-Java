package fr.tordesillas.ccexpert.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hero {
    private String frName;
    private String enName;
    private String deName;
    private String ruName;
    private String esName;
    private String talentGwAttack;
    private String crestGwAttack;
    private String insigniaGwAttack;
    private String talentGwDefense;
    private String crestGwDefense;
    private String insigniaGwDefense;
    private String talentDungeon;
    private String crestDungeon;
    private String insigniaDungeon;
    private String pet;
    private List<String> enchantments;

    public Hero(String frName, String enName, String deName, String ruName, String esName, String talentGwAttack, String crestGwAttack,
                String insigniaGwAttack, String talentGwDefense, String crestGwDefense, String insigniaGwDefense, String talentDungeon,
                String crestDungeon, String insigniaDungeon, String pet, List<String> enchantments) {
        this.frName = frName;
        this.enName = enName;
        this.deName = deName;
        this.ruName = ruName;
        this.esName = esName;
        this.talentGwAttack = talentGwAttack;
        this.crestGwAttack = crestGwAttack;
        this.insigniaGwAttack = insigniaGwAttack;
        this.talentGwDefense = talentGwDefense;
        this.crestGwDefense = crestGwDefense;
        this.insigniaGwDefense = insigniaGwDefense;
        this.talentDungeon = talentDungeon;
        this.crestDungeon = crestDungeon;
        this.insigniaDungeon = insigniaDungeon;
        this.pet = pet;
        this.enchantments = enchantments;
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

    public String getFrenchName() {
        return frName;
    }

    public String getEnglishName() {
        return enName;
    }

    public String getGermanName() {
        return deName;
    }

    public String getRussianName() {
        return ruName;
    }

    public String getSpanishName() {
        return esName;
    }

    public String getPicture() {
        return enName.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }

    public String getPetPicture() {
        return Sets.getInstance().getPet(pet).getResource();
    }

    public String getTalentGwAttack() {
        return Sets.getInstance().getTalent(talentGwAttack).getTalentResource();
    }

    public String getCrestGwAttack() {
        return Sets.getInstance().getTalent(crestGwAttack).getCrestResource();
    }

    public String getInsigniaGwAttack() {
        return Sets.getInstance().getInsignia(insigniaGwAttack).getInsigniaResource();
    }

    public String getTalentGwDefense() {
        return Sets.getInstance().getTalent(talentGwDefense).getTalentResource();
    }

    public String getCrestGwDefense() {
        return Sets.getInstance().getTalent(crestGwDefense).getCrestResource();
    }

    public String getInsigniaGwDefense() {
        return Sets.getInstance().getInsignia(insigniaGwDefense).getInsigniaResource();
    }

    public String getTalentDungeon() {
        return Sets.getInstance().getTalent(talentDungeon).getTalentResource();
    }

    public String getCrestDungeon() {
        return Sets.getInstance().getTalent(crestDungeon).getCrestResource();
    }

    public String getInsigniaDungeon() {
        return Sets.getInstance().getInsignia(insigniaDungeon).getInsigniaResource();
    }

    public List<String> getEnchantments() {
        List<String> newEnchantments = new ArrayList<>();
        for (String enchantment : enchantments) {
            newEnchantments.add(Sets.getInstance().getTalent(enchantment).getTalentResource());
        }
        return newEnchantments;
    }
}
