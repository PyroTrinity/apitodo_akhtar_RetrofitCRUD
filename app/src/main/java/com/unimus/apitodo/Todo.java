package com.unimus.apitodo;

public class Todo {
    private int id;
    private String title;
    private String created_at;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
