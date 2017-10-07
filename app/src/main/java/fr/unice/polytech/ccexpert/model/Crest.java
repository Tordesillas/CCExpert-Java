package fr.unice.polytech.ccexpert.model;

import android.media.Image;

public class Crest {
    private String name;
    private int level;
    private String description;
    private Image picture;

    public Crest(String name, String description, Image picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public Image getPicture() {
        return picture;
    }
}
