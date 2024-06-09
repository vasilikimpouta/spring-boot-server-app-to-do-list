package com.example.todo.server.controller;

import com.example.todo.server.model.ToDo;
import com.example.todo.server.service.ToDoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private final ToDoService toDoService;
    private final ToDoResponseMapper toDoResponseMapper;
    public ToDoController(ToDoService toDoService, ToDoResponseMapper toDoResponseMapper) {
        this.toDoService = toDoService;
        this.toDoResponseMapper = toDoResponseMapper;
    }
    @GetMapping
    public List<ToDoResponse> getTodos() {
        return toDoService.getToDos()
                .stream()
                .map(toDoResponseMapper::map)
                .collect(Collectors.toList());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDoResponse createToDo(@Valid @RequestBody TodoRequest toDo) {
         return toDoResponseMapper.map(toDoService.createTodo(toDo));
    }

    @PutMapping("{id}")
    public ToDoResponse updateToDo(@PathVariable int id, @Valid @RequestBody TodoRequest request) {
         ToDo updatedTodo=toDoService.updateTodo(id, request);
         return toDoResponseMapper.map(updatedTodo);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable int id) {
        toDoService.deleteTodo(id);
    }

}
