package com.acescripts.scripts.overloadaio.tutorialisland.methods;

import com.acescripts.scripts.overloadaio.OverloadAIO;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WalkingEvent;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.ConditionalSleep;

import static org.osbot.rs07.script.MethodProvider.random;
import static org.osbot.rs07.script.MethodProvider.sleep;

/**
 * Created by Transporter on 07/08/2016 at 00:22.
 */

public class TutorialIslandMethods {
    private Script script;

    public TutorialIslandMethods(Script script) {
        this.script = script;
    }

    public void setStatus(String status) {
        OverloadAIO.status = status;
    }

    public void interactWithNpc(String npcName, String interactOption) {
        NPC npc = script.getNpcs().closest(npcName);

        if (npc != null) {
            if(npc.isVisible() && script.myPosition().distance(npc.getPosition()) < 7  && !script.myPlayer().isAnimating() && !script.myPlayer().isMoving()) {
                npc.interact(interactOption);
                if(npc != null) {
                    for(int i = 0; i < npc.getActions().length; i++) {
                        npc.getActions();
                    }
                    npc.getActions();
                }
                npc.getActions();
                new ConditionalSleep(10000) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() || script.myPlayer().isMoving() || script.myPlayer().isInteracting(npc);
                    }
                }.sleep();
            } else {
                if(script.myPosition().distance(npc.getPosition()) > 5) {
                    script.walking.walk(npc);
                } else {
                    script.getCamera().toPosition(npc.getPosition());
                }
            }
        }
    }

    public void interactWithObject(String objectName, String interactOption) {
        Entity object = script.objects.closest(objectName);

        if(object != null) {
            if(object.isVisible() && !script.myPlayer().isAnimating() && !script.myPlayer().isMoving()) {
                object.interact(interactOption);
                new ConditionalSleep(3500) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() || script.myPlayer().isMoving();
                    }
                }.sleep();
            } else {
                if(script.myPosition().distance(object.getPosition()) > 5) {
                    script.walking.walk(object);
                } else {
                    script.getCamera().toPosition(object.getPosition());
                }
            }
        }
    }

    public void interactWithObject(int objectName, String interactOption) {
        Entity object = script.objects.closest(objectName);

        if(object != null) {
            if(object.isVisible() && !script.myPlayer().isAnimating() && !script.myPlayer().isMoving()) {
                object.interact(interactOption);
                new ConditionalSleep(3500) {
                    @Override
                    public boolean condition() {
                        return script.myPlayer().isAnimating() || script.myPlayer().isMoving();
                    }
                }.sleep();
            } else {
                if(script.myPosition().distance(object.getPosition()) > 5) {
                    script.walking.walk(object);
                } else {
                    script.getCamera().toPosition(object.getPosition());
                }
            }
        }
    }

    private boolean canLight(Position p) {
        for (RS2Object current : script.objects.get(p.getX(), p.getY())) {
            if (current != null && current.getPosition().equals(p) && !current.getName().equals("null")) {
                return false;
            }
        }
        return true;
    }

    public boolean walkExact(Script script, Position position) {
        WalkingEvent event = new WalkingEvent(position);
        event.setMinDistanceThreshold(0);
        return script.execute(event).hasFinished();
    }

    public void createFire() throws InterruptedException {
        Area firemakingArea = new Area(3101, 3098, 3104, 3094);

        if(canLight(script.myPosition())) {
            useInventoryItems("Logs", "Tinderbox");
        } else {
            for(Position fireMakePos: firemakingArea.getPositions()) {
                if(!canLight(script.myPosition()) && canLight(fireMakePos) && !script.myPosition().equals(fireMakePos)) {
                    walkExact(script, fireMakePos);
                    new ConditionalSleep(3500) {
                        @Override
                        public boolean condition() {
                            return script.myPosition().equals(fireMakePos);
                        }
                    }.sleep();
                }
            }
        }
    }

    public void cookShrimp() throws InterruptedException {
        Entity fire = script.objects.closest("Fire");

        if (fire != null) {
            if (script.getInventory().isItemSelected()) {
                if (fire.isVisible() && !script.myPlayer().isAnimating() && !script.myPlayer().isMoving()) {
                    fire.interact("Use");
                    new ConditionalSleep(5000) {
                        @Override
                        public boolean condition() {
                            return script.myPlayer().isAnimating() || script.myPlayer().isMoving();
                        }
                    }.sleep();
                } else {
                    script.camera.toPosition(fire.getPosition());
                }
            } else {
                script.getInventory().getItem("Raw shrimps").interact("Use");
                new ConditionalSleep(2000) {
                    @Override
                    public boolean condition() {
                        return script.getInventory().isItemSelected();
                    }
                }.sleep();
            }
        } else {
            if (script.inventory.contains("Logs")) {
                createFire();
            } else {
                interactWithObject("Tree", "Chop down");
            }
        }
    }

    public void useItemOnObject(String objectName, String itemName) throws InterruptedException {
        Entity object = script.objects.closest(objectName);

        if (object != null) {
            script.camera.toPosition(object.getPosition());

            if (script.getInventory().isItemSelected()) {
                if (object.isVisible()) {
                    object.interact("Use");
                    sleep(random(4000, 5000));
                } else {
                    script.camera.toPosition(object.getPosition());
                }
            } else {
                script.getInventory().getItem(itemName).interact("Use");
                new ConditionalSleep(2000) {
                    @Override
                    public boolean condition() {
                        return script.getInventory().isItemSelected();
                    }
                }.sleep();
            }
        }
    }

    public void useInventoryItems(String item1, String item2) {
        if(script.getInventory().isItemSelected()) {
            script.getInventory().getItem(item1).interact("Use");
            new ConditionalSleep(2000) {
                @Override
                public boolean condition() {
                    return script.myPlayer().isAnimating() && script.myPlayer().isMoving();
                }
            }.sleep();
        } else {
            script.getInventory().getItem(item2).interact("Use");
            new ConditionalSleep(2000) {
                @Override
                public boolean condition() {
                    return script.getInventory().isItemSelected();
                }
            }.sleep();
        }
    }

    public void clickContinue() throws InterruptedException {
        if(script.widgets.get(231, 1) != null && script.widgets.get(231, 1).isVisible()) {
            script.widgets.get(231, 2).interact();
            sleep(random(250, 750));
        } else if(script.widgets.get(162, 32) != null && script.widgets.get(162, 32).isVisible()) {
            script.widgets.get(162, 33).interact();
            sleep(random(250, 750));
        } else if(script.widgets.get(11, 1) != null && script.widgets.get(11, 1).isVisible()) {
            script.widgets.get(11, 3).interact();
            sleep(random(250, 750));
        } else if(script.widgets.get(193, 1) != null && script.widgets.get(193, 1).isVisible()) {
            script.widgets.get(193, 4).interact();
            sleep(random(250, 750));
        } else if(script.widgets.get(217, 1) != null && script.widgets.get(217, 1).isVisible()) {
            script.widgets.get(217, 2).interact();
            sleep(random(250, 750));
        } else if(script.widgets.get(229, 0) != null && script.widgets.get(229, 0).isVisible()) {
            script.widgets.get(229, 1).interact();
            sleep(random(250, 750));
        }
    }
}
