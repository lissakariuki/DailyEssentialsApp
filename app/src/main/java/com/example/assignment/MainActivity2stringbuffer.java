package com.example.assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2stringbuffer extends AppCompatActivity {
    Button button5;
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2stringbuffer);

        button5 = findViewById(R.id.button5);


        textView = findViewById(R.id.textView7);

        Intent intent = getIntent();
        String dataSummary = intent.getStringExtra("data_summary");
        if (dataSummary != null) {
            textView.setText(dataSummary);
        }

        button5.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity2stringbuffer.this, MainActivity.class);
            startActivity(intent1);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
