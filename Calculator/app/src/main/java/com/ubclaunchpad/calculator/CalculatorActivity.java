package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = CalculatorActivity.class.getSimpleName();

    ToggleButton toggleAdd;
    ToggleButton toggleSubtract;
    ToggleButton toggleMultiply;
    ToggleButton toggleDivide;

    Button buttonEquals;

    EditText firstInput;
    EditText secondInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        toggleAdd = (ToggleButton) findViewById(R.id.operation_add);
        toggleSubtract = (ToggleButton) findViewById(R.id.operation_sub);
        toggleMultiply = (ToggleButton) findViewById(R.id.operation_multi);
        toggleDivide = (ToggleButton) findViewById(R.id.operation_div);
        buttonEquals = (Button) findViewById(R.id.operation_equals);

        firstInput = (EditText) findViewById(R.id.firstInput);
        secondInput = (EditText) findViewById(R.id.secondInput);

        toggleAdd.setOnClickListener(this);
        toggleSubtract.setOnClickListener(this);
        toggleMultiply.setOnClickListener(this);
        toggleDivide.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
    }

    /**
     * This implementation of the click listener is for the view found in res/layout/activity_calculator
     * The functions in general should grab the appropriate inputs, and if they are valid, computes the answer.
     * The answer should be displayed in a second activity labelled "AnswerActivity"
     * @param v
     */

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            // when the user selects a toggle switch (operation)
            case R.id.operation_add:
            {
                toggleOnClick(toggleAdd);
                break;
            }
            case R.id.operation_sub:
            {
                toggleOnClick(toggleSubtract);
                break;
            }
            case R.id.operation_multi:
            {
                toggleOnClick(toggleMultiply);
                break;
            }
            case R.id.operation_div:
            {
                toggleOnClick(toggleDivide);
                break;
            }

            // when the user taps the equals button
            case R.id.operation_equals:
            {

                String firstInputString = firstInput.getText().toString().trim();
                String secondInputString = secondInput.getText().toString().trim();

                // if user did not input a value
                if (firstInputString.length() == 0) {
                    Toast.makeText(this, "Please give a value for the first input", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (secondInputString.length() == 0) {
                    Toast.makeText(this, "Please give a value for the second input", Toast.LENGTH_SHORT).show();
                    break;
                }

                // if user only inputted a decimal point
                if (firstInputString.length() == 1 && firstInputString.equals(".")){
                    Toast.makeText(this, "Please input a valid number for the first input", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (secondInputString.length() == 1 && secondInputString.equals(".")){
                    Toast.makeText(this, "Please input a valid number for the second input", Toast.LENGTH_SHORT).show();
                    break;
                }

                double input1 = Double.parseDouble(firstInput.getText().toString());
                double input2 = Double.parseDouble(secondInput.getText().toString());

                double result;

                // creating the intent to be passed to ResultActivity
                Intent intent = new Intent(this, ResultActivity.class);

                intent.putExtra("input1", input1);
                intent.putExtra("input2", input2);

                // first check to see which toggle (operation) has been selected
                if (toggleAdd.isChecked()) {
                    result = input1 + input2;
                    intent.putExtra("operation", " + ");
                } else if (toggleSubtract.isChecked()){
                    result = input1 - input2;
                    intent.putExtra("operation", " - ");
                } else if (toggleMultiply.isChecked()){
                    result = input1 * input2;
                    intent.putExtra("operation", " x ");
                } else if (toggleDivide.isChecked()){
                    result = input1 / input2;
                    intent.putExtra("operation", " / ");
                } else {
                    // if the user has not selected an operation
                    Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show();
                    break;
                }

                intent.putExtra("result", result);
                startActivity(intent);

                break;
            }
            //TODO any extra implmentations (optional)
            default:
            {
                Toast.makeText(this, "Click not implmented yet", Toast.LENGTH_LONG).show();
                Log.e(TAG, "View id: " + v.getId() + " click not implemented yet");
                break;
            }
        }
    }

    // this is called when a toggle switch is selected
    public void toggleOnClick(ToggleButton toggleBut){
        clearToggles();
        toggleBut.setChecked(true);
    }

    // this unchecks all toggle switches
    public void clearToggles() {
        toggleAdd.setChecked(false);
        toggleSubtract.setChecked(false);
        toggleMultiply.setChecked(false);
        toggleDivide.setChecked(false);
    }
}
