package fr.unice.polytech.ccexpert.model;

public class HeroFaculties {
    private int heroId;
    private int level;
    private int powerLevel;
    private String talentName;
    private int talentLevel;
    private int inscription;
    private String equipmentName;
    private int equipmentLevel;
    private int equipmentAttack;
    private int equipmentDodge;
    private int evolution;

    HeroFaculties(int heroId, int level, int powerLevel, String talentName, int talentLevel, int inscription,
                         String equipmentName, int equipmentLevel, int equipmentAttack, int equipmentDodge, int evolution) {
        this.heroId = heroId;
        this.level = level;
        this.powerLevel = powerLevel;
        this.talentName = talentName;
        this.talentLevel = talentLevel;
        this.inscription = inscription;
        this.equipmentName = equipmentName;
        this.equipmentLevel = equipmentLevel;
        this.equipmentAttack = equipmentAttack;
        this.equipmentDodge = equipmentDodge;
        this.evolution = evolution;
    }

    public int getHeroId() {
        return heroId;
    }

    public int getLevel() {
        return level;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public String getTalentName() {
        return talentName;
    }

    public int getTalentLevel() {
        return talentLevel;
    }

    public int getInscription() {
        return inscription;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getEquipmentLevel() {
        return equipmentLevel;
    }

    public int getEquipmentAttack() {
        return equipmentAttack;
    }

    public int getEquipmentDodge() {
        return equipmentDodge;
    }

    public int getEvolution() {
        return evolution;
    }
}
