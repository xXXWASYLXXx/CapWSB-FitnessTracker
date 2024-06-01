package com.capgemini.wsb;//package com.capgemini.wsb;
//import com.capgemini.wsb.fitnesstracker.user.api.User;
//import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;
//import com.capgemini.wsb.fitnesstracker.user.api.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//


import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.capgemini.wsb.fitnesstracker")
public class FitnessTracker
{ public static void main(String[] args)
{ SpringApplication.run(FitnessTracker.class, args); }
}


//@RestController
//
//@RequestMapping("/users")
//
//public class FitnessTracker {


//    private final UserService userService;
//
//
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @GetMapping("/{userId}")
//    public UserDto getUserById(@PathVariable Long userId) {
//        return userService.getUserById(userId);
//    }
//
//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto userDTO) {
//        return userService.createUser(userDTO);
//    }
//
//
//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//    }
//
//
//    @GetMapping("/search")
//    public List<UserDto> searchUsersByEmail(@RequestParam String email) {
//        return userService.searchUsersByEmail(email);
//    }
//
//
//
//    @GetMapping("/searchByAge")
//    public List<UserDto> searchUsersByAgeGreaterThan(@RequestParam int age) {
//        return userService.searchUsersByAgeGreaterThan(age);
//    }
//
//
//
//    @PutMapping("/{userId}")
//    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDTO) {
//        return userService.updateUser(userId, userDTO);
//    }
//}
