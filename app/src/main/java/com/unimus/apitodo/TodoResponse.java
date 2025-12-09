package com.unimus.apitodo;

import java.util.List;

public class TodoResponse {
    boolean status;
    List<Todo> data;

    public  List<Todo> getData(){
        return data;
    }
}
