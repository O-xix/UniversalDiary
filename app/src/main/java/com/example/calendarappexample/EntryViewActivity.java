package com.example.calendarappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EntryViewActivity extends AppCompatActivity {

    private TextView entryDateTV, entryNameTV, entryTextTV, entryTimeTV, entryCommentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_view);
        initWidgets();

    }

    private void initWidgets() {
        entryNameTV = findViewById(R.id.entryNameTV);
        entryDateTV = findViewById(R.id.entryDateTV);
        entryTimeTV = findViewById(R.id.entryTimeTV);
        entryTextTV = findViewById(R.id.entryTextTV);
        entryCommentTV = findViewById(R.id.entryCommentTV);
    }
}