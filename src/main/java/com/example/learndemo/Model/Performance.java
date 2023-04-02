package com.example.learndemo.Model;

import java.util.Date;

public class Performance {
    private int learningZone;

    private String completed;

    private int actualTimeTake;

    public Performance(int learningZone, String completed, int actualTimeTake) {
        this.learningZone = learningZone;
        this.completed = completed;
        this.actualTimeTake = actualTimeTake;
    }

    public int getLearningZone() {
        return learningZone;
    }

    public void setLearningZone(int learningZone) {
        this.learningZone = learningZone;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public int getActualTimeTake() {
        return actualTimeTake;
    }

    public void setActualTimeTake(int actualTimeTake) {
        this.actualTimeTake = actualTimeTake;
    }
}