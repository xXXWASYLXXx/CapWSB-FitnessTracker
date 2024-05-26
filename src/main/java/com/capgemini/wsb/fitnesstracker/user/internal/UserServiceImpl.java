package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public User createUser(User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public UserDto createUser(UserDto user) {
        return null;
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public List<UserDto> searchUsersByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> searchUsersByAgeGreaterThan(int age) {
        return null;
    }

    @Override

    public UserDto updateUser(Long userId, UserDto userDTO) {
        User user = userMapper.toEntity(userDTO);
        User updatedUser = updateUser(userId, user);
        return userMapper.toDto(updatedUser);
    }
    @Override
    public User updateUser(Long userId, User userDTO) {
        // Znajdź istniejącego użytkownika na podstawie userId
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        // Ustaw nowe dane użytkownika na podstawie przekazanego obiektu UserDTO
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setBirthdate(userDTO.getBirthdate());
        existingUser.setEmail(userDTO.getEmail());
        // Zapisz zaktualizowanego użytkownika w bazie danych
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}