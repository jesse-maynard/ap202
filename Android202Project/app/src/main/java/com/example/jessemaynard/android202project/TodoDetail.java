package com.example.jessemaynard.android202project;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by jessemaynard on 10/27/16.
 */

public class TodoDetail extends AppCompatActivity {
    private int index;
    private EditText todoTitle;
    private EditText todoText;
    private EditText todoCat;
    private TextView dueDate;
    private TextView dueTime;
    private SimpleDateFormat dateFormat;
//    private Spinner todoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_detail);

        todoTitle = (EditText) findViewById(R.id.edit_title);
        todoText = (EditText) findViewById(R.id.edit_text);
        todoCat = (EditText) findViewById(R.id.edit_cat);

        Intent intent = getIntent();
        index = intent.getIntExtra(MainActivity.TODO_INDEX, -1);

        todoTitle.setText(intent.getStringExtra(MainActivity.TODO_TITLE));
        todoText.setText(intent.getStringExtra(MainActivity.TODO_TEXT));

        Button saveButton = (Button) findViewById(R.id.btn_done);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                intent.putExtra(MainActivity.TODO_INDEX, index);
                intent.putExtra(MainActivity.TODO_TITLE, todoTitle.getText().toString());
                intent.putExtra(MainActivity.TODO_TEXT, todoText.getText().toString());
                intent.putExtra(MainActivity.TODO_CAT, todoCat.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public void onBackPressed(){
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_add_task){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }
}
