package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 09:52.
 */

public enum NpcData {
    CHICKEN("Chicken", new String[] {"LUMBRIDGE_NORTH_EAST", "LUMBRIDGE_NORTH_EAST", "FALADOR_SOUTH"}, "Bones", "Raw chicken", "Feather"),
    COW("Cow", new String[] {"COW_LOCATION"}, "Bones", "Cowhide", "Raw beef"),
    HILL_GIANT("Hill Giant", new String[] {"HILL_GIANT_LOCATION"}, "Big Bones", "Iron arrow", "Iron dagger", "Iron full helm", "Iron kiteshield", "Steel longsword", "Steel arrow", "Iron 2h sword", "Steel Dagger", "Steel platebody", "Mithril arrow", "Adamant arrow", "Water rune", "Cosmic rune", "Law rune", "Mind rune", "Nature rune", "Fire rune", "Chaos rune", "Death rune", "Grimy guam leaf", "Grimy marrentill", "Grimy tarromin", "Grimy harralander", "Grimy ranarr weed", "Grimy irit leaf", "Grimy avantoe", "Grimy kwuarm", "Grimy cadantine", "Grimy lantadyme", "Grimy dwarf weed", "Potato seed", "Onion seed", "Tomato seed", "Cabbage seed", "Strawberry seed", "Sweetcorn seed", "Limpwurt seed", "Watermelon seed", "Coins", "Limpwurt root", "Beer", "Body talisman", "Champion scroll (giant)", "Rare drop table");

    private String npcName;
    private String[] npcLocation;
    private String[] npcLoot;

    NpcData(String npcName, String[] npcLocation, String... npcLoot){
        this.npcName = npcName;
        this.npcLocation = npcLocation;
        this.npcLoot = npcLoot;
    }

    public String getNpcName() {
        return this.npcName;
    }

    public String[] getNpcLocation() {
        return this.npcLocation;
    }

    public String[] getNpcLoot() {
        return this.npcLoot;
    }
}
