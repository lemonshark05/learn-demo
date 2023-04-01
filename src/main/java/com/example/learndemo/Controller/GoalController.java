package com.example.learndemo.Controller;
import com.example.learndemo.Model.Goal;
import com.example.learndemo.Repository.GoalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goals")
public class GoalController {
    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/create")
    public Goal createGoal(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    @GetMapping("/get/{id}")
    public Goal getGoalById(@PathVariable("id") ObjectId id) {
        return goalRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    public Goal updateGoal(@PathVariable("id") ObjectId id, @RequestBody Goal goal) {
        Goal updatedGoal = goalRepository.findById(id);
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

    @DeleteMapping("/delete/{id}")
    public void deleteGoal(@PathVariable("id") ObjectId id) {
        goalRepository.deleteById(id);
    }

    @GetMapping("/getByTitle/{title}")
    public List<Goal> getByTitle(@PathVariable("title") String title) {
        return goalRepository.findByTitle(title);
    }

    @GetMapping("/getByAuthor/{title}")
    public List<Goal> getByAuthor(@PathVariable("author") String author) {
        return goalRepository.findByAuthor(author);
    }

    @GetMapping("/getByDescription/{description}")
    public List<Goal> getByDescription(@PathVariable("description") String description) {
        return goalRepository.findByDescription(description);
    }

    @GetMapping("/getByTargetDate")
    public List<Goal> getByTargetDate(@RequestParam("targetDate") Date targetDate) {
        return goalRepository.findByTargetDate(targetDate);
    }

    @GetMapping("/getBySharedWith/{id}")
    public List<Goal> getBySharedWith(@PathVariable("id") ObjectId id) {
        return goalRepository.findBySharedWith(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

