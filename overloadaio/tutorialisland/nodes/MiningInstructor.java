package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

import java.util.Random;

/**
 * Created by Transporter on 07/08/2016 at 01:25.
 */

public class MiningInstructor extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public MiningInstructor(Script script) {
        super(script);
    }

    private void talkToMiningNpc() {
        NPC npc = script.getNpcs().closest("Mining Instructor");

        if(npc != null) {
            tutorialIslandMethods.interactWithNpc("Mining Instructor", "Talk-to");
        } else {
            script.walking.walk(new Position(3080, 9505, 0));
        }
    }

    private void makeBronzeBar() throws InterruptedException {
        if(script.getTabs().getOpen().equals(Tab.INVENTORY)) {
            Random rand = new Random();
            int n = rand.nextInt(2) + 1;

            if(n == 1) {
                tutorialIslandMethods.useItemOnObject("Furnace", "Tin ore");
            } else if(n == 2) {
                tutorialIslandMethods.useItemOnObject("Furnace", "Copper ore");
            }
        } else {
            script.getTabs().open(Tab.INVENTORY);
        }
    }

    private void smithBronzeDagger() throws InterruptedException {
        if(script.widgets.get(312, 1, 0) != null && script.widgets.get(312, 1, 0).isVisible()) {
            script.widgets.get(312, 2, 2).interact("Smith 1");
        } else {
            if(!script.myPlayer().isAnimating()) {
                tutorialIslandMethods.useItemOnObject("Anvil", "Bronze bar");
            }
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Mining and Smithing.":
                    tutorialIslandMethods.setStatus("Talking to Mining Instructor.");
                    talkToMiningNpc();
                    break;
                case "Prospecting.":
                    tutorialIslandMethods.setStatus("Prospecting Tin Ore.");
                    tutorialIslandMethods.interactWithObject(10080, "Prospect");
                    break;
                case "It's tin.":
                    tutorialIslandMethods.setStatus("Prospecting Copper Ore.");
                    tutorialIslandMethods.interactWithObject(10079, "Prospect");
                    break;
                case "It's copper.":
                    tutorialIslandMethods.setStatus("Talking to Mining Instructor.");
                    tutorialIslandMethods.interactWithNpc("Mining Instructor", "Talk-to");
                    break;
                case "Smelting.":
                    tutorialIslandMethods.setStatus("Making Bronze Bar.");
                    makeBronzeBar();
                    break;
                case "You've made a bronze bar!":
                    tutorialIslandMethods.setStatus("Talking to Mining Instructor.");
                    tutorialIslandMethods.interactWithNpc("Mining Instructor", "Talk-to");
                    break;
                case "Smithing a dagger.":
                    tutorialIslandMethods.setStatus("Smithing Bronze Dagger.");
                    smithBronzeDagger();
                    break;
                case "You've finished in this area.":
                    tutorialIslandMethods.setStatus("Opening Gate.");
                    tutorialIslandMethods.interactWithObject("Gate", "Open");
                    break;
            }

            String widgetMessageOne = script.widgets.get(372, 1).getMessage();

            switch (widgetMessageOne) {
                case "It's quite simple really. All you need to do is right click on the":
                    tutorialIslandMethods.setStatus("Mining Tin.");
                    tutorialIslandMethods.interactWithObject(10080, "Mine");
                    break;
                case "Now you have some tin ore you just need some copper ore,":
                    tutorialIslandMethods.setStatus("Mining Copper.");
                    tutorialIslandMethods.interactWithObject(10079, "Mine");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 8 || script.configs.get(406) == 9;
    }
}
