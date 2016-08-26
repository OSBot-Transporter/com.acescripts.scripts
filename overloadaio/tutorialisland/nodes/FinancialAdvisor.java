package com.acescripts.scripts.overloadaio.tutorialisland.nodes;

import com.acescripts.scripts.overloadaio.framework.Node;
import com.acescripts.scripts.overloadaio.tutorialisland.methods.TutorialIslandMethods;
import org.osbot.rs07.script.Script;

/**
 * Created by Transporter on 07/08/2016 at 02:06.
 */

public class FinancialAdvisor extends Node {

    private TutorialIslandMethods tutorialIslandMethods = new TutorialIslandMethods(script);

    public FinancialAdvisor(Script script) {
        super(script);
    }

    @Override
    public void execute() throws InterruptedException {
        if(script.widgets.get(372, 0) != null && script.widgets.get(372, 0).isVisible()) {
            if(script.widgets.get(372, 0).getMessage().equals("Financial advice.")) {
                tutorialIslandMethods.setStatus("Talking to Financial Advisor Interface.");
                tutorialIslandMethods.interactWithNpc("Financial Advisor", "Talk-to");
            } else if(script.widgets.get(372, 2).getMessage().equals("Continue through the next door.")) {
                tutorialIslandMethods.setStatus("Opening Door.");
                tutorialIslandMethods.interactWithObject(9722, "Open");
            }
        } else {
            tutorialIslandMethods.setStatus("Clicking Continue.");
            tutorialIslandMethods.clickContinue();
        }
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.configs.get(406) == 15;
    }
}
