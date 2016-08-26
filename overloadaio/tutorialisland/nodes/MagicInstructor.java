package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Spells;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

import java.util.Random;

/**
 * Created by Transporter on 07/08/2016 at 02:20.
 */
public class MagicInstructor extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public MagicInstructor(Script script) {
        super(script);
    }

    private void talkToNpc() {
        NPC npc = script.getNpcs().closest("Magic Instructor");

        if(npc != null) {
            tutorialIslandMethods.interactWithNpc("Magic Instructor", "Talk-to");
        } else {
            script.walking.walk(new Position(3141, 3086, 0));
        }
    }

    private void walkToWindStrikeTile(int x, int y) {
        if(!script.myPosition().equals(new Position(x, y, 0))) {
            tutorialIslandMethods.walkExact(script, new Position(x, y, 0));
            new ConditionalSleep(3500) {
                @Override
                public boolean condition() {
                    return script.myPosition().equals(new Position(x, y, 0));
                }
            }.sleep();
        }
    }

    private void attackChicken() {
        NPC npc = script.getNpcs().closest("Chicken");

        if(npc.isVisible()) {
            script.getMagic().castSpellOnEntity(Spells.NormalSpells.WIND_STRIKE, npc);
        } else {
            script.camera.toPosition(npc.getPosition());
        }
    }

    private void castWindStrike(String npcName) {
        NPC npc = script.getNpcs().closest(npcName);

        if(script.getTabs().getOpen() != null && script.getTabs().getOpen().equals(Tab.MAGIC)) {
            if(npc != null) {
                Random rand = new Random();
                int n = rand.nextInt(3) + 1;

                if(script.myPosition().getY() != 3091) {
                    if(n == 1) {
                        walkToWindStrikeTile(3138, 3091);
                    } else if(n == 2) {
                        walkToWindStrikeTile(3139, 3091);
                    } else if(n == 3) {
                        walkToWindStrikeTile(3140, 3091);
                    }
                } else {
                    attackChicken();
                }
            }
        } else {
            script.getTabs().open(Tab.MAGIC);
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Your final instructor!":
                    tutorialIslandMethods.setStatus("Talking to Magic Instructor.");
                    talkToNpc();
                    break;
                case "Open up your final menu.":
                    tutorialIslandMethods.setStatus("Opening Magic Tab.");
                    script.getTabs().open(Tab.MAGIC);
                    break;
                case "You have almost completed the tutorial!":
                    tutorialIslandMethods.setStatus("Talking to Magic Instructor.");
                    tutorialIslandMethods.interactWithNpc("Magic Instructor", "Talk-to");
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "Cast Wind Strike at a chicken.":
                    tutorialIslandMethods.setStatus("Casting Wind Strike.");
                    castWindStrike("Chicken");
                    break;
            }
        } else if(script.widgets.get(219, 0, 0) != null && script.widgets.get(219, 0, 0).isVisible()) {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            script.widgets.get(219, 0, 1).interact("Continue");
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        Area tutorialIslandArea = new Area(3059, 3136, 3151, 3059);
        return script.configs.get(406) == 18 || script.configs.get(406) == 19 || script.configs.get(406) == 20 && tutorialIslandArea.contains(script.myPosition());
    }
}
