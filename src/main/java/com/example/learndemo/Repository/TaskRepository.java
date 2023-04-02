package com.example.learndemo.Repository;
import com.example.learndemo.Model.Task;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Document("tasks")
public interface TaskRepository extends MongoRepository<Task, Integer> {
    @Query("{'_id' : ?0}")
    Task findById(ObjectId id);
    @Query("{'todoId' : ?0}")
    List<Task> findByTodoId(ObjectId todoId);

    @Query("{'userId' : ?0, 'goalId' : ?1}")
    List<Task> findByUserAndGoal(ObjectId userId, ObjectId goalId);

    @Query("{'userId' : ?0}")
    List<Task> findByUserId(ObjectId userId);

    @Query("{'goalId' : ?0}")
    List<Task> findByGoalId(ObjectId goalId);

    Task save(Task update);

    @Query(value="{'_id' : ?0}", delete = true)
    void deleteById(ObjectId id);
}


