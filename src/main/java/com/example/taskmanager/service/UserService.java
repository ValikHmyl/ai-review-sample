package com.example.taskmanager.service;

import com.example.taskmanager.exception.ResourceNotFoundException;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
