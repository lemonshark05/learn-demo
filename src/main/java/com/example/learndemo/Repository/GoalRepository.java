package com.example.learndemo.Repository;

import com.example.learndemo.Model.Goal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

@Document("goals")
public interface GoalRepository extends MongoRepository<Goal, Integer> {
    @Query("{'_id' : ?0}")
    Goal findById(ObjectId id);

    @Query("{'title' : ?0}")
    List<Goal> findByTitle(String title);

    @Query("{'author' : ?0}")
    List<Goal> findByAuthor(String author);

    @Query("{'description' : ?0}")
    List<Goal> findByDescription(String description);

    @Query("{'targetDate' : ?0}")
    List<Goal> findByTargetDate(Date targetDate);

    @Query("{'sharedWith' : ?0}")
    List<Goal> findBySharedWith(ObjectId userId);

    Goal save(Goal goal);

    @Query("{'_id' : ?0}")
    void deleteById(ObjectId id);
}
