package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 09:50.
 */

public enum Food {
    NONE("None"),
    PEACH("Peach"),
    KEBAB("Kebab"),
    SHRIMP("Shrimp"),
    ANCHOVIES("Anchovies"),
    CHICKEN("Chicken"),
    MEAT("Cooked meat"),
    BREAD("Bread"),
    HERRING("Herring"),
    MACKEREL("Mackerel"),
    TROUT("Trout"),
    SALMON("Salmon"),
    TUNA("Tuna"),
    CAKE("Cake"),
    TWO_THIRDS_CAKE("2/3 cake"),
    SLICE_OF_CAKE("Slice of cake"),
    LOBSTER("Lobster"),
    SWORDFISH("Swordfish"),
    MONKFISH("Monkfish"),
    SHARK("Shark"),
    MANTA_RAY("Manta ray"),
    TUNA_POTATO("Tuna potato");

    private String name;

    Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}