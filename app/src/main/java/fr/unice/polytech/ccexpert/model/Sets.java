package fr.unice.polytech.ccexpert.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Sets {
    private static Sets mInstance = null;

    private Map<Integer, Hero> heroesIds;
    private Map<String, Hero> heroesNames;
    private Map<Integer, HeroFaculties> heroesFaculties;
    private List<Dungeon> dungeonSet;

    private Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        heroesFaculties = new HashMap<>();
        dungeonSet = new ArrayList<>();
    }

    public static Sets getInstance(){
        if (mInstance == null) {
            mInstance = new Sets();
        }
        return mInstance;
    }

    void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getFrenchName(), hero);
    }

    void addHeroCompo(HeroFaculties compo, int id) {
        heroesFaculties.put(id, compo);
    }

    void addDungeon(Dungeon dungeon) {
        dungeonSet.add(dungeon);
    }

    public Hero getHero(int id) {
        return heroesIds.get(id);
    }

    public Hero getHero(String name) {
        return heroesNames.get(name);
    }

    public Collection<Hero> getHeroesFaculties() {
        return heroesNames.values();
    }

    public HeroFaculties getHeroFaculties(int id) {
        return heroesFaculties.get(id);
    }

    public Collection<Dungeon> getDungeonSet(int door, int base) {
        Collection<Dungeon> matchingDungeons = new HashSet<>();
        for (Dungeon dungeon : dungeonSet) {
            if (dungeon.getBase() == base && dungeon.getDoor() == door) {
                matchingDungeons.add(dungeon);
            }
        }
        return matchingDungeons;
    }

    public List<HeroFaculties> getHeroesFaculties(int[] ids) {
        List<HeroFaculties> heroes = new ArrayList<>();
        for (int id : ids) {
            heroes.add(heroesFaculties.get(id));
        }
        return heroes;
    }
}
