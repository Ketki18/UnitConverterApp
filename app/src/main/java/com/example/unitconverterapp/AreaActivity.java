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

public class AreaActivity extends AppCompatActivity {

    private static final double[] AREA_CONVERSION_FACTORS = {
            1.0,                // Square meters (m²)
            10.7639,            // Square feet (ft²)
            1.0E-6,             // Square kilometers (km²)
            1.1959900463,       // Square yards (yd²)
            1550.0031000062,    // Square inches (in²)
            3.8610215854245E-7, // Acres (ac)
    };

    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText areaInput;
    private Button convertButton;
    private TextView resultArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        fromUnitSpinner = findViewById(R.id.spinnerFromUnit);
        toUnitSpinner = findViewById(R.id.spinnerToUnit);
        areaInput = findViewById(R.id.areaInput);
        convertButton = findViewById(R.id.convertButton);
        resultArea = findViewById(R.id.resultArea);

        // Populate the "from" and "to" unit spinners with area unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.area_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertArea();
            }
        });
    }

    private void convertArea() {
        String areaString = areaInput.getText().toString().trim();
        if (!areaString.isEmpty()) {
            double areaValue = Double.parseDouble(areaString);
            int fromUnitPosition = fromUnitSpinner.getSelectedItemPosition();
            int toUnitPosition = toUnitSpinner.getSelectedItemPosition();

            double convertedArea = convert(areaValue, fromUnitPosition, toUnitPosition);

            String fromUnit = fromUnitSpinner.getSelectedItem().toString();
            String toUnit = toUnitSpinner.getSelectedItem().toString();

            String result;
            if (toUnitPosition == 2 && Math.abs(convertedArea) < 1.0) {
                result = String.format("%.2f %s = %.2E %s", areaValue, fromUnit, convertedArea, toUnit);
            } else {
                result = String.format("%.2f %s = %.2f %s", areaValue, fromUnit, convertedArea, toUnit);
            }

            resultArea.setText(result);
        } else {
            resultArea.setText("Please enter an area value.");
        }
    }

    private double convert(double value, int fromUnitPosition, int toUnitPosition) {
        if (fromUnitPosition == toUnitPosition) {
            return value;
        } else if (fromUnitPosition == 0 && toUnitPosition == 1) { // m² to ft²
            return value * AREA_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 0 && toUnitPosition == 2) { // m² to km²
            return value * AREA_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 0 && toUnitPosition == 3) { // m² to yd²
            return value * AREA_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 0 && toUnitPosition == 4) { // m² to in²
            return value * AREA_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 0 && toUnitPosition == 5) { // m² to ac
            return value * AREA_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 1 && toUnitPosition == 0) { // ft² to m²
            return value / AREA_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 1 && toUnitPosition == 2) { // ft² to km²
            return value * AREA_CONVERSION_FACTORS[2] / AREA_CONVERSION_FACTORS[1];
        } else if (fromUnitPosition == 1 && toUnitPosition == 3) { // ft² to yd²
            return value / AREA_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 1 && toUnitPosition == 4) { // ft² to in²
            return value * 144.0;
        } else if (fromUnitPosition == 1 && toUnitPosition == 5) { // ft² to ac
            return value / 43560.0;
        } else if (fromUnitPosition == 2 && toUnitPosition == 0) { // km² to m²
            return value / AREA_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 2 && toUnitPosition == 1) { // km² to ft²
            return value * AREA_CONVERSION_FACTORS[1] / AREA_CONVERSION_FACTORS[2];
        } else if (fromUnitPosition == 2 && toUnitPosition == 3) { // km² to yd²
            return value * 1.196e+6;
        } else if (fromUnitPosition == 2 && toUnitPosition == 4) { // km² to in²
            return value * 1.55e+9;
        } else if (fromUnitPosition == 2 && toUnitPosition == 5) { // km² to ac
            return value * 247.105;
        } else if (fromUnitPosition == 3 && toUnitPosition == 0) { // yd² to m²
            return value / AREA_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 1) { // yd² to ft²
            return value * AREA_CONVERSION_FACTORS[1] / AREA_CONVERSION_FACTORS[3];
        } else if (fromUnitPosition == 3 && toUnitPosition == 2) { // yd² to km²
            return value / 1.196e+6;
        } else if (fromUnitPosition == 3 && toUnitPosition == 4) { // yd² to in²
            return value * 1296.0;
        } else if (fromUnitPosition == 3 && toUnitPosition == 5) { // yd² to ac
            return value / 4840.0;
        } else if (fromUnitPosition == 4 && toUnitPosition == 0) { // in² to m²
            return value / AREA_CONVERSION_FACTORS[4];
        } else if (fromUnitPosition == 4 && toUnitPosition == 1) { // in² to ft²
            return value / 144.0;
        } else if (fromUnitPosition == 4 && toUnitPosition == 2) { // in² to km²
            return value / 1.55e+9;
        } else if (fromUnitPosition == 4 && toUnitPosition == 3) { // in² to yd²
            return value / 1296.0;
        } else if (fromUnitPosition == 4 && toUnitPosition == 5) { // in² to ac
            return value / 6.273e+6;
        } else if (fromUnitPosition == 5 && toUnitPosition == 0) { // ac to m²
            return value / AREA_CONVERSION_FACTORS[5];
        } else if (fromUnitPosition == 5 && toUnitPosition == 1) { // ac to ft²
            return value * 43560.0;
        } else if (fromUnitPosition == 5 && toUnitPosition == 2) { // ac to km²
            return value / 247.105;
        } else if (fromUnitPosition == 5 && toUnitPosition == 3) { // ac to yd²
            return value * 4840.0;
        } else if (fromUnitPosition == 5 && toUnitPosition == 4) { // ac to in²
            return value * 6.273e+6;
        }
        return value;
    }
}
