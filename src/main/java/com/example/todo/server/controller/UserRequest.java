package com.example.todo.server.controller;


import jakarta.validation.constraints.NotBlank;

public record UserRequest (@NotBlank String name, @NotBlank String username){};



