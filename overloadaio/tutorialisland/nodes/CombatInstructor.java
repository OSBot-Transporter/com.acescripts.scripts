package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;

/**
 * Created by Transporter on 07/08/2016 at 01:48.
 */

public class CombatInstructor extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public CombatInstructor(Script script) {
        super(script);
    }

    private void equipBronzeSword() {
        if(script.widgets.get(85, 0) != null && script.widgets.get(85, 0).isVisible()) {
            for(int i = 0; i < 27; i++) {
                if(script.widgets.get(85, 0, i) != null) {
                    if(script.widgets.get(85, 0, i).getItemId() == 1205) {
                        script.widgets.get(85, 0, i).interact();
                    }
                }
            }
        } else {
            openEquipMenu();
        }
    }

    private void attackRat() {
        if(!script.myPlayer().isUnderAttack() && !script.myPlayer().isAnimating() && script.myPlayer().getInteracting() == null) {
            if(script.myPosition().equals(new Position(3104, 9509, 0))) {
                tutorialIslandMethods.walkExact(script, new Position(3107, 9511, 0));
            } else {
                NPC rat = script.getNpcs().npcs.closest(new Filter<NPC>() {
                    @Override
                    public boolean match(NPC npc) {
                        return npc != null && npc.getName().equals("Giant rat") && !npc.isUnderAttack() && (npc.getInteracting() == null || npc.getInteracting().equals(script.myPlayer())) && npc.getAnimation() != 4653;
                    }
                });
                rat.interact("Attack");
                new ConditionalSleep(5000) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() && script.myPlayer().isMoving();
                    }
                }.sleep();
            }
        }
    }

    private void rangeRat() {
        if(script.getTabs().getOpen().equals(Tab.INVENTORY)) {
            if(script.inventory.contains("Bronze arrow")) {
                script.inventory.getItem("Bronze arrow").interact("Wield");
            } else if(script.inventory.contains("Shortbow")) {
                script.inventory.getItem("Shortbow").interact("Wield");
            } else {
                attackRat();
            }
        } else {
            script.getTabs().open(Tab.INVENTORY);
        }
    }

    private void returnToCombatInstructor() throws InterruptedException {
        NPC npc = script.getNpcs().closest("Combat Instructor");

        if(script.map.canReach(npc)) {
            tutorialIslandMethods.interactWithNpc("Combat Instructor", "Talk-to");
        } else {
            tutorialIslandMethods.interactWithObject(9719, "Open");
            sleep(random(3000, 4000));
        }
    }

    private void talkToCombatInstructor() {
        if(script.widgets.get(84, 1) != null && script.widgets.get(84, 1).isVisible()) {
            script.widgets.get(84, 4).interact();
        } else {
            tutorialIslandMethods.interactWithNpc("Combat Instructor", "Talk-to");
        }
    }

    private void openEquipMenu() {
        if(script.getTabs().getOpen().equals(Tab.EQUIPMENT)) {
            script.widgets.get(387, 18).interact();
        } else {
            script.getTabs().open(Tab.EQUIPMENT);
        }
    }

    private void equipWeapon(int weaponId) {
        for(int i = 0; i < 27; i++) {
            if(script.widgets.get(85, 0, i) != null) {
                if(script.widgets.get(85, 0, i).getItemId() == weaponId) {
                    script.widgets.get(85, 0, i).interact();
                }
            }
        }
    }

    private void equipBetterWeapons() {
        if(script.widgets.get(84, 1) == null) {
            openEquipMenu();
        } else {
            if(script.widgets.get(85, 0) != null && script.widgets.get(85, 0).isVisible()) {
                equipWeapon(1277);
                equipWeapon(1171);
            }
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Combat.":
                    tutorialIslandMethods.setStatus("Talking to Combat Instructor.");
                    tutorialIslandMethods.interactWithNpc("Combat Instructor", "Talk-to");
                    break;
                case "Wielding weapons.":
                    tutorialIslandMethods.setStatus("Opening Equipment.");
                    script.getTabs().open(Tab.EQUIPMENT);
                    break;
                case "This is your worn inventory.":
                    tutorialIslandMethods.setStatus("Opening Worn Interface.");
                    script.widgets.get(387, 18).interact();
                    break;
                case "Worn interface":
                    tutorialIslandMethods.setStatus("Equipping Bronze Dagger.");
                    equipBronzeSword();
                    break;
                case "Combat interface.":
                    tutorialIslandMethods.setStatus("Opening Combat Tab.");
                    script.getTabs().open(Tab.ATTACK);
                    break;
                case "Attacking.":
                    tutorialIslandMethods.setStatus("Attacking Rat.");
                    attackRat();
                    break;
                case "Well done, you've made your first kill!":
                    tutorialIslandMethods.setStatus("Talking to Combat Instructor.");
                    returnToCombatInstructor();
                    break;
                case "Rat ranging.":
                    tutorialIslandMethods.setStatus("Ranging Rat.");
                    rangeRat();
                    break;
                case "Moving on.":
                    tutorialIslandMethods.setStatus("Climbing Ladder.");
                    tutorialIslandMethods.interactWithObject("Ladder", "Climb-up");
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "You're now holding your dagger.":
                    tutorialIslandMethods.setStatus("Talking to Combat Instructor.");
                    talkToCombatInstructor();
                    break;
                case "Unequipping items.":
                    tutorialIslandMethods.setStatus("Equipping Better Weapons.");
                    equipBetterWeapons();
                    break;
                case "This is your combat interface.":
                    tutorialIslandMethods.setStatus("Opening Gate.");
                    tutorialIslandMethods.interactWithObject(9719, "Open");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 10 || script.configs.get(406) == 11 || script.configs.get(406) == 12;
    }
}