package com.example.learndemo.Controller;

import com.example.learndemo.Model.Todo;
import com.example.learndemo.Repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todolist")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @PostMapping("/add")
    public Todo addTodo(@RequestBody Todo todo) {
        System.out.println(todo.toString());
        return todoRepository.save(todo);
    }

    @GetMapping("/get/{id}")
    public Todo getTodoById(@PathVariable("id") String id) {
        Todo p = todoRepository.findById(id);
        System.out.println(p.toString());
        return p;
    }

    @GetMapping("/getAll")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/getByTitle/{title}")
    public List<Todo> getTodosByTitle(@PathVariable("title") String title) {
        return todoRepository.findByTitle(title);
    }

    @GetMapping("/getByDescription/{description}")
    public List<Todo> getTodosByDescription(@PathVariable("description") String description) {
        return todoRepository.findByDescription(description);
    }

    @GetMapping("/getByDifficultyLevel/{difficulty}")
    public List<Todo> getTodosByDifficultyLevel(@PathVariable("difficulty") int difficulty) {
        return todoRepository.findByDifficultyLevel(difficulty);
    }

    @GetMapping("/getBySharedWith/{userId}")
    public List<Todo> getTodosBySharedWith(@PathVariable("userId") ObjectId userId) {
        return todoRepository.findBySharedWith(userId);
    }

    @PutMapping("/update/{id}")
    public Todo updateTodo(@PathVariable("id") String id, @RequestBody Todo todo) {
        Todo updatedTodo = todoRepository.findById(id);
        if (updatedTodo != null) {
            updatedTodo.setTitle(todo.getTitle());
            updatedTodo.setDescription(todo.getDescription());
            updatedTodo.setCreatedDate(todo.getCreatedDate());
            updatedTodo.setExpectedTimeTake(todo.getExpectedTimeTake());
            updatedTodo.setDifficultyLevel(todo.getDifficultyLevel());
            updatedTodo.setSharedWith(todo.getSharedWith());
            updatedTodo.setTasklist(todo.getTasklist());
            return todoRepository.save(updatedTodo);
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(@PathVariable("id") ObjectId id) {
        todoRepository.deleteById(id);
    }
}