package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    private Calendar endDateCalendar = Calendar.getInstance();
    private Calendar startDateCalendar = Calendar.getInstance();
    private DatePickerCustom datePickerCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action bar


        //init
        MainActivityInit mainActivityInit = new MainActivityInit(this);
        mainActivityInit.init();

        datePickerCustom =new DatePickerCustom(this);
    }

    public void endDateOnClick(View view){
        datePickerCustom.show(R.id.endDateEditText, endDateCalendar);
    }

    public void startDateOnClick(View view){
        datePickerCustom.show(R.id.startDateEditText, startDateCalendar);
    }

    private String getStringFromEditText(int id){
        EditText editText = (EditText)this.findViewById(id);
        return editText.getText().toString();
    }

    private String convertListToString(List<String> list){
        if(list.size() == 0){
            return null;
        }else{
            StringBuilder resultStringBuilder = new StringBuilder();
            for(String result : list){
                resultStringBuilder.append(result);
                resultStringBuilder.append("\n\n");
            }
            return resultStringBuilder.toString();
        }
    }

    public void submitButtonOnClick(View view){
        String amount = getStringFromEditText(R.id.amountEditText);
        String interestRate = getStringFromEditText(R.id.interestRateEditText);
        String startDate = getStringFromEditText(R.id.startDateEditText);
        String endDate = getStringFromEditText(R.id.endDateEditText);

        InterestCalculation interestCalculation = new InterestCalculation(this, amount, interestRate, startDate, endDate);
        String result = convertListToString(interestCalculation.calculate());

        TextView resultTextView = (TextView)this.findViewById(R.id.resultTextView);
        resultTextView.setText(result);

    }
}
