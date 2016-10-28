package com.example.jessemaynard.android202project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by jessemaynard on 10/27/16.
 */

public class CategoryAdapter extends BaseAdapter {
    // Arraylist for the categories and the items within.
        private ArrayList<Object> items;
        private LayoutInflater layoutInflater;

    // Variables that determine the type of view created.
        private static final int TYPE_TODO = 0;
        private static final int TYPE_CAT = 1;
    // Constructor for the adapter.
        public CategoryAdapter(Context context, ArrayList<Object> object){
            this.items = object;
            this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    // Get number of items to inflate to the view.
        @Override
        public int getCount(){
            return items.size();
        }
    // Get the position we're at in the array.
        @Override
        public long getItemId(int position){
                return position;
            }
    // Get an items position in the array.
        @Override
        public Object getItem(int position){
            return items.get(position);
        }
    // Determine the number of view types to create in the adapter.
        @Override
        public int getViewTypeCount(){
            return 2;
        }
    // Determine the type of the item at the position in the array.
        @Override
        public int getItemViewType(int position){
            if (getItem(position) instanceof Todo){
                return TYPE_TODO;
            }
            return TYPE_CAT;
        }
    //Determine the ability to interact with the view.
        @Override
        public boolean isEnabled(int position){
            return false;
        }
    //Determine the type of view being created and insert the correct information.
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            int type = getItemViewType(position);
            if (convertView == null){
                if (type == TYPE_TODO){
                    convertView = layoutInflater.inflate(R.layout.todo_list_item, parent, false);
                } else if (type == TYPE_CAT){
                    convertView = layoutInflater.inflate(R.layout.todo_list_cat, parent, false);
                }
            }

            if (type == TYPE_TODO){
                Todo todo = (Todo)getItem(position);

                TextView title = (TextView) convertView.findViewById(R.id.todo_title);
                TextView text = (TextView) convertView.findViewById(R.id.todo_text);
                TextView date = (TextView) convertView.findViewById(R.id.todo_date);

                title.setText(todo.getTodoTitle());
                text.setText(todo.getTodoText());
//                date.setText(todo.getTodoCreatedDate().toString());
            } else if(type == TYPE_CAT){
                String catName = (String) getItem(position);
                TextView category = (TextView) convertView.findViewById(R.id.category);
                category.setText(catName);
            }
            return convertView;
        }
}
