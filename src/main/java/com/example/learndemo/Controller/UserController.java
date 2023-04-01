package com.example.learndemo.Controller;
import com.example.learndemo.Model.User;
import com.example.learndemo.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
//    @Async("taskExecutor")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.toString());
        return userRepository.save(user);
    }

    @PostMapping("/login")
//    @Async("taskExecutor")
    public User login(@RequestParam("username") String username,
                      @RequestParam("password") String password){
        System.out.println("username: "+username+",\npassword: "+password);
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") ObjectId id) {
        return userRepository.findById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable("id") ObjectId id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id);
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
    @PostMapping("/{userId}/addGoal")
    public User addGoal(@PathVariable("userId") ObjectId userId,
                        @RequestParam("goalId") ObjectId goalId,
                        @RequestParam("dueDate") String dueDate) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.addGoal(goalId, dueDate);
            return userRepository.save(user);
        } else {
            return null;
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") ObjectId id) {
        userRepository.deleteById(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}