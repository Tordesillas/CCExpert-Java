package fr.unice.polytech.ccexpert.model;

public class Hero {
    private String nom;
    private String name;
    private int picture;

    private int attackSpeed;
    private int attack;
    private int attackByLvl;
    private int healthPoints;
    private int healthByLvl;
    private int speed;
    private int speedByLvl;
    private int criticalHit;
    private int criticalDamage;
    private int criticalResist;
    private int accuracy;
    private int dodge;
    private int range;
    private int inscription;
    private String talent;
    private String equipmentTalent;

    public Hero(String nom, String name, int picture, int attackSpeed, int attack, int attackByLvl,
                int healthPoints, int healthByLvl, int speed, int speedByLvl, int criticalHit,
                int criticalDamage, int criticalResist, int accuracy, int dodge, int range) {
        this.nom = nom;
        this.name = name;
        this.picture = picture;
        this.attackSpeed = attackSpeed;
        this.attack = attack;
        this.attackByLvl = attackByLvl;
        this.healthPoints = healthPoints;
        this.healthByLvl = healthByLvl;
        this.speed = speed;
        this.speedByLvl = speedByLvl;
        this.criticalHit = criticalHit;
        this.criticalDamage = criticalDamage;
        this.criticalResist = criticalResist;
        this.accuracy = accuracy;
        this.dodge = dodge;
        this.range = range;
    }

    public String getFrenchName() {
        return nom;
    }

    public int getPicture() {
        return picture;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
