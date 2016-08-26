package com.acescripts.scripts.overloadaio.framework;

/**
 * Created by Transporter on 26/07/2016 at 09:49.
 */

public class CombatTaskItems {
    private int taskNumber;
    private String skillName;
    private String taskType;
    private int taskGoal;
    private String taskMonsterType;
    private String taskMonsterLocation;
    private boolean taskBuryBones;

    public CombatTaskItems(int taskNumber, String skillName, String taskType, int taskGoal, String taskMonsterType, String taskMonsterLocation, boolean taskBuryBones) { //declare variables for this class
        this.taskNumber = taskNumber;
        this.skillName = skillName;
        this.taskType = taskType;
        this.taskGoal = taskGoal;
        this.taskMonsterType = taskMonsterType;
        this.taskMonsterLocation = taskMonsterLocation;
        this.taskBuryBones = taskBuryBones;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getTaskType() {
        return taskType;
    }

    public int getTaskGoal() {
        return taskGoal;
    }

    public String getTaskMonsterType() {
        return taskMonsterType;
    }

    public String getTaskMonsterLocation() {
        return taskMonsterLocation;
    }

    public boolean getTaskBuryBones() {
        return taskBuryBones;
    }

    @Override
    public String toString() {
        return "Task Number: " + getTaskNumber() + " Skill Name: " + getSkillName() + " Task Type: " + getTaskType() + " Task Goal: " + getTaskGoal() + " Monster Type: " + getTaskMonsterType() + " Monster Location: " + getTaskMonsterLocation() + " Bury Bones: " + getTaskBuryBones();
    }
}
