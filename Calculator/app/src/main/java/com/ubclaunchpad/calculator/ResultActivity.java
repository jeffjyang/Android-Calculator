package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTextView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        resultTextView = (TextView) findViewById(R.id.resultTextView);

        Intent intent = getIntent();
        double input1 = intent.getDoubleExtra("input1", 0);
        double input2 = intent.getDoubleExtra("input2", 0);

        String operation = intent.getStringExtra("operation");

        double result = intent.getDoubleExtra("result", 0);

        resultTextView.setText(input1 + operation + input2 + "\n = " + result);

    }

    // Go back to the calculator when user taps back button
    @Override
    public void onClick(View v) {
        Intent backIntent = new Intent (this, CalculatorActivity.class);
        startActivity(backIntent);
    }
}
