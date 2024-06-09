package com.example.todo.server.model;

public class User {
    private String name;
    private String userName;
    private Integer id;

    public String getName() {
        return name;
    }
    public User(String name , String userName, Integer id){
        this.name = name;
        this.userName= userName;
        this.id= id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
