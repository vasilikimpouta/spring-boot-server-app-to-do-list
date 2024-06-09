package com.example.todo.server.controller;

import com.example.todo.server.model.User;
import com.example.todo.server.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getUsers()
                .stream()
                .map(user -> new UserResponse(user.getName(), user.getUserName(), user.getId())).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return new UserResponse(user.getName(), user.getUserName(), user.getId());
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @Valid @RequestBody UserRequest userRequest) {
        User updateUser = userService.updateUser(id, userRequest);
        return new UserResponse(updateUser.getName(), updateUser.getUserName(), updateUser.getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
     if(!userService.deleteUser(id)){
         throw new EntityNotFoundException() ;
     };
    }
}
