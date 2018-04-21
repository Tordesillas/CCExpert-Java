package fr.unice.polytech.ccexpert.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Sets {
    private static Sets mInstance = null;

    private Map<Integer, Hero> heroesIds;
    private Map<String, Hero> heroesNames;
    private List<Dungeon> dungeonSet;
    private Map<String, Artifact> artifacts;
    private Map<String, Talent> talents;
    private Map<String, Pet> pets;

    private Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        dungeonSet = new ArrayList<>();
        artifacts = new HashMap<>();
        talents = new HashMap<>();
        pets = new HashMap<>();
    }

    public static Sets getInstance() {
        if (mInstance == null) {
            mInstance = new Sets();
        }
        return mInstance;
    }

    void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getFrenchName(), hero);
    }

    void addDungeon(Dungeon dungeon) {
        dungeonSet.add(dungeon);
    }

    void addArtifact(Artifact artifact) {
        artifacts.put(artifact.getFrenchName(), artifact);
    }

    void addTalent(Talent talent) {
        talents.put(talent.getFrenchName(), talent);
    }

    void addPet(Pet pet) {
        pets.put(pet.getFrenchName(), pet);
    }

    public Hero getHero(String name) {
        return heroesNames.get(name);
    }

    public Artifact getArtifact(String name) {
        return artifacts.get(name);
    }

    public Talent getTalent(String name) {
        return talents.get(name);
    }

    public Pet getPet(String name) {
        return pets.get(name);
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

    public List<Hero> getHeroSorted(boolean byName) {
        List<Hero> heroes = new ArrayList<>();
        if (byName) {
            heroes = getHeroesListSorted();
        } else {
            List<Integer> sortedKeys = new ArrayList<>(heroesIds.keySet());
            Collections.sort(sortedKeys);
            Collections.reverse(sortedKeys);
            for (int id : sortedKeys) {
                heroes.add(heroesIds.get(id));
            }
        }
        return heroes;
    }

    private List<Hero> getHeroesListSorted() {
        List<String> nameSorted = new ArrayList<>();
        List<Hero> heroesSorted = new ArrayList<>();
        if ("french".equals(Locale.getDefault().getDisplayLanguage().toLowerCase()) ||
                "français".equals(Locale.getDefault().getDisplayLanguage().toLowerCase())) {
            nameSorted = new ArrayList<>(heroesNames.keySet());
            Collections.sort(nameSorted);
            for (String name : nameSorted) {
                heroesSorted.add(heroesNames.get(name));
            }
        } else {
            Map<String, Hero> enHeroes = new HashMap<>();
            for (Hero hero : heroesNames.values()) {
                nameSorted.add(hero.getEnglishName());
                enHeroes.put(hero.getEnglishName(), hero);
            }
            Collections.sort(nameSorted);
            for (String name : nameSorted) {
                heroesSorted.add(enHeroes.get(name));
            }
        }

        return heroesSorted;
    }
}
