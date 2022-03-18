package com.example.calendarappexample;

import static com.example.calendarappexample.CalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DailyViewActivity extends AppCompatActivity {

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;
    private TextView entryOne;
    private TextView entryTwo;
    private TextView entryMore;
    public static Entry selectedViewEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_view);
        initWidgets();
        Context dailyViewActivityContext = this;
        hourListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedViewEntry = (Entry) parent.getItemAtPosition(position);
                startActivity(new Intent(dailyViewActivityContext, PublicEntryComment.class));
            }
        });
    }

    private void initWidgets() {
        monthDayText = findViewById(R.id.monthDayText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
        entryOne = findViewById(R.id.entry1);
        entryTwo = findViewById(R.id.entry2);
        entryMore = findViewById(R.id.entryMore);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDayView();
    }

    private void setDayView() {
        monthDayText.setText(CalendarUtils.monthDayFromDate(selectedDate));
        String dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        dayOfWeekTV.setText(dayOfWeek);
        setHourAdapter();
    }

    private void setHourAdapter() {
        HourAdapter hourAdapter = new HourAdapter(getApplicationContext(), hourEventList());
        hourListView.setAdapter(hourAdapter);
    }

    private ArrayList<HourEntry> hourEventList() {
        ArrayList<HourEntry> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++) {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Entry> entries = Entry.entriesForDateAndTime(selectedDate, time);
            HourEntry hourEntry = new HourEntry(time, entries);
            list.add(hourEntry);
        }

        return list;
    }

    public void previousDayAction(View view) {
        selectedDate = selectedDate.minusDays(1);
        setDayView();
    }

    public void nextDayAction(View view) {
        selectedDate = selectedDate.plusDays(1);
        setDayView();
    }

    public void newEventAction(View view) {
        startActivity(new Intent(this, EntryCreateActivity.class));
    }
    /*
    public static String editEntryTitle;
    public static String editEntryText;
    public static LocalTime editEntryTime;
    public static LocalDate editEntryDate;
    public static int editEntryInArray;
    public void editEntryOneAction(View view) {
        ArrayList<HourEntry> list = hourEventList();
        ArrayList<Entry> entries = new ArrayList<Entry>();
        for(int i = 0; i < list.size(); i++) {
            HourEntry parseHourEntry = list.get(i);
            entries = parseHourEntry.entries;
            String entryOneTitle = (String) entryOne.getText();
            for(int j = 0; j < entries.size(); j++) {
                Entry parseEntry = entries.get(j);
                editEntryTitle = parseEntry.getName();
                editEntryText = parseEntry.getText();
                editEntryTime = parseEntry.getTime();
                editEntryDate = parseEntry.getDate();

                //want to implement LocalTime
                if (entryOneTitle == editEntryTitle) {
                    startActivity(new Intent(this, WeekViewActivity.class));
                }
            }
        }
    }

    public void editEntryTwoAction(View view) {

    }

    public void viewMoreEntryAction(View view) {
    }
     */
}