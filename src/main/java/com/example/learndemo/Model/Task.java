package com.example.learndemo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
@Document("tasks")
public class Task {
    @Id
    private ObjectId id;

    private ObjectId todoId;

    private ObjectId userId;

    private ObjectId goalId;

    private String reviewDate;

    private String dueDate;

    private List<Performance> performance;
    public Task() {
    }
    public Task(ObjectId todoId, ObjectId userId, ObjectId goalId, String reviewDate, String dueDate) {
        this.todoId = todoId;
        this.userId = userId;
        this.goalId = goalId;
        this.reviewDate = reviewDate;
        this.dueDate = dueDate;
    }

    public ObjectId getId() {
        return id;
    }
    public ObjectId getTodoId() {
        return todoId;
    }

    public void setTodoId(ObjectId todoId) {
        this.todoId = todoId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getGoalId() {
        return goalId;
    }

    public void setGoalId(ObjectId goalId) {
        this.goalId = goalId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public List<Performance> getPerformance() {
        return performance;
    }

    public void setPerformance(List<Performance> performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", todoId=" + todoId +
                ", userId=" + userId +
                ", goalId=" + goalId +
                ", reviewDate='" + reviewDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", performance=" + performance +
                '}';
    }
}
