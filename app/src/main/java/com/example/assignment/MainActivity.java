package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_QUANTITY = 4;
    private EditText breadQuantityEditText, milkQuantityEditText, sugarQuantityEditText;
    private EditText breadTotalEditText, milkTotalEditText, sugarTotalEditText;
    private EditText grandtotalEditText, discountEditText, netPayEditText;
    private Button calculateButton, listViewButton, recyclerViewButton, stringBufferButton;
    private StringBuilder summaryBuffer = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateTotals();
            }
        });


    }

    private void initializeViews() {
        breadQuantityEditText = findViewById(R.id.editTextText);
        milkQuantityEditText = findViewById(R.id.editTextText5);
        sugarQuantityEditText = findViewById(R.id.editTextText7);
        breadTotalEditText = findViewById(R.id.editTextText2);
        milkTotalEditText = findViewById(R.id.editTextText6);
        sugarTotalEditText = findViewById(R.id.editTextText8);
        calculateButton = findViewById(R.id.button3);
        listViewButton = findViewById(R.id.button);
        recyclerViewButton = findViewById(R.id.button9);
        stringBufferButton = findViewById(R.id.button10);
        grandtotalEditText = findViewById(R.id.editTextText9);
        discountEditText = findViewById(R.id.editTextText10);
        netPayEditText = findViewById(R.id.editTextText11);
    }

    private void calculateTotals() {
        int breadQuantity = getQuantity(breadQuantityEditText);
        double breadTotal = calculateItemTotal(breadQuantity, 65.5); // Example price per unit
        breadTotalEditText.setText(String.format("%.2f", breadTotal));

        int milkQuantity = getQuantity(milkQuantityEditText);
        double milkTotal = calculateItemTotal(milkQuantity, 55.5); // Example price per unit
        milkTotalEditText.setText(String.format("%.2f", milkTotal));

        int sugarQuantity = getQuantity(sugarQuantityEditText);
        double sugarTotal = calculateItemTotal(sugarQuantity, 100.8); // Example price per unit
        sugarTotalEditText.setText(String.format("%.2f", sugarTotal));

        double grandTotal = breadTotal + milkTotal + sugarTotal;
        grandtotalEditText.setText(String.format("%.2f", grandTotal));

        // Apply discount
        double discountPercentage = getDiscountPercentage(grandTotal);
        double discountAmount = (grandTotal * discountPercentage) / 100;
        double netPay = grandTotal - discountAmount;

        discountEditText.setText(String.format("%.2f", discountAmount));
        netPayEditText.setText(String.format("%.2f", netPay));

        updateSummaryBuffer(breadQuantity, breadTotal, milkQuantity, milkTotal, sugarQuantity, sugarTotal, grandTotal, discountAmount, netPay);
    }

    private void updateSummaryBuffer(int breadQuantity, double breadTotal, int milkQuantity, double milkTotal, int sugarQuantity, double sugarTotal, double grandTotal, double discountAmount, double netPay) {
        ArrayList<String> dataSummary = new ArrayList<>();
        dataSummary.add("Bread: " + breadQuantity + " units - Total: $" + breadTotal);
        dataSummary.add("Milk: " + milkQuantity + " units - Total: $" + milkTotal);
        dataSummary.add("Sugar: " + sugarQuantity + " units - Total: $" + sugarTotal);
        dataSummary.add("Grand Total: $" + grandTotal);
        dataSummary.add("Discount: $" + discountAmount);
        dataSummary.add("Net Pay: $" + netPay);

        summaryBuffer.setLength(0); // Clear previous data
        for (String line : dataSummary) {
            summaryBuffer.append(line).append("\n");
        }

        // Set OnClickListeners for buttons with updated data
        listViewButton.setOnClickListener(v -> navigateToListView(dataSummary));
        recyclerViewButton.setOnClickListener(v -> navigateToRecyclerView(dataSummary));
        stringBufferButton.setOnClickListener(v -> navigateToStringBuffer(summaryBuffer.toString()));
    }

    private int getQuantity(EditText editText) {
        String quantityText = editText.getText().toString();
        if (quantityText.isEmpty()) {
            return 0;
        } else {
            int quantity = Integer.parseInt(quantityText);
            return Math.min(quantity, MAX_QUANTITY); // Limit quantity to MAX_QUANTITY
        }
    }

    private double calculateItemTotal(int quantity, double pricePerUnit) {
        return quantity * pricePerUnit;
    }

    private int getDiscountPercentage(double grandTotal) {
        if (grandTotal <= 10000) {
            return 0;
        } else if (grandTotal <= 25000) {
            return 15;
        } else if (grandTotal <= 35000) {
            return 25;
        } else {
            return 30;
        }
    }

    private void navigateToListView(ArrayList<String> dataSummary) {
        Intent intent1 = new Intent(MainActivity.this, MainActivity2listview.class);
        intent1.putStringArrayListExtra("data_summary", dataSummary);
        startActivity(intent1);
    }

    private void navigateToRecyclerView(ArrayList<String> dataSummary) {
        Intent intent2 = new Intent(MainActivity.this, MainActivity2recyclerview.class);
        intent2.putStringArrayListExtra("data_summary", dataSummary);
        startActivity(intent2);
    }

    private void navigateToStringBuffer(String dataSummary) {
        Intent intent3 = new Intent(MainActivity.this, MainActivity2stringbuffer.class);
        intent3.putExtra("data_summary", dataSummary);
        startActivity(intent3);
    }
}
