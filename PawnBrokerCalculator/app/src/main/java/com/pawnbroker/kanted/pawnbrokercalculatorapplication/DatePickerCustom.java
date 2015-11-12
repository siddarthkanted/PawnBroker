package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by sikanted on 11/12/2015.
 */
public class DatePickerCustom {

    private Activity mainActivity;

    public DatePickerCustom(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void show(final int editTextId, final Calendar calendar){

        DatePickerDialog datePickerDialog =  new DatePickerDialog(mainActivity, new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEditText(editTextId, calendar);
            }
        }  ,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateEditText(int editTextId, Calendar calendar){
        String value = CalendarUtil.convertCalendarToString(calendar);
        EditText editText = (EditText)mainActivity.findViewById(editTextId);
        editText.setText(value);
    }




}
