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

public class TempActivity extends AppCompatActivity {

    private Spinner spinnerFromTemp, spinnerToTemp;
    private EditText tempInput;
    private Button convertButton;
    private TextView resultTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        spinnerFromTemp = findViewById(R.id.spinnerFromTemp);
        spinnerToTemp = findViewById(R.id.spinnerToTemp);
        tempInput = findViewById(R.id.tempInput);
        convertButton = findViewById(R.id.convertButton);
        resultTemp = findViewById(R.id.resultTemp);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.temp_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromTemp.setAdapter(adapter);
        spinnerToTemp.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String fromTempUnit = spinnerFromTemp.getSelectedItem().toString();
        String toTempUnit = spinnerToTemp.getSelectedItem().toString();
        String inputTempString = tempInput.getText().toString().trim();

        if (!inputTempString.isEmpty()) {
            double inputTemp = Double.parseDouble(inputTempString);

            double convertedTemp;
            if (fromTempUnit.equals("Celsius") && toTempUnit.equals("Fahrenheit")) {
                convertedTemp = celsiusToFahrenheit(inputTemp);
            } else if (fromTempUnit.equals("Celsius") && toTempUnit.equals("Kelvin")) {
                convertedTemp = celsiusToKelvin(inputTemp);
            } else if (fromTempUnit.equals("Fahrenheit") && toTempUnit.equals("Celsius")) {
                convertedTemp = fahrenheitToCelsius(inputTemp);
            } else if (fromTempUnit.equals("Fahrenheit") && toTempUnit.equals("Kelvin")) {
                convertedTemp = fahrenheitToKelvin(inputTemp);
            } else if (fromTempUnit.equals("Kelvin") && toTempUnit.equals("Celsius")) {
                convertedTemp = kelvinToCelsius(inputTemp);
            } else if (fromTempUnit.equals("Kelvin") && toTempUnit.equals("Fahrenheit")) {
                convertedTemp = kelvinToFahrenheit(inputTemp);
            } else {
                resultTemp.setText("Invalid temperature conversion.");
                return;
            }

            String result = String.format("%.2f %s = %.2f %s", inputTemp, fromTempUnit, convertedTemp, toTempUnit);
            resultTemp.setText(result);
        } else {
            resultTemp.setText("Please enter a temperature value.");
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit + 459.67) * 5 / 9;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin * 9 / 5) - 459.67;
    }
}
