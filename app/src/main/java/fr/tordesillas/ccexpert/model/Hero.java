package fr.tordesillas.ccexpert.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Hero {
    private String frName;
    private String enName;
    private String deName;
    private String ruName;
    private String talentGwAttack;
    private String crestGwAttack;
    private String talentGwDefense;
    private String crestGwDefense;
    private String talentDungeon;
    private String crestDungeon;
    private String pet;
    private List<String> enchantments;

    public Hero(String frName, String enName, String deName, String ruName, String talentGwAttack, String crestGwAttack, String talentGwDefense,
                String crestGwDefense, String talentDungeon, String crestDungeon, String pet, List<String> enchantments) {
        this.frName = frName;
        this.enName = enName;
        this.deName = deName;
        this.ruName = ruName;
        this.talentGwAttack = talentGwAttack;
        this.crestGwAttack = crestGwAttack;
        this.talentGwDefense = talentGwDefense;
        this.crestGwDefense = crestGwDefense;
        this.talentDungeon = talentDungeon;
        this.crestDungeon = crestDungeon;
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

    public String getTalentGwDefense() {
        return Sets.getInstance().getTalent(talentGwDefense).getTalentResource();
    }

    public String getCrestGwDefense() {
        return Sets.getInstance().getTalent(crestGwDefense).getCrestResource();
    }

    public String getTalentDungeon() {
        return Sets.getInstance().getTalent(talentDungeon).getTalentResource();
    }

    public String getCrestDungeon() {
        return Sets.getInstance().getTalent(crestDungeon).getCrestResource();
    }

    public List<String> getEnchantments() {
        List<String> newEnchantments = new ArrayList<>();
        for (String enchantment : enchantments) {
            newEnchantments.add(Sets.getInstance().getTalent(enchantment).getTalentResource());
        }
        return newEnchantments;
    }
}
