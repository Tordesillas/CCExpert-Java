package fr.unice.polytech.ccexpert.model;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String nom;
    private String name;
    private String talentGwAttack;
    private String crestGwAttack;
    private String artifactGwAttack;
    private String talentGwDefense;
    private String crestGwDefense;
    private String artifactGwDefense;
    private String talentDungeon;
    private String crestDungeon;
    private String artifactDungeon;
    private String pet;
    private List<String> enchantments;

    public Hero(String nom, String name, String talentGwAttack, String crestGwAttack, String artifactGwAttack, String talentGwDefense,
                String crestGwDefense, String artifactGwDefense, String talentDungeon, String crestDungeon, String artifactDungeon,
                String pet, List<String> enchantments) {
        this.nom = nom;
        this.name = name;
        this.talentGwAttack = talentGwAttack;
        this.crestGwAttack = crestGwAttack;
        this.artifactGwAttack = artifactGwAttack;
        this.talentGwDefense = talentGwDefense;
        this.crestGwDefense = crestGwDefense;
        this.artifactGwDefense = artifactGwDefense;
        this.talentDungeon = talentDungeon;
        this.crestDungeon = crestDungeon;
        this.artifactDungeon = artifactDungeon;
        this.pet = pet;
        this.enchantments = enchantments;
    }

    public String getFrenchName() {
        return nom;
    }

    public String getEnglishName() {
        return name;
    }

    public String getPicture() {
        return parseName(name);
    }

    public String getPetPicture() {
        return parseName(Sets.getInstance().getPet(pet).getName());
    }

    public String getTalentGwAttack() {
        return parseName(Sets.getInstance().getTalent(talentGwAttack).getName());
    }

    public String getCrestGwAttack() {
        return parseName(Sets.getInstance().getTalent(crestGwAttack).getName()) + "_crest";
    }

    public String getArtifactGwAttack() {
        return parseName(Sets.getInstance().getArtifact(artifactGwAttack).getName());
    }

    public String getTalentGwDefense() {
        return parseName(Sets.getInstance().getTalent(talentGwDefense).getName());
    }

    public String getCrestGwDefense() {
        return parseName(Sets.getInstance().getTalent(crestGwDefense).getName()) + "_crest";
    }

    public String getArtifactGwDefense() {
        return parseName(Sets.getInstance().getArtifact(artifactGwDefense).getName());
    }

    public String getTalentDungeon() {
        return parseName(Sets.getInstance().getTalent(talentDungeon).getName());
    }

    public String getCrestDungeon() {
        return parseName(Sets.getInstance().getTalent(crestDungeon).getName()) + "_crest";
    }

    public String getArtifactDungeon() {
        return parseName(Sets.getInstance().getArtifact(artifactDungeon).getName());
    }

    public List<String> getEnchantments() {
        List<String> newEnchantments = new ArrayList<>();
        for (String enchantment : enchantments) {
            newEnchantments.add(parseName(Sets.getInstance().getTalent(enchantment).getName()));
        }
        return newEnchantments;
    }

    private String parseName(String name) {
        return name.toLowerCase().trim().replace(' ', '_').replace("-", "_");
    }
}
