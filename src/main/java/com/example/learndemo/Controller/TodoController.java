package com.example.learndemo.Controller;
import com.example.learndemo.Model.Goal;
import com.example.learndemo.Model.Task;
import com.example.learndemo.Model.Todo;
import com.example.learndemo.Repository.GoalRepository;
import com.example.learndemo.Repository.TaskRepository;
import com.example.learndemo.Repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todolist")
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private GoalRepository goalRepository;

    /**
     * Create a todo
     * @param todo
     * @return
     */
    @PostMapping("/create")
    public Todo createTodo(@RequestBody Todo todo) {
        System.out.println(todo.toString());
        Goal goal = goalRepository.findById(new ObjectId(todo.getGoalid()));
        goal.addTodoList(todo.getId());
        goalRepository.save(goal);
        return todoRepository.save(todo);
    }

    /**
     * Create a new goal list to copy a exist goal list
     * @param todo
     * @return
     */
    @PostMapping("/copyTodo")
    public List<Todo> generateCopyTodo(@RequestBody List<ObjectId> todo) {
        List<Todo> list = new ArrayList<>();
        for(ObjectId id : todo){
            Todo newtodo = todoRepository.findById(id);
            newtodo = newtodo.copyTodo(newtodo);
            todoRepository.save(newtodo);
            list.add(newtodo);
        }
        return list;
    }

    /**
     * To get information of  a todo by ID
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Todo getTodoById(@PathVariable("id") ObjectId id) {

        return todoRepository.findById(id);
    }

    /**
     * To get information of  a todo by goal ID
     * @param goalid
     * @return
     */
    @GetMapping("/getbygoalid/{goalid}")
    public List<Todo> getTodoBygoalId(@PathVariable("goalid") ObjectId goalid) {
        return todoRepository.findByGoalid(goalid);
    }

    /**
     * Get all todo information
     * @return
     */
    @GetMapping("/getAll")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    /**
     * Get information about a todo list by title
     * @param title
     * @return
     */
    @GetMapping("/getByTitle/{title}")
    public List<Todo> getTodosByTitle(@PathVariable("title") String title) {

        return todoRepository.findByTitle(title);
    }

    /**
     * Get information about a todo list by description
     * @param description
     * @return
     */
    @GetMapping("/getByDescription/{description}")
    public List<Todo> getTodosByDescription(@PathVariable("description") String description) {
        return todoRepository.findByDescription(description);
    }

    /**
     * Get information about a todo list by difficultyLevel
     * @param difficulty
     * @return
     */
    @GetMapping("/getByDifficultyLevel/{difficulty}")
    public List<Todo> getTodosByDifficultyLevel(@PathVariable("difficulty") int difficulty) {
        return todoRepository.findByDifficultyLevel(difficulty);
    }

    /**
     * Get information about a  list by id which is in the sharedWith id list
     * @param userId
     * @return
     */
    @GetMapping("/getBySharedWith/{userId}")
    public List<Todo> getTodosBySharedWith(@PathVariable("userId") ObjectId userId) {
        return todoRepository.findBySharedWith(userId);
    }

    @PutMapping("/update")
    public Todo updateTodo(@RequestBody Todo todo) {
        Todo updatedTodo = todoRepository.findById(new ObjectId(todo.getId()));
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
        //delete task
        List<Task> tasklist = taskRepository.findByTodoId(id);
        for(Task task : tasklist){
            taskRepository.deleteById(new ObjectId(task.getId()));
        }
        todoRepository.deleteById(id);
    }
}
