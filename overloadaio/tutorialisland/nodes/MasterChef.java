package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 07/08/2016 at 01:05.
 */

public class MasterChef extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public MasterChef(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Making dough.":
                    tutorialIslandMethods.setStatus("Making Dough.");
                    tutorialIslandMethods.useInventoryItems("Bucket of water", "Pot of flour");
                    break;
                case "Cooking dough.":
                    tutorialIslandMethods.setStatus("Cooking Dough.");
                    tutorialIslandMethods.useItemOnObject("Range", "Bread dough");
                    break;
            }

            String widgetMessageOne = script.widgets.get(372, 1).getMessage();

            switch (widgetMessageOne) {
                case "Follow the path until you get to the door with the yellow arrow":
                    tutorialIslandMethods.setStatus("Opening Door.");
                    tutorialIslandMethods.interactWithObject("Door", "Open");
                    break;
                case "Talk to the chef indicated. He will teach you the more advanced":
                    tutorialIslandMethods.setStatus("Talking to Master Chef.");
                    tutorialIslandMethods.interactWithNpc("Master Chef", "Talk-to");
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "Cooking dough":
                    tutorialIslandMethods.setStatus("Opening Music.");
                    script.getTabs().open(Tab.MUSIC);
                    break;
                case "The music player.":
                    tutorialIslandMethods.setStatus("Opening Door.");
                    tutorialIslandMethods.interactWithObject(9710, "Open");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 4 || script.configs.get(406) == 5;
    }
}
