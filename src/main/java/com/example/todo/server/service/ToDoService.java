package com.example.todo.server.service;

import com.example.todo.server.model.ToDo;
import com.example.todo.server.controller.TodoRequest;
import com.example.todo.server.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }
    public ToDo createTodo(TodoRequest todoRequest) {
        ToDo todo = new ToDo(null, todoRequest.getName(), todoRequest.getStatus());
        int id = toDoRepository.createTodo(todo);
        todo.setId(id);
        return todo;
    }
    public ToDo updateTodo(int id, TodoRequest request) {
        ToDo updatedTodo = toDoRepository.findById(id);

        if(updatedTodo==null){
            throw new EntityNotFoundException("Todo with id:" +id + " does not exist.");
        }
        updatedTodo.setName(request.getName());
        updatedTodo.setStatus(request.getStatus());
        toDoRepository.updateTodo(updatedTodo);

        return updatedTodo;
    }
    public void deleteTodo(int id) {
        toDoRepository.deleteById(id);
    }
}

