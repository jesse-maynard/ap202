package com.example.jessemaynard.android202project;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * Created by jessemaynard on 10/27/16.
 */

public class Todo implements Comparable<Todo> {
    @SerializedName("todoTitle")
    private String todoTitle;
    @SerializedName("todoText")
    private String todoText;
    @SerializedName("todoDueDate")
    private Date todoDueDate;
    @SerializedName("todoCreatedDate")
    private Date todoCreatedDate;
    @SerializedName("categoryID")
    private int categoryID;

    public Todo(String todoTitle, String todoText, Date todoCreatedDate, Date todoDueDate, int categoryID) {
        this.todoTitle = todoTitle;
        this.todoText = todoText;
        this.todoDueDate = todoDueDate;
        this.todoCreatedDate = todoCreatedDate;
        this.categoryID = categoryID;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public Date getTodoDueDate() {
        return todoDueDate;
    }

    public void setTodoDueDate(Date todoDueDate) {
        this.todoDueDate = todoDueDate;
    }

    public Date getTodoCreatedDate() {
        return todoCreatedDate;
    }

    public void setTodoCreatedDate(Date todoCreatedDate) {
        this.todoCreatedDate = todoCreatedDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int compareTo(Todo another){
        //Sort the items in the view
            return another.getTodoDueDate().compareTo(this.todoCreatedDate);
    }

}