package com.example.learndemo.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("goals")
public class Goal {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private Date createdDate;
    //day
    private int recommendTerms;
    //get all todo detail in a list
    private List<ObjectId> todolist;
    private List<ObjectId> sharedWith;

    private ObjectId author;
    private String category;

    public Goal() {
    }
    public Goal(String title, String description, Date createdDate, int targetDate) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.recommendTerms = targetDate;
    }
    public ObjectId getId() {
        return id;
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

    public int getTargetDate() {
        return recommendTerms;
    }

    public void setTargetDate(int targetDate) {
        this.recommendTerms = targetDate;
    }

    public List<ObjectId> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<ObjectId> todolist) {
        this.todolist = todolist;
    }

    public ObjectId getAuthor() {
        return author;
    }

    public void setAuthor(ObjectId author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ObjectId> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<ObjectId> sharedWith) {
        this.sharedWith = sharedWith;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", recommendTerms=" + recommendTerms +
                ", todolist=" + todolist +
                ", sharedWith=" + sharedWith +
                ", author=" + author +
                ", category='" + category + '\'' +
                '}';
    }
}