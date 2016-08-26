package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 07/08/2016 at 02:08.
 */

public class BankInstructor extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public BankInstructor(Script script) {
        super(script);
    }

    private void openPollBooth() {
        if(script.bank.isOpen()) {
            script.bank.close();
        } else {
            tutorialIslandMethods.interactWithObject("Poll booth", "Use");
        }
    }

    private void openNextDoor() {
        if(script.widgets.get(345, 1, 0) != null && script.widgets.get(345, 1, 0).isVisible()) {
            script.widgets.get(345, 1, 11).interact("Close");
        } else {
            tutorialIslandMethods.interactWithObject(9721, "Open");
        }
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            String widgetMessage = script.widgets.get(372, 0).getMessage();

            switch(widgetMessage) {
                case "Banking.":
                    tutorialIslandMethods.setStatus("Opening Bank.");
                    tutorialIslandMethods.interactWithObject("Bank booth", "Use");
                    break;
                case "This is your bank box.":
                    tutorialIslandMethods.setStatus("Opening Poll Box.");
                    openPollBooth();
                    break;
                case "This is a poll booth.":
                    tutorialIslandMethods.setStatus("Opening Door.");
                    openNextDoor();
                    break;
            }
        } else if(script.widgets.get(219, 0, 0) != null && script.widgets.get(219, 0, 0).isVisible()) {
            tutorialIslandMethods.setStatus("Closing Interface.");
            script.widgets.get(219, 0, 1).interact("Continue");
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 14;
    }
}
