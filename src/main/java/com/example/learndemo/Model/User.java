package com.example.learndemo.Model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    private String username;
    private String password;
    private String title;
    private String email;
    private List<ObjectId> todolist;
    //goal add a duedate
    private List<GoalItem> goallist;
    private List<ObjectId> tasklist;

    // Default constructor
    public User() {
    }
    public User(String username) {
        this.username = username;
        this.goallist = new ArrayList<>();
    }

    public User(ObjectId id, String password, String email, String username) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.goallist = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ObjectId> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<ObjectId> todolist) {
        this.todolist = todolist;
    }

    public List<ObjectId> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<ObjectId> tasklist) {
        this.tasklist = tasklist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<GoalItem> getGoallist() {
        return goallist;
    }

    public void setGoallist(List<GoalItem> goallist) {
        this.goallist = goallist;
    }

    public void addGoal(ObjectId goalId, String dueDate) {
        this.goallist.add(new GoalItem(goalId, dueDate));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", todolist=" + todolist +
                ", goallist=" + goallist +
                ", tasklist=" + tasklist +
                '}';
    }
}
