package com.example.jessemaynard.android202project;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;

import java.util.Calendar;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import static android.text.format.DateFormat.is24HourFormat;

/**
 * Created by jessemaynard on 10/30/16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TextView setTime;
//    private SimpleDateFormat dateFormat;

    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Set the default values to the current time
            final Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
//            dateFormat = new SimpleDateFormat("hh:mm", Locale.getDefault());

        // Create and return new instance of the TimePicker Dialog.
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    is24HourFormat(getActivity()));
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
