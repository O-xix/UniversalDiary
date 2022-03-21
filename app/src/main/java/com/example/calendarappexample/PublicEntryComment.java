package com.example.calendarappexample;

import static com.example.calendarappexample.Entry.entriesList;
import static com.example.calendarappexample.MainActivity.SQLiteDB;
import static com.example.calendarappexample.WeekViewActivity.pubdate;
import static com.example.calendarappexample.WeekViewActivity.pubname;
import static com.example.calendarappexample.WeekViewActivity.pubtext;
import static com.example.calendarappexample.WeekViewActivity.pubtime;
import static com.example.calendarappexample.WeekViewActivity.selectedEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
        pubname = selectedEntry.getTitle();
        pubtext = selectedEntry.getText();
        pubdate = CalendarUtils.formattedDate(selectedEntry.getDate());
        pubtime = CalendarUtils.formattedTime(selectedEntry.getTime());
        entryTextTV.setText(pubtext);
        entryNameTV.setText(pubname);
        entryDateTV.setText(pubdate);
        entryTimeTV.setText(pubtime);
        entryTextTV.setMovementMethod(new ScrollingMovementMethod());
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
        for(int i = 0; i < entriesList.size(); i++) {
            if(entriesList.get(i) == selectedEntry) {
                entriesList.get(i).setComments(selectedEntry.getComments());
                entriesList.get(i).setNumComments(selectedEntry.getNumComments());
            }
        }
        selectedEntry.setNumComments(selectedEntry.getNumComments() + 1);
        SQLiteDB.updateentry(selectedEntry.getTitle(), selectedEntry.getText(), selectedEntry.getComments(), selectedEntry.getNumComments(), CalendarUtils.formattedDate(selectedEntry.getDate()), CalendarUtils.formattedTime(selectedEntry.getTime()));
        finish();
    }
}