package com.example.todo.server.controller;
import com.example.todo.server.model.ToDo.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TodoRequest {
    @NotBlank
    private String name;
    @NotNull
    private Status status;
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
