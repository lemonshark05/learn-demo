package com.example.learndemo.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.text.SimpleDateFormat;
import java.util.*;

@Document("todolist")
public class Todo {
    @Id
    private String id;

    private String goalId;

    private String title;
    private String description;
    private String createdDate;
    private List<String> tasklist;
    private List<String> sharedWith;
    private int expectedTimeTake;
    private int difficultyLevel;


    public Todo() {
    }

    /**
     *
     * @param title
     */
    public Todo(String title) {
        this.title = title;
        this.description = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createdDate = dateFormat.format(new Date());
        this.sharedWith = new ArrayList<>();
    }

    public Todo(String title, String description, String createdDate) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.sharedWith = new ArrayList<>();
    }

    public String getGoalid() {
        return goalId;
    }

    public void setGoalid(String goalid) {
        this.goalId = goalid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<String> tasklist) {
        this.tasklist = tasklist;
    }

    public int getExpectedTimeTake() {
        return expectedTimeTake;
    }

    public void setExpectedTimeTake(int expectedTimeTake) {
        this.expectedTimeTake = expectedTimeTake;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public List<String> getSharedWith() {
        return sharedWith;
    }

    public Todo copyTodo(Todo todo){
        Todo newTodo = todo;
        newTodo.setId(null);
        newTodo.setGoalid(null);
        newTodo.setTasklist(null);
        newTodo.setSharedWith(null);
        return newTodo;
    }

    public void setSharedWith(List<String> sharedWith) {
        this.sharedWith = sharedWith;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", goalid=" + goalId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", tasklist=" + tasklist +
                ", sharedWith=" + sharedWith +
                ", expectedTimeTake=" + expectedTimeTake +
                ", difficultyLevel=" + difficultyLevel +
                '}';
    }
}