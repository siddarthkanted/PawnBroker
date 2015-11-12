package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.app.Activity;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by sikanted on 11/12/2015.
 */
public class InterestCalculationValidation {

    private String amount;
    private String interestRate;
    private String startDate;
    private String endDate;
    private Activity activity;

    private List<String> resultList;

    public InterestCalculationValidation(String amount, String interestRate, String startDate, String endDate, Activity mainActivity) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activity = mainActivity;
        resultList = new ArrayList<>();
    }


    public List<String> validation(){

        String amountValidation = numberValidation(amount);
        if(amountValidation != null){
            resultList.add(String.format(amountValidation, activity.getString(R.string.amount)));
        }

        String interestRateValidation = numberValidation(interestRate);
        if(interestRateValidation != null){
            resultList.add(String.format(interestRateValidation, activity.getString(R.string.interestRate)));
        }

        String startDateValidation = dateValidation(startDate);
        if(startDateValidation != null){
            resultList.add(String.format(startDateValidation, activity.getString(R.string.startDate)));
        }

        String endDateValidation = dateValidation(endDate);
        if(endDateValidation != null){
            resultList.add(String.format(endDateValidation, activity.getString(R.string.endDate)));
        }

        String toFromDateValidation = toFromDateValidation(startDate, endDate);
        if(toFromDateValidation != null){
            resultList.add(String.format(toFromDateValidation, activity.getString(R.string.startDate), activity.getString(R.string.endDate)));
        }

        return  resultList;
    }

    private String toFromDateValidation(String toDateInString, String fromDateInString){
        Calendar toCalendar =  CalendarUtil.convertStringToCalendar(toDateInString);
        Calendar fromCalendar =  CalendarUtil.convertStringToCalendar(fromDateInString);

        if(toCalendar.compareTo(fromCalendar) > 0) {
            return "%1$s must be less than %2$s";
        }

        return null;
    }

    private String dateValidation(String dateInString){
        if(null == dateInString || TextUtils.isEmpty(dateInString)){
            return "%1$s can't be null or empty";
        }

        Calendar calendar =  CalendarUtil.convertStringToCalendar(dateInString);

        if(calendar == null){
            return "%1$s must be date. Format is " + Constant.dateFormat;
        }

        return null;
    }

    private String numberValidation(String number){
        try {

            if(null == number || TextUtils.isEmpty(number)){
                return "%1$s can't be null or empty";
            }

            double numberDouble = Double.parseDouble(number);

            if(numberDouble < 0){
                return "%1$s can't be less than zero";
            }

            return null;

        } catch (NumberFormatException e) {
            return "%1$s must be a number";
        }
    }
}
