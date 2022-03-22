package com.example.calendarappexample;

import static com.example.calendarappexample.CalendarUtils.parsedDate;
import static com.example.calendarappexample.CalendarUtils.parsedTime;
import static com.example.calendarappexample.Entry.entriesList;
import static com.example.calendarappexample.Entry.publicEntriesList;
//import static com.example.calendarappexample.MainActivity.PublishedSQLiteDB;
import static com.example.calendarappexample.MainActivity.SQLiteDB;
import static com.example.calendarappexample.MainActivity.grabEntriesFromDB;
import static com.example.calendarappexample.MainActivity.grabPublishedEntriesFromDB;
import static com.example.calendarappexample.WeekViewActivity.pubdate;
import static com.example.calendarappexample.WeekViewActivity.pubname;
import static com.example.calendarappexample.WeekViewActivity.pubtext;
import static com.example.calendarappexample.WeekViewActivity.pubtime;
import static com.example.calendarappexample.WeekViewActivity.selectedEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        entryNameTV = findViewById(R.id.entryTitleTV);
        entryDateTV = findViewById(R.id.entryDateTV);
        entryTimeTV = findViewById(R.id.entryTimeTV);
        entryCommentET = findViewById(R.id.entryCommentET);
    }

    public void saveCommentAction(View view) {
        String entryComment = entryCommentET.getText().toString();
        selectedEntry.setComments(selectedEntry.getComments() + "\n\n - " + entryComment);
        for(int i = 0; i < entriesList.size(); i++) {
            if(entriesList.get(i) == selectedEntry) {
                entriesList.get(i).setComments(selectedEntry.getComments());
                entriesList.get(i).setNumComments(selectedEntry.getNumComments());
            }
        }
        selectedEntry.setNumComments(selectedEntry.getNumComments() + 1);
        Cursor res = SQLiteDB.getdata();
        while(res.moveToNext()){
            Entry entry = new Entry(res.getString(0), res.getString(1), res.getString(2), res.getInt(3), parsedDate(res.getString(5)), parsedTime(res.getString(6)));
            if(selectedEntry.getTitle().equals(entry.getTitle()) && selectedEntry.getTime() == entry.getTime() && selectedEntry.getDate() == entry.getDate()) {
                SQLiteDB.updateentry(selectedEntry.getTitle(), selectedEntry.getText(), selectedEntry.getComments(), selectedEntry.getNumComments(), CalendarUtils.formattedDate(selectedEntry.getDate()), CalendarUtils.formattedTime(selectedEntry.getTime()));
            }
        }
        SQLiteDB.updateentry(selectedEntry.getTitle(), selectedEntry.getText(), selectedEntry.getComments(), selectedEntry.getNumComments(), CalendarUtils.formattedDate(selectedEntry.getDate()), CalendarUtils.formattedTime(selectedEntry.getTime()));
        entriesList.clear();
        grabEntriesFromDB();
        publicEntriesList.clear();
        grabPublishedEntriesFromDB();
        Toast.makeText(this, selectedEntry.getTitle() + " commented succesfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}