package com.example.learndemo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Document("tasks")
public class Task {
    @Id
    private String id;

    private String todoId;

    private String userId;

    private String goalId;

    private String reviewDate;

    private String dueDate;

    private List<Performance> performance;
    public Task() {
    }
    public Task(String todoId, String userId, String goalId, String reviewDate, String dueDate) {
        this.todoId = todoId;
        this.userId = userId;
        this.goalId = goalId;
        this.reviewDate = reviewDate;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }
    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
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
