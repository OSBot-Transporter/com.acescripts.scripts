package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 06/08/2016 at 23:53.
 */

public class RunescapeGuide extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public RunescapeGuide(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 1) != null && script.widgets.get(372, 1).isVisible()) {
            String widgetMessage = script.widgets.get(372, 1).getMessage();

            switch(widgetMessage) {
                case "To start the tutorial use your left mouse button to click on the":
                    tutorialIslandMethods.setStatus("Talking to Runescape Guide.");
                    tutorialIslandMethods.interactWithNpc("RuneScape Guide", "Talk-to");
                    break;
                case "Player controls":
                    tutorialIslandMethods.setStatus("Opening Settings.");
                    script.getTabs().open(Tab.SETTINGS);
                    break;
            }
        } else if(script.widgets.get(421, 1) != null && script.widgets.get(421, 1).isVisible()) {
            String widgetMessage = script.widgets.get(421, 1).getMessage();

            switch(widgetMessage) {
                case "Player controls":
                    tutorialIslandMethods.setStatus("Talking to Runescape Guide.");
                    tutorialIslandMethods. interactWithNpc("RuneScape Guide", "Talk-to");
                    break;
                case "Interacting with scenery":
                    tutorialIslandMethods.setStatus("Opening Door.");
                    tutorialIslandMethods.interactWithObject("Door", "Open");
                    break;
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.widgets.get(269, 97) == null && script.configs.get(406) == 0;
    }
}
