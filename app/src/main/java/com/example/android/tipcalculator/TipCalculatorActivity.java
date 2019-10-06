package com.example.android.tipcalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }

    private void showToast (String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void computeTip (View view) {
        hideKeyboard(this);
        EditText checkAmountEditText = findViewById(R.id.checkAmountValue);
        EditText partySizeEditText = findViewById(R.id.partySizeValue);
        double checkAmount, partySize;
        try {
            checkAmount = Double.parseDouble(checkAmountEditText.getText().toString());
            partySize = Double.parseDouble(partySizeEditText.getText().toString());
        } catch (Exception e) {
            if (partySizeEditText.getText().toString().matches("") ||
                    checkAmountEditText.getText().toString().matches("")) {
                showToast("Check amount and party size fields can't be empty!");
                return;
            }
            System.out.println("Error Make Sure the values are legal");
            return;
        }
        if (partySize == 0 || checkAmount == 0) {
            showToast("Check amount and party size can't be 0!");
            return;
        }
        else if (checkAmount < 0 || partySize < 0) {
            showToast("Check amount and party size can't have negative values!");
            return;
        }
        System.out.println(checkAmount + " " + partySize);
        TextView tip15TV = findViewById(R.id.fifteenPercentTipValue);
        TextView tip20TV = findViewById(R.id.twentyPercentTipValue);
        TextView tip25TV = findViewById(R.id.twentyfivePercentTipValue);
        TextView total15TV = findViewById(R.id.fifteenPercentTotalValue);
        TextView total20TV = findViewById(R.id.twentyPercentTotalValue);
        TextView total25TV = findViewById(R.id.twentyfivePercentTotalValue);
        int tip15 = (int) Math.round((.15*checkAmount)/partySize);
        int tip20 = (int) Math.round((.2*checkAmount)/partySize);
        int tip25 = (int) Math.round((.25*checkAmount)/partySize);
        int perPersonTotal = (int) Math.round(checkAmount/partySize);
        tip15TV.setText(Integer.toString(tip15));
        tip20TV.setText(Integer.toString(tip20));
        tip25TV.setText(Integer.toString(tip25));
        total15TV.setText(Integer.toString(perPersonTotal+tip15));
        total20TV.setText(Integer.toString(perPersonTotal+tip20));
        total25TV.setText(Integer.toString(perPersonTotal+tip25));
    }
}
