package com.example.learndemo.Controller;
import com.example.learndemo.Model.Goal;
import com.example.learndemo.Model.Task;
import com.example.learndemo.Model.Todo;
import com.example.learndemo.Repository.GoalRepository;
import com.example.learndemo.Repository.TaskRepository;
import com.example.learndemo.Repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private GoalRepository goalRepository;

    /**
     * Create a goal
     * @param goal
     * @return
     */
    @PostMapping("/create")
    public Goal createGoal(@RequestBody Goal goal) {
        // Save the new goal object
        Goal savedGoal = goalRepository.save(goal);
        return savedGoal;
    }

    /**
     * Find a goal by goal ID
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Goal getGoalById(@PathVariable("id") ObjectId id) {
        return goalRepository.findById(id);
    }



    @GetMapping("/getallinfo/{id}")
    public Map<String, Object> getGoalallinfo(@PathVariable("id") ObjectId id) {
        Goal goal = goalRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        // Create a List object to hold the goal data
        List<Todo> todos = new ArrayList<>();
        for(String todoid: goal.getTodolist()){
            Todo todo = todoRepository.findById(new ObjectId(todoid));
            todos.add(todo);
        }
        response.put("goal", goal);
        response.put("todolist",todos);
        // Return the customized JSON response
        return response;
    }

    /**
     * Update the information of a goal
     * @param goal
     * @return
     */
    @PutMapping("/update")
    public Goal updateGoal(@RequestBody Goal goal) {
        Goal updatedGoal = goalRepository.findById(new ObjectId(goal.getId()));
        if (updatedGoal != null) {
            updatedGoal.setTitle(goal.getTitle());
            updatedGoal.setDescription(goal.getDescription());
            updatedGoal.setTargetDate(goal.getTargetDate());
            updatedGoal.setTodolist(goal.getTodolist());
            updatedGoal.setSharedWith(goal.getSharedWith());
            updatedGoal.setAuthor(goal.getAuthor());
            updatedGoal.setCategory(goal.getCategory());
            return goalRepository.save(updatedGoal);
        } else {
            return null;
        }
    }

    /**
     * Get all goals information
     * @return
     */
    @GetMapping("/getAll")
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    /**
     * Delete a goal by goal ID
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteGoal(@PathVariable("id") ObjectId id) {
        List<Todo> todolist = todoRepository.findByGoalid(id);
        for(Todo todo : todolist) {
            List<Task> tasklist = taskRepository.findByTodoId(new ObjectId(todo.getId()));
            for (Task task : tasklist) {
                taskRepository.deleteById(new ObjectId(task.getId()));
            }
            todoRepository.deleteById(new ObjectId(todo.getId()));
        }
        goalRepository.deleteById(id);
    }

    /**
     * Get information about a goal list by title
     * @param title
     * @return
     */
    @GetMapping("/getByTitle/{title}")
    public List<Goal> getByTitle(@PathVariable("title") String title) {
        return goalRepository.findByTitle(title);
    }

    /**
     * Get information about a goal list by author(it is a ID)
     * @param author
     * @return
     */
    @GetMapping("/getByAuthor/{author}")
    public List<Goal> getByAuthor(@PathVariable("author") ObjectId author) {
        return goalRepository.findByAuthor(author);
    }

    /**
     * Get information about a goal list by description
     * @param description
     * @return
     */
    @GetMapping("/getByDescription/{description}")
    public List<Goal> getByDescription(@PathVariable("description") String description) {
        return goalRepository.findByDescription(description);
    }

    /**
     * Get information about a goal list by targetDate
     * @param targetDate
     * @return
     */
    @GetMapping("/getByTargetDate")
    public List<Goal> getByTargetDate(@RequestParam("targetDate") Date targetDate) {
        return goalRepository.findByTargetDate(targetDate);
    }

    /**
     * Get information about a goal list by id which is in the sharedWith id list
     * @param id
     * @return
     */
    @GetMapping("/getBySharedWith/{id}")
    public List<Goal> getBySharedWith(@PathVariable("id") ObjectId id) {
        return goalRepository.findBySharedWith(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

