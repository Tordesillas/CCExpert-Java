package fr.unice.polytech.ccexpert.model;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ccexpert.R;

public class StringToResource {
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

    public static int getTalentPicture(String frenchName) {
        try {
            return nameToTalentRes.get(frenchName.toLowerCase());

        } catch (Exception e) {
            return R.drawable.psyshield;
        }
    }
}
