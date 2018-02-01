package fr.unice.polytech.ccexpert.model;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ccexpert.R;

public class StringToResource {
    private static Map<String, Integer> nameToHeroRes = new HashMap<String, Integer>() {{
        put("Roi des Fantômes", R.drawable.phantom_king);
        put("Sirenia", R.drawable.siren);
        put("Immortep", R.drawable.immortep);
        put("Atlanticore", R.drawable.atlanticore);
        put("Grizzlie Faucheur", R.drawable.grizzly_reaper);
        put("Dieu du Tonnerre", R.drawable.thunder_god);
        put("Druide", R.drawable.druid);
        put("Champion", R.drawable.champion);
        put("Succube", R.drawable.succubus);
        put("Ninja", R.drawable.ninja);
        put("Paladin", R.drawable.paladin);
        put("Mage Spirituel", R.drawable.spirit_mage);
        put("Duc Citrouille", R.drawable.pumpkin_duke);
        put("Chieftain Minotaur", R.drawable.minotaur_chieftain);
        put("Snowzilla", R.drawable.snowzilla);
        put("Cupid", R.drawable.cupid);
        put("Souffle Destructeur", R.drawable.moltanica);
        put("Bélier", R.drawable.aries);
        put("Compte Vampire", R.drawable.vlad_dracula);
        put("Chevalier de la Mort", R.drawable.death_knight);
        put("Orksbane", R.drawable.orksbane);
        put("Père Noël", R.drawable.santa_boom);
        put("Pixie", R.drawable.pixie);
        put("Destructeur", R.drawable.destroyer);
        put("Démoniste", R.drawable.warlock);
        put("Treantaur", R.drawable.treantaur);
        put("Reine Harpie", R.drawable.harpy_queen);
        put("Skeletaur", R.drawable.skull_knight);
        put("Barbe Blanche", R.drawable.dread_drake);
        put("Ghoulem", R.drawable.ghoulem);
        put("Sucre d'Orge", R.drawable.candy_kane);
        put("Arctiqua", R.drawable.arctica);
        put("Valentina", R.drawable.valentina);
        put("Dompteur de Bêtes", R.drawable.beast_tamer);
        put("Léona", R.drawable.lady_leo);
        put("Diaboluc", R.drawable.grimfiend);
        put("Dracax", R.drawable.dracax);
        put("Medusia", R.drawable.medusa);
        put("Voltanica", R.drawable.demogorgon);
        put("Ducilia", R.drawable.trixie_treat);
        put("Revenant", R.drawable.revenant);
        put("Rudolphe", R.drawable.lil_nick);
        put("Mickael", R.drawable.michael);
        put("Crève-Coeur", R.drawable.heartbreaker);
        put("Anubis", R.drawable.anubis);
        put("Ronin", R.drawable.ronin);
        put("Rose Mortelle", R.drawable.gunslinger);
        put("Rockno", R.drawable.rockno);
        put("Méchatessa", R.drawable.mechtessa);
        put("Skeletica", R.drawable.skeletica);
    }};

    private static Map<String, Integer> nameToTalentRes = new HashMap<String, Integer>() {{
        put("fou furieux", R.drawable.berserk);
        put("roue à aubes", R.drawable.blade_shell);
        put("rempart", R.drawable.bulwark);
        put("condamnation", R.drawable.condemnation);
        put("ronge", R.drawable.corrode);
        put("frappe létale", R.drawable.deadly_strike);
        put("éclairer", R.drawable.enlighten);
        put("garde enflammé", R.drawable.flame_guard);
        put("fureur du ciel", R.drawable.heavens_wrath);
        put("coup puissant", R.drawable.heavy_blow);
        put("drain de vie", R.drawable.life_drain);
        put("revita", R.drawable.revitalize);
        put("ressusciter", R.drawable.revive);
        put("disperser", R.drawable.scatter);
        put("brûlure", R.drawable.scorch);
        put("auto destruction", R.drawable.self_destruct);
        put("ralentir", R.drawable.slow_down);
        put("sprint", R.drawable.sprint);
        put("peau de pierre", R.drawable.stone_skin);
        put("ténacité", R.drawable.tenacity);
        put("dieu de la guerre", R.drawable.war_god);
    }};

    public static int getHeroPicture(String frenchName) {
        try {
            return nameToHeroRes.get(frenchName);
        } catch (Exception e) {
            return R.drawable.anubis;
        }
    }

    public static int getTalentPicture(String frenchName) {
        try {
            return nameToTalentRes.get(frenchName.toLowerCase());

        } catch (Exception e) {
            return R.drawable.psyshield;
        }
    }
}
