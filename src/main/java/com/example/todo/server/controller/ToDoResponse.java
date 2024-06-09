package com.example.todo.server.controller;

public record ToDoResponse(Integer id, String name, Status status) {
    enum Status {
        NOT_DONE,
        IN_PROGRESS,
        DONE;
    }
}
