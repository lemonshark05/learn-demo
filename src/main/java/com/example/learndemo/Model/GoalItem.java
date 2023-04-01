package com.example.learndemo.Model;

import org.bson.types.ObjectId;

public class GoalItem {
    private ObjectId goalid;
    private String dueDate;

    private int timePreDay;

    public GoalItem(ObjectId goalid, String dueDate) {
        this.goalid = goalid;
        this.dueDate = dueDate;
    }

    public ObjectId getGoalid() {
        return goalid;
    }

    public String getDueDate() {
        return dueDate;
    }
}
