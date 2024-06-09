package com.example.todo.server.controller;

import com.example.todo.server.model.ToDo;
import org.springframework.stereotype.Component;


@Component
public class ToDoResponseMapper {
    public ToDoResponse map(ToDo toDo) {
        return new ToDoResponse(toDo.getId(), toDo.getName(), todoResponseStatus(toDo.getStatus()));
    }

    private ToDoResponse.Status todoResponseStatus(ToDo.Status todoStatus) {
        switch (todoStatus) {
            case NOT_DONE -> {
                return ToDoResponse.Status.NOT_DONE;
            }
            case IN_PROGRESS -> {
                return ToDoResponse.Status.IN_PROGRESS;
            }
            case DONE -> {
                return ToDoResponse.Status.DONE;
            }
        }
        return null;
    }
}
