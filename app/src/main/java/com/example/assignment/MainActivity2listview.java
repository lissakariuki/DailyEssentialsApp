package com.example.assignment;

import static com.example.assignment.R.id.button11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2listview extends AppCompatActivity {
    ListView listView;
    Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2listview);
        listView = findViewById(R.id.ListView);

        Intent intent = getIntent();
        ArrayList<String> dataSummary = (ArrayList<String>) intent.getSerializableExtra("data_summary");

        if (dataSummary != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSummary);
            listView.setAdapter(adapter);
        }

        button11 = findViewById(R.id.button11);


        button11.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity2listview.this, MainActivity.class);
            startActivity(intent1);
        });


    }
}
