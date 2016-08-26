package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 07/08/2016 at 00:41.
 */

public class SurvivalExpert extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public SurvivalExpert(Script script) {
        super(script);
    }

    private void makeFire() throws InterruptedException {
        if(script.inventory.contains("Logs")) {
            tutorialIslandMethods.createFire();
        } else {
            tutorialIslandMethods.interactWithObject("Tree", "Chop down");
        }
    }

    private void cookShrimp() throws InterruptedException {
        if(script.getTabs().getOpen().equals(Tab.INVENTORY)) {
            if(script.inventory.getItem("Raw shrimps") != null && script.inventory.getAmount("Raw shrimps") < 2 && script.inventory.getItem("Burnt shrimp") == null) {
                tutorialIslandMethods.interactWithNpc("Fishing spot", "Net");
            } else {
                tutorialIslandMethods.cookShrimp();
            }
        } else {
            script.getTabs().open(Tab.INVENTORY);
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Moving around":
                    tutorialIslandMethods.setStatus("Talking to Survival Expert.");
                    tutorialIslandMethods.interactWithNpc("Survival Expert", "Talk-to");
                    break;
                case "Viewing the items that you were given.":
                    tutorialIslandMethods.setStatus("Opening Inventory");
                    script.getTabs().open(Tab.INVENTORY);
                    break;
                case "Cut down a tree":
                    tutorialIslandMethods.setStatus("Chopping Down a Tree.");
                    tutorialIslandMethods.interactWithObject("Tree", "Chop down");
                    break;
                case "Making a fire":
                    tutorialIslandMethods.setStatus("Making a Fire.");
                    makeFire();
                    break;
                case "Catch some Shrimp.":
                    tutorialIslandMethods.setStatus("Catching some Shrimp.");
                    tutorialIslandMethods.interactWithNpc("Fishing spot", "Net");
                    break;
                case "Cooking your shrimp.":
                    tutorialIslandMethods.setStatus("Cooking the Shrimp.");
                    cookShrimp();
                    break;
                case "Burning your shrimp.":
                    tutorialIslandMethods.setStatus("Cooking the Shrimp.");
                    cookShrimp();
                    break;
                case "Well done, you've just cooked your first RuneScape meal.":
                    tutorialIslandMethods.setStatus("Opening Gate.");
                    tutorialIslandMethods.interactWithObject("Gate", "Open");
                    break;
            }

            String widgetMessageOne = script.widgets.get(372, 1).getMessage();

            switch(widgetMessageOne) {
                case "You gained some experience.":
                    tutorialIslandMethods.setStatus("Opening Skills.");
                    script.getTabs().open(Tab.SKILLS);
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "Your skill stats.":
                    tutorialIslandMethods.setStatus("Talking to Survival Expert.");
                    tutorialIslandMethods.interactWithNpc("Survival Expert", "Talk-to");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 2 || script.configs.get(406) == 3;
    }
}
