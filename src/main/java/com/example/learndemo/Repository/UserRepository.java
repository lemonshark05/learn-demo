package com.example.learndemo.Repository;
import com.example.learndemo.Model.GoalItem;
import com.example.learndemo.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Document("users")
public interface UserRepository extends MongoRepository<User, Integer> {

    @Query("{'_id' : ?0}")
    User findById(ObjectId id);

    @Query("{'username': '?0'}")
    User findByUsername(String username);

    @Query("{'email': '?0'}")
    User findByEmail(String email);

    User save(User update);
    @Query("{'_id' : ?0}, {'$push' : {'todolist' : ?1}}")
    void pushToTodoList(ObjectId id, ObjectId todoId);

    @Query("{'_id' : ?0}, {'$push' : {'goallist' : ?1}}")
    void pushToGoalList(ObjectId id, GoalItem goalId);

    @Query("{'_id' : ?0}, {'$push' : {'tasklist' : ?1}}")
    void pushToTaskList(ObjectId id, ObjectId taskId);

    @Query("{'_id' : ?0}, {'$set' : {'email' : ?1}}")
    void updateEmail(ObjectId id, String email);

    @Query("{'_id' : ?0}, {'$set' : {'goallist' : ?1}}")
    void updateGoalId(ObjectId id, ObjectId goalId);

    @Query(value="{'_id' : ?0}", delete = true)
    void deleteById(ObjectId id);
}
