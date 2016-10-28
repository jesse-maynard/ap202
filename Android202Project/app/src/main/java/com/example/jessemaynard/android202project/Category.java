package com.example.jessemaynard.android202project;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by jessemaynard on 10/27/16.
 */

public class Category {

    @SerializedName("catName")
    private String catName;

    @SerializedName("todos")
    public ArrayList<Todo> todos;

    public Category(String catName, ArrayList<Todo> todos) {
        this.catName = catName;
        this.todos = todos;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
