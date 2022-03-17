package com.example.calendarappexample;

import static com.example.calendarappexample.WeekViewActivity.pubdate;
import static com.example.calendarappexample.WeekViewActivity.pubname;
import static com.example.calendarappexample.WeekViewActivity.pubtext;
import static com.example.calendarappexample.WeekViewActivity.pubtime;
import static com.example.calendarappexample.WeekViewActivity.selectedEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PublicEntryComment extends AppCompatActivity {

    private TextView entryDateTV, entryTimeTV, entryNameTV, entryTextTV;
    private EditText entryCommentET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_entry_comment);
        initWidgets();
        pubname = selectedEntry.getName();
        pubtext = selectedEntry.getText();
        pubdate = CalendarUtils.formattedDate(selectedEntry.getDate());
        pubtime = CalendarUtils.formattedTime(selectedEntry.getTime());
        entryTextTV.setText(pubtext);
        entryNameTV.setText(pubname);
        entryDateTV.setText(pubdate);
        entryTimeTV.setText(pubtime);
    }

    private void initWidgets() {
        entryTextTV = findViewById(R.id.entryTextTV);
        entryNameTV = findViewById(R.id.entryNameTV);
        entryDateTV = findViewById(R.id.entryDateTV);
        entryTimeTV = findViewById(R.id.entryTimeTV);
        entryCommentET = findViewById(R.id.entryCommentET);
    }

    public void saveCommentAction(View view) {
        String entryComment = entryCommentET.getText().toString();
        selectedEntry.setComments(selectedEntry.getComments() + "\n - " + entryComment);
        selectedEntry.setNumComments(selectedEntry.getNumComments() + 1);
        finish();
    }
}