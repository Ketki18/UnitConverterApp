package com.example.unitconverterapp;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TimeActivity extends AppCompatActivity {

    private Spinner spinnerFromTime, spinnerToTime;
    private EditText timeInput;
    private Button convertButton;
    private TextView resultTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        spinnerFromTime = findViewById(R.id.spinnerFromTime);
        spinnerToTime = findViewById(R.id.spinnerToTime);
        timeInput = findViewById(R.id.timeInput);
        convertButton = findViewById(R.id.convertButton);
        resultTime = findViewById(R.id.resultTime);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.time_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromTime.setAdapter(adapter);
        spinnerToTime.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        String fromTimeUnit = spinnerFromTime.getSelectedItem().toString();
        String toTimeUnit = spinnerToTime.getSelectedItem().toString();
        String inputTimeString = timeInput.getText().toString().trim();

        if (!inputTimeString.isEmpty()) {
            double inputTime = Double.parseDouble(inputTimeString);

            double convertedTime;
            if (fromTimeUnit.equals("Minutes (min)") && toTimeUnit.equals("Seconds (sec)")) {
                convertedTime = minutesToSeconds(inputTime);
            } else if (fromTimeUnit.equals("Minutes (min)") && toTimeUnit.equals("Hours (hrs)")) {
                convertedTime = minutesToHours(inputTime);
            } else if (fromTimeUnit.equals("Seconds (sec)") && toTimeUnit.equals("Minutes (min)")) {
                convertedTime = secondsToMinutes(inputTime);
            } else if (fromTimeUnit.equals("Seconds (sec)") && toTimeUnit.equals("Hours (hrs)")) {
                convertedTime = secondsToHours(inputTime);
            } else if (fromTimeUnit.equals("Hours (hrs)") && toTimeUnit.equals("Seconds (sec)")) {
                convertedTime = hoursToSeconds(inputTime);
            } else if (fromTimeUnit.equals("Hours (hrs)") && toTimeUnit.equals("Minutes (min)")) {
                convertedTime = hoursToMinutes(inputTime);
            } else {
                resultTime.setText("Invalid time conversion.");
                return;
            }

            String result = String.format("%.0f %s = %.0f %s", inputTime, fromTimeUnit, convertedTime, toTimeUnit);
            resultTime.setText(result);
        } else {
            resultTime.setText("Please enter a time value.");
        }
    }

    private double minutesToSeconds(double minutes) {
        return minutes * 60;
    }

    private double minutesToHours(double minutes) {
        return minutes / 60;
    }

    private double secondsToMinutes(double seconds) {
        return seconds / 60;
    }

    private double secondsToHours(double seconds) {
        return seconds / 3600;
    }

    private double hoursToSeconds(double hours) {
        return hours * 3600;
    }

    private double hoursToMinutes(double hours) {
        return hours * 60;
    }
}
