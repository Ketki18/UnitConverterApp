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

public class WeightActivity extends AppCompatActivity {

    private static final double[] WEIGHT_CONVERSION_FACTORS = {1.0, 1000.0, 2.20462, 35.27396}; // Conversion factors for: kg, g, lb, oz

    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText weightInput;
    private Button convertButton;
    private TextView resultWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        fromUnitSpinner = findViewById(R.id.spinnerFromUnit);
        toUnitSpinner = findViewById(R.id.spinnerToUnit);
        weightInput = findViewById(R.id.weightInput);
        convertButton = findViewById(R.id.convertButton);
        resultWeight = findViewById(R.id.resultWeight);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.weight_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    private void convertWeight() {
        String weightString = weightInput.getText().toString().trim();
        if (!weightString.isEmpty()) {
            double weightValue = Double.parseDouble(weightString);
            int fromUnitPosition = fromUnitSpinner.getSelectedItemPosition();
            int toUnitPosition = toUnitSpinner.getSelectedItemPosition();

            double convertedWeight = convert(weightValue, fromUnitPosition, toUnitPosition);

            String fromUnit = fromUnitSpinner.getSelectedItem().toString();
            String toUnit = toUnitSpinner.getSelectedItem().toString();
            String result = String.format("%.2f %s = %.2f %s", weightValue, fromUnit, convertedWeight, toUnit);
            resultWeight.setText(result);
        } else {
            resultWeight.setText("Please enter a weight value.");
        }
    }

    private double convert(double value, int fromUnitPosition, int toUnitPosition) {
        if (fromUnitPosition == toUnitPosition) {
            return value;
        } else if (fromUnitPosition == 0 && toUnitPosition == 1) { // kg to g
            return value * 1000.0;
        } else if (fromUnitPosition == 0 && toUnitPosition == 2) { // kg to lb
            return value * 2.20462;
        } else if (fromUnitPosition == 0 && toUnitPosition == 3) { // kg to oz
            return value * 35.27396;
        } else if (fromUnitPosition == 1 && toUnitPosition == 0) { // g to kg
            return value / 1000.0;
        } else if (fromUnitPosition == 1 && toUnitPosition == 2) { // g to lb
            return value * 0.00220462;
        } else if (fromUnitPosition == 1 && toUnitPosition == 3) { // g to oz
            return value * 0.03527396;
        } else if (fromUnitPosition == 2 && toUnitPosition == 0) { // lb to kg
            return value * 0.453592;
        } else if (fromUnitPosition == 2 && toUnitPosition == 1) { // lb to g
            return value * 453.592;
        } else if (fromUnitPosition == 2 && toUnitPosition == 3) { // lb to oz
            return value * 16.0;
        } else if (fromUnitPosition == 3 && toUnitPosition == 0) { // oz to kg
            return value * 0.0283495;
        } else if (fromUnitPosition == 3 && toUnitPosition == 1) { // oz to g
            return value * 28.3495;
        } else if (fromUnitPosition == 3 && toUnitPosition == 2) { // oz to lb
            return value * 0.0625;
        }
        return value;
    }
}
