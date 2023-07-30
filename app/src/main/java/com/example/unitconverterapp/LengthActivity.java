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

public class LengthActivity extends AppCompatActivity {

    private static final double[] LENGTH_CONVERSION_FACTORS = {
            1.0,            // meters (m)
            3.28084,        // feet (ft)
            39.3701,        // inches (in)
            0.001,          // kilometers (km)
            1000.0,         // millimeters (mm)
            100.0,          // centimeters (cm)
    };

    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText lengthInput;
    private Button convertButton;
    private TextView resultLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        fromUnitSpinner = findViewById(R.id.spinnerFromUnit);
        toUnitSpinner = findViewById(R.id.spinnerToUnit);
        lengthInput = findViewById(R.id.lengthInput);
        convertButton = findViewById(R.id.convertButton);
        resultLength = findViewById(R.id.resultLength);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.length_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
            }
        });
    }

    private void convertLength() {
        String lengthString = lengthInput.getText().toString().trim();
        if (!lengthString.isEmpty()) {
            double lengthValue = Double.parseDouble(lengthString);
            int fromUnitPosition = fromUnitSpinner.getSelectedItemPosition();
            int toUnitPosition = toUnitSpinner.getSelectedItemPosition();

            double convertedLength = convert(lengthValue, fromUnitPosition, toUnitPosition);

            String fromUnit = fromUnitSpinner.getSelectedItem().toString();
            String toUnit = toUnitSpinner.getSelectedItem().toString();

            String result = String.format("%.2f %s = %.2f %s", lengthValue, fromUnit, convertedLength, toUnit);
            resultLength.setText(result);
        } else {
            resultLength.setText("Please enter a length value.");
        }
    }

    private double convert(double value, int fromUnitPosition, int toUnitPosition) {
        if (fromUnitPosition == toUnitPosition) {
            return value;
        } else if (fromUnitPosition == 0 && toUnitPosition == 1) { // m to ft
            return value * LENGTH_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 0 && toUnitPosition == 2) { // m to in
            return value * LENGTH_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 0 && toUnitPosition == 3) { // m to km
            return value * LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 0 && toUnitPosition == 4) { // m to mm
            return value * LENGTH_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 0 && toUnitPosition == 5) { // m to cm
            return value * LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 1 && toUnitPosition == 0) { // ft to m
            return value / LENGTH_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 1 && toUnitPosition == 2) { // ft to in
            return value * 12.0;
        } else if (fromUnitPosition == 1 && toUnitPosition == 3) { // ft to km
            return value * LENGTH_CONVERSION_FACTORS[3] / LENGTH_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 1 && toUnitPosition == 4) { // ft to mm
            return value * 304.8;
        } else if (fromUnitPosition == 1 && toUnitPosition == 5) { // ft to cm
            return value * 30.48;
        } else if (fromUnitPosition == 2 && toUnitPosition == 0) { // in to m
            return value / LENGTH_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 2 && toUnitPosition == 1) { // in to ft
            return value / 12.0;
        } else if (fromUnitPosition == 2 && toUnitPosition == 3) { // in to km
            return value * LENGTH_CONVERSION_FACTORS[3] / LENGTH_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 2 && toUnitPosition == 4) { // in to mm
            return value * 25.4;
        } else if (fromUnitPosition == 2 && toUnitPosition == 5) { // in to cm
            return value * 2.54;
        } else if (fromUnitPosition == 3 && toUnitPosition == 0) { // km to m
            return value / LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 1) { // km to ft
            return value * LENGTH_CONVERSION_FACTORS[1] / LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 2) { // km to in
            return value * LENGTH_CONVERSION_FACTORS[2] / LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 4) { // km to mm
            return value * LENGTH_CONVERSION_FACTORS[4] / LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 5) { // km to cm
            return value * LENGTH_CONVERSION_FACTORS[5] / LENGTH_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 4 && toUnitPosition == 0) { // mm to m
            return value / LENGTH_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 4 && toUnitPosition == 1) { // mm to ft
            return value * LENGTH_CONVERSION_FACTORS[1] / LENGTH_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 4 && toUnitPosition == 2) { // mm to in
            return value * LENGTH_CONVERSION_FACTORS[2] / LENGTH_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 4 && toUnitPosition == 3) { // mm to km
            return value * LENGTH_CONVERSION_FACTORS[3] / LENGTH_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 4 && toUnitPosition == 5) { // mm to cm
            return value * LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 0) { // cm to m
            return value / LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 1) { // cm to ft
            return value * LENGTH_CONVERSION_FACTORS[1] / LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 2) { // cm to in
            return value * LENGTH_CONVERSION_FACTORS[2] / LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 3) { // cm to km
            return value * LENGTH_CONVERSION_FACTORS[3] / LENGTH_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 4) { // cm to mm
            return value / LENGTH_CONVERSION_FACTORS[5];
        }
        return value;
    }
}
