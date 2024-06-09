package com.example.todo.server.service;

import com.example.todo.server.controller.UserRequest;
import com.example.todo.server.model.User;
import com.example.todo.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getAllUsers();

    }

    public User createUser(UserRequest userRequest) {
        User user = new User(userRequest.name(), userRequest.username(), 1);
        user.setId(userRepository.createUser(user));
        return user;
    }

    ;

    public User updateUser(Integer id, UserRequest userRequest) {
        List<User> users = userRepository.getAllUsers();
        List<User> userId = new ArrayList<User>();
        User updatedUser = null;
        for (User user : users) {
            if (user.getId() == id) {
                userId.add(user);
            }
        }
        if (!userId.isEmpty()) {
            updatedUser = userId.getFirst();
            updatedUser.setName(userRequest.name());
            updatedUser.setUserName(userRequest.username());

        }
        assert updatedUser != null;
        System.out.println(updatedUser.getId());
        userRepository.updateUser(updatedUser);
        return updatedUser;
    }

    public boolean deleteUser(Integer id) {
        List<User> users = userRepository.getAllUsers();
        List<User> usersId = new ArrayList<>();
        for (User user : users) {
            if (user.getId() == id) {
                usersId.add(user);
                userRepository.deleteUser(id);
            }
        }
        return !usersId.isEmpty();
    }
}