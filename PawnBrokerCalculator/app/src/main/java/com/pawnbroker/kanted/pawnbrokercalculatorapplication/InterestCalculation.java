package com.pawnbroker.kanted.pawnbrokercalculatorapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sikanted on 11/12/2015.
 */
public class InterestCalculation {

    private String amount;
    private String interestRate;
    private String startDate;
    private String endDate;

    private List<String> resultList;

    private Activity activity;

    public InterestCalculation(Activity mainActivity, String amount, String interestRate, String startDate, String endDate) {
        this.activity = mainActivity;
        this.amount = amount;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.endDate = endDate;

        resultList = new ArrayList<>();
    }

    public List<String> calculate(){

        //validation
        InterestCalculationValidation interestCalculationValidation = new InterestCalculationValidation(amount, interestRate,
                startDate, endDate, activity);
        resultList = interestCalculationValidation.validation();
        if(resultList.size() != 0){
            return resultList;
        }

        //difference
        long daysDifference = CalendarUtil.daysBetween(startDate, endDate);
        String response = String.format(activity.getString(R.string.differenceBetween),
                activity.getString(R.string.startDate),
                activity.getString(R.string.endDate),
                daysDifference,
                activity.getString(R.string.days)
        );

        resultList.add(response);

        Double monthsDifference = daysDifference/30.41;
        response = String.format(activity.getString(R.string.differenceBetween),
                activity.getString(R.string.startDate),
                activity.getString(R.string.endDate),
                Math.round(monthsDifference * 100.0) / 100.0,
                activity.getString(R.string.months)
        );

        resultList.add(response);

        Set<Double> monthsDifferenceSet = new HashSet<>();
        monthsDifferenceSet.add(monthsDifference);
        monthsDifferenceSet.add(new Double(monthsDifference.intValue() - 1));
        monthsDifferenceSet.add(new Double(1));
        monthsDifferenceSet.add(new Double(monthsDifference.intValue()));
        monthsDifferenceSet.add(new Double(monthsDifference.intValue() + 1));

        List<Double> monthsDifferenceList = new ArrayList<>(monthsDifferenceSet);

        Collections.sort(monthsDifferenceList);

        for(Double monthsDifferenceInteger : monthsDifferenceList){
            calculateInterest(monthsDifferenceInteger);
        }

        return resultList;
    }

    private void calculateInterest(Double months){
        if (months >= 1) {
            double interest = Double.parseDouble(amount) * Double.parseDouble(interestRate) * months;
            interest = interest/100;

            double totalAmount =  Double.parseDouble(amount) + interest;

            String response = String.format(activity.getString(R.string.interestMonths),
                    Math.round(months * 100.0) / 100.0,
                    Math.round(interest * 100.0) / 100.0,
                    Math.round(totalAmount * 100.0) / 100.0
            );

            resultList.add(response);
        }
    }


}
