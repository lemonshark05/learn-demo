package com.example.learndemo.Model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document("todolist")
public class Todo {
    @Id
    private String id;
    private String title;
    private String description;
    private Date createdDate;
    private List<ObjectId> tasklist;
    private List<ObjectId> sharedWith;
    private int expectedTimeTake;
    private int difficultyLevel;


    /**
     *
     * @param date
     * @param num
     * @return
     */
    public Date getAfterDate(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, num);
        Date res = calendar.getTime();
        return res;
    }

    public Todo() {
    }

    /**
     *
     * @param title
     */
    public Todo(String title) {
        this.title = title;
        this.description = "";
        this.createdDate = new Date();
        this.sharedWith = new ArrayList<>();
    }

    public Todo(String id, String title, String description, Date createdAt) {
        this.title = title;
        this.description = description;
        this.createdDate = createdAt;
        this.sharedWith = new ArrayList<>();
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ObjectId> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<ObjectId> tasklist) {
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

    public List<ObjectId> getSharedWith() {
        return sharedWith;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", tasklist=" + tasklist +
                ", sharedWith=" + sharedWith +
                ", expectedTimeTake=" + expectedTimeTake +
                ", difficultyLevel=" + difficultyLevel +
                '}';
    }

    public void setSharedWith(List<ObjectId> sharedWith) {
        this.sharedWith = sharedWith;
    }
}