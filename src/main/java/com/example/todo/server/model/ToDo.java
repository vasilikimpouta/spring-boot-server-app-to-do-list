package com.example.todo.server.model;

import java.util.UUID;

public class ToDo {
    private Integer id;
    private String name;
    private Status status;

    public ToDo(Integer id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status=status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        NOT_DONE,
        IN_PROGRESS,
        DONE;
    }

}
