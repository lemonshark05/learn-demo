package com.example.learndemo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("goals")
public class Goal {
    @Id
    private String id;
    private String title;
    private String description;
    private String createdDate;
    //day
    private int recommendTerms;
    //get all todo detail in a list
    private List<String> todolist;
    private List<String> sharedWith;

    private String author;
    private String category;
    public Goal() {
    }
    public Goal(String title, String description, String createdDate, int targetDate) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.recommendTerms = targetDate;
    }
    public String getId() {
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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getTargetDate() {
        return recommendTerms;
    }

    public void setTargetDate(int targetDate) {
        this.recommendTerms = targetDate;
    }

    public List<String> getTodolist() {
        return todolist;
    }

    public void setTodolist(List<String> todolist) {
        this.todolist = todolist;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSharedWith() {
        return sharedWith;
    }
    public List<String> addSharedWith(String userid) {
        this.sharedWith.add(userid);
        return sharedWith;
    }

    public List<String> addTodoList(String id) {
        this.todolist.add(id);
        return todolist;
    }

    public void setSharedWith(List<String> sharedWith) {
        this.sharedWith = sharedWith;
    }

    public int getRecommendTerms() {
        return recommendTerms;
    }

    public void setRecommendTerms(int recommendTerms) {
        this.recommendTerms = recommendTerms;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", recommendTerms=" + recommendTerms +
                ", todolist=" + todolist +
                ", sharedWith=" + sharedWith +
                ", author=" + author +
                ", category='" + category + '\'' +
                '}';
    }
}