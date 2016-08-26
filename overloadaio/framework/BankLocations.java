package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 10:47.
 */

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum BankLocations {
    DRAYNOR(Banks.DRAYNOR),
    AL_KHARID(Banks.AL_KHARID),
    LUMBRIDGE(Banks.LUMBRIDGE_UPPER),
    FALADOR_EAST(Banks.FALADOR_EAST),
    FALADOR_WEST(Banks.FALADOR_WEST),
    VARROCK_EAST(Banks.VARROCK_EAST),
    VARROCK_WEST(Banks.VARROCK_WEST),
    GRAND_EXCHANGE(new Area(3160, 3485, 3169, 3494)),
    SEERS(Banks.CAMELOT),
    CATHERBY(Banks.CATHERBY),
    EDGEVILLE(Banks.EDGEVILLE),
    YANILLE(Banks.YANILLE),
    GNOME_STRONGHOLD(Banks.GNOME_STRONGHOLD),
    ARDOUNGE_NORTH(Banks.ARDOUGNE_NORTH),
    ARDOUNE_SOUTH(Banks.ARDOUGNE_SOUTH),
    CASTLE_WARS(Banks.CASTLE_WARS),
    DUEL_ARENA(Banks.DUEL_ARENA),
    PEST_CONTROL(Banks.PEST_CONTROL),
    CANIFIS(Banks.CANIFIS),
    BLAST_FURNACE(new Area(1949, 4956, 1947, 4958)),
    TZHAAR(Banks.TZHAAR);

    private final Area area;

    BankLocations(Area area) {
        this.area = area;
    }

    public static Area closestTo(Entity e) {
        HashMap<BankLocations, Integer> distMap = new HashMap<BankLocations, Integer>();
        for (BankLocations b : BankLocations.values()) {
            distMap.put(b, e.getPosition().distance(b.area.getRandomPosition()));
        }
        HashMap<Integer, BankLocations> distMapSorted = sortByDistance(distMap);
        Area cBank = distMapSorted.values().toArray(new BankLocations[BankLocations.values().length])[0].area;
        return cBank;
    }

    private static <K, V extends Comparable<? super V>> HashMap<V, K> sortByDistance(Map<K, V> map) {
        HashMap<V, K> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> result.put(e.getValue(), e.getKey()));
        return result;
    }
}
