package com.example.learndemo.Controller;
import com.example.learndemo.Model.Goal;
import com.example.learndemo.Model.GoalItem;
import com.example.learndemo.Model.Task;
import com.example.learndemo.Model.User;
import com.example.learndemo.Repository.GoalRepository;
import com.example.learndemo.Repository.TaskRepository;
import com.example.learndemo.Repository.TodoRepository;
import com.example.learndemo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Get all information of users
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * user register
     * @param user
     * @return
     */
    @PostMapping("/register")
//    @Async("taskExecutor")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        return userRepository.save(user);
    }

    /**
     * user login
     * @param user
     * @return
     */
    @PostMapping("/login")
//    @Async("taskExecutor")
    public User login(@RequestBody User user){
        //email to login
//        System.out.println("email: "+email+"\npassword: "+password);
        User existingUser = userRepository.findByEmail(user.getEmail());
        System.out.println(existingUser.getPassword());
        System.out.println(user.getPassword());
        if (existingUser != null && passwordEncoder.matches( user.getPassword(), existingUser.getPassword())) {
            System.out.println(existingUser.getEmail());
            return existingUser;
        } else {
            System.out.println("wrong");
            return null;
        }
    }

    /**
     * enroll
     * @param userid
     * @param goalitem
     * @return
     */
    @PostMapping("/enroll")
    public String erollGoalItem(
//            @RequestParam("goalid") String goalid,
            @RequestParam("userid") String userid,
            @RequestBody GoalItem goalitem){
        Goal goal = goalRepository.findById(new ObjectId(goalitem.getGoalid()));
        if (goal == null) {
            return "Goal not found";
        }

        User user = userRepository.findById(new ObjectId(userid));
        if (user == null) {
            return "User not found";
        }

        // Add user to the goal's sharedWith list if not already in it
        if (!goal.getSharedWith().contains(userid)) {
            goal.addSharedWith(userid);
            goalRepository.save(goal);
        }

        // Add the goal item to the user's goallist
        user.getGoallist().add(goalitem);
        userRepository.save(user);

        // Create tasks for all todos associated with the goal
        for (String todoid : goal.getTodolist()) {
            Task task = new Task(todoid, userid, goalitem.getGoalid());
            Task re = taskRepository.save(task);
            System.out.println(re.toString());
        }
        return "Goal item enrolled successfully";
    }

    /**
     * Find a user by user ID
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") ObjectId id) {
        return userRepository.findById(id);
    }

    @GetMapping("/getallinfo/{id}")
    public Map<String, Object> getAllinfo(@PathVariable("id") ObjectId id) {
        // Retrieve the User object from the database
        User user = userRepository.findById(id);

        // Create a Map object to hold the customized JSON response
        Map<String, Object> response = new HashMap<>();

        // Create a List object to hold the goal data
        List<Map<String, Object>> goals = new ArrayList<>();

        // Retrieve the goal data from the goals collection and add it to the goals List
        for (GoalItem goalItem : user.getGoallist()) {
            Goal goal = goalRepository.findById(new ObjectId(goalItem.getGoalid()));
            if (goal != null) {
                Map<String, Object> goalMap = new HashMap<>();
                goalMap.put("id", goal.getId());
                goalMap.put("title", goal.getTitle());
                goalMap.put("description", goal.getDescription());
                goalMap.put("createdDate", goal.getCreatedDate());
                goalMap.put("recommendTerms", goal.getRecommendTerms());
                goalMap.put("todolist", goal.getTodolist());
                goalMap.put("sharedWith", goal.getSharedWith());
                goalMap.put("author", goal.getAuthor());
                goalMap.put("category", goal.getCategory());
                goalMap.put("dueDate", goalItem.getDueDate());
                goalMap.put("timePreDay", goalItem.getTimePreDay());
                goals.add(goalMap);
            }
        }

        // Add the goals List to the response Map
        response.put("goallist", goals);
        response.put("todolist", user.getTodolist());
        response.put("tasklist", user.getTasklist());

        // Return the customized JSON response
        return response;
    }

    /**
     * Update the user information
     * @param user
     * @return
     */
    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User updatedUser = userRepository.findById(new ObjectId(user.getId()));
        if (updatedUser != null) {
            updatedUser.setTitle(user.getTitle());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setGoallist(user.getGoallist());
            if (user.getTodolist() != null && !user.getTodolist().isEmpty()) {
                updatedUser.getTodolist().addAll(user.getTodolist());
            }
            if (user.getTasklist() != null && !user.getTasklist().isEmpty()) {
                updatedUser.getTasklist().addAll(user.getTasklist());
            }
            return userRepository.save(updatedUser);
        } else {
            return null;
        }
    }

    /**
     * Add a goal to a user
     * @param userId
     * @param goalId
     * @param dueDate
     * @return
     */
    @PostMapping("/addGoal/{userId}")
    public User addGoal(@PathVariable("userId") String userId,
                        @RequestParam("goalId") String goalId,
                        @RequestParam("dueDate") String dueDate) {
        User user = userRepository.findById(new ObjectId(userId));
        if (user != null) {
            user.addGoal(goalId, dueDate);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    /**
     * Get all users information
     * @return
     */
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Delete a user by user ID
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") ObjectId id) {
        //delete tasks
        List<Task> tasklist = taskRepository.findByUserId(id);
        for(Task task : tasklist){
            taskRepository.deleteById(new ObjectId(task.getId()));
        }
        userRepository.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
