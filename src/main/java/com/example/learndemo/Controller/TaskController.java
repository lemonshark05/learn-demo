package com.example.learndemo.Controller;
import com.example.learndemo.Model.Task;
import com.example.learndemo.Model.Todo;
import com.example.learndemo.Repository.TaskRepository;
import com.example.learndemo.Repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/getAll")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Task getTaskById(@PathVariable("id") ObjectId id) {
        return taskRepository.findById(id);
    }

    @PutMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        Task updatedTask = taskRepository.findById(new ObjectId(task.getId()));
        if (updatedTask != null) {
            updatedTask.setTodoId(task.getTodoId());
            updatedTask.setUserId(task.getUserId());
            updatedTask.setGoalId(task.getGoalId());
            updatedTask.setDueDate(task.getDueDate());
            updatedTask.setReviewDate(task.getReviewDate());
            updatedTask.setPerformance(task.getPerformance());
            return taskRepository.save(updatedTask);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable("id") ObjectId id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/getByTodo/{todoId}")
    public List<Task> getByTodoId(@PathVariable("todoId") ObjectId todoId) {
        return taskRepository.findByTodoId(todoId);
    }

    @GetMapping("/getByUser/{userId}")
    public List<Task> getByUserId(@PathVariable("userId") ObjectId userId) {

        return taskRepository.findByUserId(userId);
    }

    @GetMapping("/getByGoal/{goalId}")
    public List<Task> getByGoalId(@PathVariable("goalId") ObjectId goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    @GetMapping("/getByUserandGoal")
    public Map<String, Object> getByUserIdandGoalId(@RequestParam("userId") ObjectId userId,
                                                    @RequestParam("goalId") ObjectId goalId) {
        List<Task> tasklist = taskRepository.findByUserAndGoal(userId, goalId);

        Map<String, Object> response = new HashMap<>();
        // Create a List object to hold the goal data
        List<Map<String, Object>> tasks = new ArrayList<>();

        // Retrieve the goal data from the goals collection and add it to the goals List
        for (Task task: tasklist) {
            Map<String, Object> taskMap = new HashMap<>();
            Todo todo = todoRepository.findById(new ObjectId(task.getTodoId()));
            taskMap.put("task", task);
            if (todo  != null) {
                taskMap.put("todo", todo);
            }
            tasks.add(taskMap);
        }
        response.put("tasklist", tasks);
        // Return the customized JSON response
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}