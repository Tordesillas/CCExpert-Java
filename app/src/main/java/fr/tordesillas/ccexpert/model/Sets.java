package fr.tordesillas.ccexpert.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Sets {
    private static Sets mInstance = null;

    private Map<Integer, Hero> heroesIds;
    private Map<String, Hero> heroesNames;
    private List<Dungeon> dungeonSet;
    private Map<String, Talent> talents;
    private Map<String, Pet> pets;
    private List<Archdemon> archdemons;
    private Map<String, HeroRoll> heroesRoll;
    private List<String> heroNamesSet;

    private Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        dungeonSet = new ArrayList<>();
        talents = new HashMap<>();
        pets = new HashMap<>();
        archdemons = new ArrayList<>();
        heroesRoll = new HashMap<>();
        heroNamesSet = new LinkedList<>();
    }

    public static Sets getInstance() {
        if (mInstance == null) {
            mInstance = new Sets();
        }
        return mInstance;
    }

    /* Adders */
    void addHero(Hero hero, int id) {
        heroesIds.put(id, hero);
        heroesNames.put(hero.getFrenchName(), hero);
    }

    void addDungeon(Dungeon dungeon) {
        dungeonSet.add(dungeon);
    }

    void addTalent(Talent talent) {
        talents.put(talent.getFrenchName(), talent);
    }

    void addPet(Pet pet) {
        pets.put(pet.getFrenchName(), pet);
    }

    void addArchdemon(Archdemon archdemon) {
        archdemons.add(archdemon);
    }

    void addHeroRoll(HeroRoll hero) {
        heroesRoll.put(hero.getName(), hero);
    }

    /* Getters */
    public Hero getHero(String name) {
        return heroesNames.get(name);
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

        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                nameSorted = new ArrayList<>(heroesNames.keySet());
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    heroesSorted.add(heroesNames.get(name));
                }
                break;
            case "deu":
                Map<String, Hero> geHeroes = new HashMap<>();
                for (Hero hero : heroesNames.values()) {
                    nameSorted.add(hero.getGermanName());
                    geHeroes.put(hero.getGermanName(), hero);
                }
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    heroesSorted.add(geHeroes.get(name));
                }
                break;
            default:
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

    public Archdemon getArchdemon(int position) {
        if (position >= archdemons.size() - 1) {
            return archdemons.get(archdemons.size() - 1);
        } else if (position <= 0) {
            return archdemons.get(0);
        } else {
            return archdemons.get(position);
        }
    }

    public int getArchdemonsSize() {
        return archdemons.size();
    }

    public List<Talent> getTalents() {
        return getTalentsOrEnchantments(false);
    }

    public List<Talent> getEnchantments() {
        return getTalentsOrEnchantments(true);
    }

    private List<Talent> getTalentsOrEnchantments(boolean isEnchantment) {
        List<String> nameSorted;
        List<Talent> talentsSorted = new LinkedList<>();
        Talent t;

        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                nameSorted = new LinkedList<>(talents.keySet());
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    t = talents.get(name);
                    if (t.isEnchantment() == isEnchantment) {
                        talentsSorted.add(t);
                    }
                }
                break;
            default:
                Map<String, Talent> enTalents = new HashMap<>();
                nameSorted = new LinkedList<>();
                for (Talent talent : talents.values()) {
                    nameSorted.add(talent.getEnglishName());
                    enTalents.put(talent.getEnglishName(), talent);
                }
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    t = enTalents.get(name);
                    if (t.isEnchantment() == isEnchantment) {
                        talentsSorted.add(t);
                    }
                }
        }

        return talentsSorted;
    }

    public Talent getTalent(String name) {
        if (talents.containsKey(name)) {
            return talents.get(name);
        }
        return talents.get(talents.keySet().iterator().next());
    }

    /* Roll */
    private static final int STEP = 5;
    void createRollSet() {
        for (HeroRoll h : heroesRoll.values()) {
            for (int i = 0; i < h.getProba() / STEP; i++) {
                heroNamesSet.add(h.getName());
            }
        }
    }

    public HeroRoll getHeroRoll() {
        try {
            Random rand = new Random();
            int i = rand.nextInt(heroNamesSet.size());
            return heroesRoll.get(heroNamesSet.get(i));
        } catch (IllegalArgumentException e) {
            return getHeroRoll();
        }
    }

    public void zeroRollCounts() {
        for (HeroRoll h : heroesRoll.values()) {
            h.zeroOccurences();
        }
    }
}
