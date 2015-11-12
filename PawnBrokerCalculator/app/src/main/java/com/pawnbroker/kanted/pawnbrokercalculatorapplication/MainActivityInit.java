package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by sikanted on 11/12/2015.
 */
public class MainActivityInit {

    private Activity activity;

    public MainActivityInit(Activity mainActivity) {
        this.activity = mainActivity;
    }

    public void init(){
        initAmount();
        initInterestRate();
        initStartDate();
        initEndDate();
    }

    private void initStartDate(){
        initEditText(R.id.startDateEditText, CalendarUtil.getCurrentDate());

        final EditText editText = (EditText) activity.findViewById(R.id.startDateEditText);

        editText.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN :
                        if(view == editText)
                        {
                            MainActivity mainActivity = (MainActivity) activity;
                            mainActivity.startDateOnClick(view);
                        }
                        break;
                    case MotionEvent.ACTION_UP  :
                        break;

                }
                return false;
            }
        });


    }

    private void initEndDate(){
        initEditText(R.id.endDateEditText, CalendarUtil.getCurrentDate());

        final EditText editText = (EditText) activity.findViewById(R.id.endDateEditText);

        editText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN :
                        if(view == editText)
                        {
                            MainActivity mainActivity = (MainActivity) activity;
                            mainActivity.endDateOnClick(view);
                        }
                        break;
                    case MotionEvent.ACTION_UP  :
                        break;

                }
                return false;
            }
        });
    }

    public void initEditText(int id, String value){
        EditText editText = (EditText) activity.findViewById(id);
        editText.setText(value);
    }

    private void initInterestRate(){
        initEditText(R.id.interestRateEditText, Constant.interestRate);
    }

    private void initAmount(){
        initEditText(R.id.amountEditText, Constant.amount);
    }
}
