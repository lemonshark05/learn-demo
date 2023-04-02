package com.example.learndemo.Model;

public class GoalItem {
    private String goalid;
    private String dueDate;

    private int timePreDay;

    public GoalItem(String goalid, String dueDate) {
        this.goalid = goalid;
        this.dueDate = dueDate;
    }

    public String getGoalid() {
        return goalid;
    }

    public String getDueDate() {
        return dueDate;
    }
}
