package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

import java.util.Random;

/**
 * Created by Transporter on 07/08/2016 at 01:16.
 */

public class QuestGuide extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public QuestGuide(Script script) {
        super(script);
    }

    private void performRandomEmote() {
        Random rand = new Random();
        int id = rand.nextInt(22);

        if(script.getTabs().getOpen().equals(Tab.EMOTES)) {
            if(script.widgets.get(216, 1, id) != null) {
                script.widgets.get(216, 1, id).interact();
            }
        } else {
            script.getTabs().open(Tab.EMOTES);
        }
    }

    private void runToNextGuide() {
        Entity object = script.objects.closest(9716);

        if(object != null && object.isVisible()) {
            tutorialIslandMethods.interactWithObject(9716, "Open");
        } else {
            script.walking.walk(new Position(3086, 3126, 0));
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Emotes.":
                    tutorialIslandMethods.setStatus("Opening Emotes.");
                    script.getTabs().open(Tab.EMOTES);
                    break;
                case "Your Quest Journal.":
                    tutorialIslandMethods.setStatus("Talking to Quest Guide.");
                    tutorialIslandMethods.interactWithNpc("Quest Guide", "Talk-to");
                    break;
            }

            String widgetMessageOne = script.widgets.get(372, 1).getMessage();

            switch (widgetMessageOne) {
                case "It's only a short distance to the next guide.":
                    tutorialIslandMethods.setStatus("Opening Settings.");
                    script.getTabs().open(Tab.SETTINGS);
                    break;
                case "In this menu you will see many options. At the bottom in the":
                    tutorialIslandMethods.setStatus("Turning on Run.");
                    script.widgets.get(261, 65).interact();
                    break;
                case "Talk with the Quest Guide.":
                    tutorialIslandMethods.setStatus("Talking to Quest Guide.");
                    tutorialIslandMethods.interactWithNpc("Quest Guide", "Talk-to");
                    break;
                case "Open the Quest Journal.":
                    tutorialIslandMethods.setStatus("Opening Quests.");
                    script.getTabs().open(Tab.QUEST);
                    break;
                case "Moving on.":
                    tutorialIslandMethods.setStatus("Climbing Down Ladder.");
                    tutorialIslandMethods.interactWithObject("Ladder", "Climb-down");
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "Emotes.":
                    tutorialIslandMethods.setStatus("Performing Random Emote.");
                    performRandomEmote();
                    break;
                case "Run to the next guide.":
                    tutorialIslandMethods.setStatus("Running To Next Guide.");
                    runToNextGuide();
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 6 || script.configs.get(406) == 7;
    }
}
