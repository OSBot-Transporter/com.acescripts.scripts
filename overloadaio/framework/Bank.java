package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 09:52.
 */

public enum Bank {
    DRAYNOR("DRAYNOR"),
    AL_KHARID("AL_KHARID"),
    LUMBRIDGE("LUMBRIDGE_UPPER"),
    FALADOR_EAST("FALADOR_EAST"),
    FALADOR_WEST("FALADOR_WEST"),
    VARROCK_EAST("FALADOR_EAST"),
    VARROCK_WEST("VARROCK_WEST"),
    GRAND_EXCHANGE("GRAND_EXCHANGE"),
    SEERS("CAMELOT"),
    CATHERBY("CATHERBY"),
    EDGEVILLE("EDGEVILLE"),
    YANILLE("YANILLE"),
    GNOME_STRONGHOLD("GNOME_STRONGHOLD"),
    ARDOUNGE_NORTH("ARDOUGNE_NORTH"),
    ARDOUNE_SOUTH("ARDOUGNE_SOUTH"),
    CASTLE_WARS("CASTLE_WARS"),
    DUEL_ARENA("DUEL_ARENA"),
    PEST_CONTROL("PEST_CONTROL"),
    CANIFIS("CANIFIS"),
    BLAST_FURNACE("BLAST_FURNACE"),
    TZHAAR("TZHAAR");

    private final String areaName;

    Bank(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return this.areaName;
    }
}
