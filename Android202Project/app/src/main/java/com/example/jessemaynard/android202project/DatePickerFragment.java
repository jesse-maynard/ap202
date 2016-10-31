package com.example.jessemaynard.android202project;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;


/**
 * Created by jessemaynard on 10/30/16.
 */

public class DatePickerFragment extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener{

    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Set the default to the current date.
            final Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
        // Create and return a new instance of the date picker.
            return new android.app.DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        // set the date.
    }
}
