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
    private Map<String, Insignia> insignias;
    private Map<String, Pet> pets;
    private List<Archdemon> archdemons;
    private Map<String, HeroRoll> heroesRoll;
    private List<String> heroNamesSet;

    private Sets() {
        heroesIds = new HashMap<>();
        heroesNames = new HashMap<>();
        dungeonSet = new ArrayList<>();
        talents = new HashMap<>();
        insignias = new HashMap<>();
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

    void addInsignia(Insignia insignia) {
        insignias.put(insignia.getFrenchName(), insignia);
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

        if ("fra".equals(Locale.getDefault().getISO3Language())) {
            nameSorted = new ArrayList<>(heroesNames.keySet());
            Collections.sort(nameSorted);
            for (String name : nameSorted) {
                heroesSorted.add(heroesNames.get(name));
            }
        } else {
            Map<String, Hero> heroes = new HashMap<>();
            for (Hero hero : heroesNames.values()) {
                nameSorted.add(hero.getName());
                heroes.put(hero.getName(), hero);
            }
            Collections.sort(nameSorted);
            for (String name : nameSorted) {
                heroesSorted.add(heroes.get(name));
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

    public List<Insignia> getInsignias() {
        List<String> nameSorted;
        List<Insignia> insigniasSorted = new LinkedList<>();

        switch (Locale.getDefault().getISO3Language()) {
            case "fra":
                nameSorted = new LinkedList<>(insignias.keySet());
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    insigniasSorted.add(insignias.get(name));
                }
                break;
            default:
                Map<String, Insignia> enInsignias = new HashMap<>();
                nameSorted = new LinkedList<>();
                for (Insignia i : insignias.values()) {
                    nameSorted.add(i.getEnglishName());
                    enInsignias.put(i.getEnglishName(), i);
                }
                Collections.sort(nameSorted);
                for (String name : nameSorted) {
                    insigniasSorted.add(enInsignias.get(name));
                }
        }

        return insigniasSorted;
    }

    public Talent getTalent(String name) {
        return talents.get(name);
    }

    public Insignia getInsignia(String name) {
        return insignias.get(name);
    }

    public List<Pet> getPets() {
        List<String> namesSorted;
        List<Pet> sortedPets = new LinkedList<>();

        if ("fra".equals(Locale.getDefault().getISO3Language())) {
            namesSorted = new LinkedList<>(pets.keySet());

            Collections.sort(namesSorted);
            for (String name : namesSorted) {
                sortedPets.add(pets.get(name));
            }
        } else {
            namesSorted = new LinkedList<>();
            Map<String, Pet> petMap = new HashMap<>();
            for (Pet pet : pets.values()) {
                namesSorted.add(pet.getName());
                petMap.put(pet.getName(), pet);
            }
            Collections.sort(namesSorted);
            for (String name : namesSorted) {
                sortedPets.add(petMap.get(name));
            }
        }

        return sortedPets;
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
        if (heroNamesSet.size() == 0) {
            return null;
        }
        int i = new Random().nextInt(heroNamesSet.size());
        return heroesRoll.get(heroNamesSet.get(i));
    }

    public void zeroRollCounts() {
        for (HeroRoll h : heroesRoll.values()) {
            h.zeroOccurences();
        }
    }
}
