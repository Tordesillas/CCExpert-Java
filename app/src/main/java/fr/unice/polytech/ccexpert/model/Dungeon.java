package fr.unice.polytech.ccexpert.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Dungeon {
    private URL urlYoutube;
    private int door;
    private int base;
    private int[] heroesIds;
    private boolean f2p;
    private boolean allFlames;

    Dungeon(String urlYoutube, int door, int base, int compo1, int compo2, int compo3, int compo4, int compo5, int compo6, int f2p, int allFlames) {
        try {
            this.urlYoutube = new URL(urlYoutube);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.door = door;
        this.base = base;
        heroesIds = new int[]{compo1, compo2, compo3, compo4, compo5, compo6};
        this.f2p = f2p == 1;
        this.allFlames = allFlames == 1;
    }

    public URL getUrlYoutube() {
        return urlYoutube;
    }

    public int getDoor() {
        return door;
    }

    public int getBase() {
        return base;
    }

    public int[] getHeroesIds() {
        return heroesIds;
    }

    public boolean isF2p() {
        return f2p;
    }

    public boolean isAllFlames() {
        return allFlames;
    }
}
