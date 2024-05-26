package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {

        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + " passed to the request");
        // Zapis nowego użytkownika
        User user = userMapper.toEntity(userDto);
        User savedUser = userService.createUser(user);
        return userMapper.toDto(savedUser);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {userService.deleteUser(userId);
        return ResponseEntity.ok("User with id " + userId + " has been deleted");
    }


    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        User updatedUser = userService.updateUser(userId, user);
        return userMapper.toDto(updatedUser);
    }

}