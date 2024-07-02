package com.example.assignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2recyclerview extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2recyclerview);

        recyclerView = findViewById(R.id.recyclerview);
        button5 = findViewById(R.id.button5);


        Intent intent = getIntent();
        ArrayList<String> dataSummary = intent.getStringArrayListExtra("data_summary");

        if (dataSummary != null) {
            RecyclerView.Adapter<SummaryAdapter.ViewHolder> adapter = new SummaryAdapter(dataSummary);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            // Handle case when dataSummary is null to avoid crashes
            // You can show a message or set an empty adapter
        }

        button5.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity2recyclerview.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}
