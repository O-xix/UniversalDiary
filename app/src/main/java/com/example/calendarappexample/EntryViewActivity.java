package com.example.calendarappexample;

import static com.example.calendarappexample.HourEntryListActivity.selectedViewEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class EntryViewActivity extends AppCompatActivity {

    private TextView entryDateTV, entryNameTV, entryTextTV, entryTimeTV, entryCommentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_view);
        initWidgets();
        entryNameTV.setText(selectedViewEntry.getName());
        entryDateTV.setText(CalendarUtils.formattedDate(selectedViewEntry.getDate()));
        entryTimeTV.setText(CalendarUtils.formattedTime(selectedViewEntry.getTime()));
        entryTextTV.setText(selectedViewEntry.getText());
        entryCommentTV.setText(selectedViewEntry.getComments());
        entryTextTV.setMovementMethod(new ScrollingMovementMethod());
        entryCommentTV.setMovementMethod(new ScrollingMovementMethod());
    }

    private void initWidgets() {
        entryNameTV = findViewById(R.id.entryNameTV);
        entryDateTV = findViewById(R.id.entryDateTV);
        entryTimeTV = findViewById(R.id.entryTimeTV);
        entryTextTV = findViewById(R.id.entryTextTV);
        entryCommentTV = findViewById(R.id.entryCommentTV);
    }
}