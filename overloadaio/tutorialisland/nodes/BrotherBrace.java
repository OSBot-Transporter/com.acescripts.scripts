package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 07/08/2016 at 02:14.
 */

public class BrotherBrace extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public BrotherBrace(Script script) {
        super(script);
    }

    private void talkToNpc() {
        NPC npc = script.getNpcs().closest("Brother Brace");

        if(npc != null && script.map.canReach(npc)) {
            tutorialIslandMethods.interactWithNpc("Brother Brace", "Talk-to");
        } else {
            if(script.map.canReach(new Position(3128, 3107, 0))) {
                script.walking.walk(new Position(3128, 3107, 0));
            } else {
                Entity object = script.objects.closest("Large door");

                if(object != null && script.myPosition().distance(object) < 5) {
                    tutorialIslandMethods.interactWithObject("Large door", "Open");
                } else {
                    script.walking.walk(new Position(3129, 3107, 0));
                }
            }
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Prayer.":
                    tutorialIslandMethods.setStatus("Talking to Brother Brace.");
                    talkToNpc();
                    break;
                case "Your Prayer menu.":
                    tutorialIslandMethods.setStatus("Opening Prayer Tab.");
                    script.getTabs().open(Tab.PRAYER);
                    break;
                case "This is your friends list.":
                    tutorialIslandMethods.setStatus("Opening Ignore Tab.");
                    script.getTabs().open(Tab.IGNORES);
                    break;
                case "This is your ignore list.":
                    tutorialIslandMethods.setStatus("Talking to Brother Brace.");
                    tutorialIslandMethods.interactWithNpc("Brother Brace", "Talk-to");
                    break;
            }

            String widgetMessageOne = script.widgets.get(372, 1).getMessage();

            switch (widgetMessageOne) {
                case "Your Prayer menu.":
                    tutorialIslandMethods.setStatus("Talking to Brother Brace.");
                    tutorialIslandMethods.interactWithNpc("Brother Brace", "Talk-to");
                    break;
                case "Friends list.":
                    tutorialIslandMethods.setStatus("Opening Friends Tab.");
                    script.getTabs().open(Tab.FRIENDS);
                    break;
                case "Your final instructor!":
                    tutorialIslandMethods.setStatus("Opening Door.");
                    tutorialIslandMethods.interactWithObject(9723, "Open");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 16 || script.configs.get(406) == 17;
    }
}
