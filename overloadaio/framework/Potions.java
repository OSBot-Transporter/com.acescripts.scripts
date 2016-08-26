package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 09:51.
 */

public enum Potions {
    NONE("None", 0, 0, 0, 0),
    ATTACK("Attack", 0, 0, 0, 0),
    SUPER_ATTACK("Super Attack", 0, 0, 0, 0),
    STRENGTH("Strength", 0, 0, 0, 0),
    SUPER_STRENGTH("Super Strength", 0, 0, 0, 0),
    DEFENCE("Defence", 0, 0, 0, 0),
    SUPER_DEFENCE("Super Defence", 0, 0, 0, 0),
    COMBAT("Combat", 0, 0, 0, 0),
    SUPER_COMBAT("Super Combat", 0, 0, 0, 0),
    PRAYER("Prayer", 0, 0, 0, 0),
    SUPER_RESTORE("Super Restore", 0, 0, 0, 0);

    private String potionName;
    private int potionOneId;
    private int potionTwoId;
    private int potionThreeId;
    private int potionFourId;

    Potions(String potionName, int potionOneId, int potionTwoId, int potionThreeId, int potionFourId) {
        this.potionName = potionName;
        this.potionOneId = potionOneId;
        this.potionTwoId = potionTwoId;
        this.potionThreeId = potionThreeId;
        this.potionFourId = potionFourId;
    }

    public String getPotionName() {
        return potionName;
    }

    public int getPotionOneId() {
        return potionOneId;
    }

    public int getPotionTwoId() {
        return potionTwoId;
    }

    public int getPotionThreeId() {
        return potionThreeId;
    }

    public int getPotionFourId() {
        return potionFourId;
    }

    @Override
    public String toString() {
        return potionName;
    }
}
