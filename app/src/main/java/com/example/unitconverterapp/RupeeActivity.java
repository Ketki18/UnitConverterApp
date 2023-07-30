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

import java.text.DecimalFormat;

public class RupeeActivity extends AppCompatActivity {

    private static final double[] CURRENCY_CONVERSION_RATES = {
            1.0,            // INR to INR
            0.014,          // INR to USD
            0.011,          // INR to GBP
            0.012,          // INR to EUR
    };

    private Spinner spinnerFromCurrency;
    private Spinner spinnerToCurrency;
    private EditText currencyInput;
    private Button convertButton;
    private TextView resultCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rupee);

        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency);
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency);
        currencyInput = findViewById(R.id.currencyInput);
        convertButton = findViewById(R.id.convertButton);
        resultCurrency = findViewById(R.id.resultCurrency);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String inputText = currencyInput.getText().toString().trim();
        if (!inputText.isEmpty()) {
            double inputValue = Double.parseDouble(inputText);
            int fromCurrencyPosition = spinnerFromCurrency.getSelectedItemPosition();
            int toCurrencyPosition = spinnerToCurrency.getSelectedItemPosition();

            double convertedValue = inputValue * CURRENCY_CONVERSION_RATES[toCurrencyPosition]
                    / CURRENCY_CONVERSION_RATES[fromCurrencyPosition];

            String fromCurrency = spinnerFromCurrency.getSelectedItem().toString();
            String toCurrency = spinnerToCurrency.getSelectedItem().toString();

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String resultText = decimalFormat.format(inputValue) + " " + fromCurrency + " = "
                    + decimalFormat.format(convertedValue) + " " + toCurrency;
            resultCurrency.setText(resultText);
        } else {
            resultCurrency.setText("Please enter a value to convert.");
        }
    }
}
