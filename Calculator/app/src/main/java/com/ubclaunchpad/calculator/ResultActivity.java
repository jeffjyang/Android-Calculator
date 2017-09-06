package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = (TextView) findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        double input1 = intent.getDoubleExtra("input1", 0);
        double input2 = intent.getDoubleExtra("input2", 0);

        String operation = intent.getStringExtra("operation");

        double result = intent.getDoubleExtra("result", 0);

        resultTextView.setText(input1 + operation + input2 + "\n = " + result);




    }
}
