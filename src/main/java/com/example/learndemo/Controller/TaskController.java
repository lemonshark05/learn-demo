package com.example.learndemo.Controller;
import com.example.learndemo.Model.Task;
import com.example.learndemo.Repository.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/get/{id}")
    public Task getTaskById(@PathVariable("id") ObjectId id) {
        return taskRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable("id") ObjectId id, @RequestBody Task task) {
        Task updatedTask = taskRepository.findById(id);
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

    @GetMapping("/getByUser/{id}")
    public List<Task> getByUserId(@PathVariable("userid") ObjectId id) {
        return taskRepository.findByUserId(id);
    }

    @GetMapping("/getByGoal/{goalId}")
    public List<Task> getByGoalId(@PathVariable("goalId") ObjectId goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}