package com.example.learndemo.Repository;

import com.example.learndemo.Model.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


@Document("todolist")
public interface TodoRepository extends MongoRepository<Todo, Integer> {

    @Query("{'_id' : ?0}")
    Todo findById(ObjectId id);

    @Query("{'goalId' : ?0}")
    List<Todo> findByGoalid(ObjectId goalid);
    @Query("{'title' : {$regex : ?0, $options: 'i'}}")
    List<Todo> findByTitle(String title);

    @Query("{'description' : {$regex : ?0, $options: 'i'}}")
    List<Todo> findByDescription(String description);

    @Query("{'sharedWith' : ?0}")
    List<Todo> findBySharedWith(ObjectId userId);

    @Query("{'difficultyLevel' : ?0}")
    List<Todo> findByDifficultyLevel(int difficultyLevel);

    Todo save(Todo todo);

    @Query(value="{'_id' : ?0}", delete = true)
    void deleteById(ObjectId id);
}
