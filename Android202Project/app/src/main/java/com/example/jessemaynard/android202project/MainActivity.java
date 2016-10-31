package com.example.jessemaynard.android202project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TODO_INDEX = "Index";
    public static final String TODO_TITLE = "Title";
    public static final String TODO_TEXT = "Text";
    public static final String TODO_CAT = "Category";

    // Variable to hold filename.
    String filename = "CatItems";
    // List view variable.
    private ListView todoList;
    // Holds the categories.
    private List<Category> cats = new ArrayList<>();
    // Holds the cats and todos and passes them to the custom array adapter.
    private ArrayList<Object> allItems = new ArrayList<>();
    // Custom adapter to display the cats and todos in the list view.
    private CategoryAdapter catAdopter;

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        todoList = (ListView)findViewById(R.id.todo_list);

        //  Create a file to save the data to.
        File filesDir = this.getFilesDir();
        final File catFile = new File(filesDir + File.separator + filename);

        // If statement that either sets up default todos or loads saved data.
        if (catFile.exists()) {
            readFile(catFile);
        } else {
            setupTodos();
            writeFile();
        }
        // Create the list view and update the adapter.
        todoList = (ListView) findViewById(R.id.todo_list);
        updateItems();
        // catAdopter handles the items from the items array.
        catAdopter = new CategoryAdapter(this, allItems);
        todoList.setAdapter(catAdopter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Log.d("..........", data.getStringExtra(TODO_TITLE));

            Todo todo = new Todo(data.getStringExtra(TODO_TITLE), data.getStringExtra(TODO_TEXT),
                    new Date(), new Date(), Integer.parseInt(data.getStringExtra(TODO_CAT)));

            // Add the item to a category, write the file and update the adapter.
            cats.get(todo.getCategoryID()).todos.add(todo);
            writeFile();
            updateItems();
            catAdopter.notifyDataSetChanged();
            writeFile();
        }
    }

    private void setupTodos() {
        cats.add(new Category("Personal", new ArrayList<Todo>()));
        cats.add(new Category("Shopping", new ArrayList<Todo>()));
        cats.add(new Category("Work", new ArrayList<Todo>()));
        cats.add(new Category("Misc.", new ArrayList<Todo>()));

        for (int i = 0; i < cats.size(); i++) {
            cats.get(i).todos.add(new Todo("test", "test test test test test test test test test", new Date(), new Date(), i));
            cats.get(i).todos.add(new Todo("test", "test test test test test test test test test", new Date(), new Date(), i));
            cats.get(i).todos.add(new Todo("test", "test test test test test test test test test", new Date(), new Date(), i));
        }
    }


    // Handle persistence with gson/json

    // Read the file from json.
    private void readFile(File catFile) {
        FileInputStream inputStream = null;
        String catText = "";
        try {
            inputStream = openFileInput(catFile.getName());
            byte[] input = new byte[inputStream.available()];
            while (inputStream.read(input) != -1) {
            }
            catText = new String(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Determine the type of the collection.
            Type collectionType = new TypeToken<List<Category>>() {
            }.getType();
            // Pull the categories into a list.
            List<Category> categoryList = gson.fromJson(catText, collectionType);
            // Create a LinkedList that can from the categories list and be saved to the global
            // categories.
            cats = new LinkedList(categoryList);
        }
    }

    // Write to the file and convert to json.
    private void writeFile() {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

            String json = gson.toJson(cats);
            byte[] bytes = json.getBytes();
            outputStream.write(bytes);

            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add_task) {
            Intent intent = new Intent(this, TodoDetail.class);

            intent.putExtra(TODO_TITLE, "");
            intent.putExtra(TODO_TEXT, "");
            intent.putExtra(TODO_CAT, "");

            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateItems() {
        allItems.clear();
        for (int i = 0; i < cats.size(); i++) {
            allItems.add(cats.get(i).getCatName());
            for (int o = 0; o < cats.get(i).todos.size(); o++) {
                allItems.add(cats.get(i).todos.get(o));
            }
        }
    }

}


