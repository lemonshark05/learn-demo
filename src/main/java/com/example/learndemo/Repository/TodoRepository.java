package com.example.learndemo.Repository;

import com.example.learndemo.Model.Todo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


@Document("todolist")
public interface TodoRepository extends MongoRepository<Todo, Integer> {

    //    @Query("{'_id : ObjectId(?0)}")
    @Query("{'_id' : ?0}")
    Todo findById(String id);

    @Query("{'title' : ?0}")
    List<Todo> findByTitle(String title);

    @Query("{'description' : ?0}")
    List<Todo> findByDescription(String description);

    @Query("{'sharedWith' : ?0}")
    List<Todo> findBySharedWith(ObjectId userId);

    @Query("{'difficultyLevel' : ?0}")
    List<Todo> findByDifficultyLevel(int difficultyLevel);

    Todo save(Todo todo);

    @Query("{'_id' : ?0}")
    void deleteById(ObjectId id);
}
