package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.user.internal.UserDto;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    void deleteUser(Long userId);

    List<UserDto> searchUsersByEmail(String email);

    List<UserDto> searchUsersByAgeGreaterThan(int age);

    UserDto updateUser(Long userId, UserDto userDTO);

    User updateUser(Long userId, User userDTO);

    List<UserDto> getAllUsers();
}
