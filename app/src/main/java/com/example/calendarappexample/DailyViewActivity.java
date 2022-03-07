package com.example.calendarappexample;

import static com.example.calendarappexample.CalendarUtils.selectedDate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class DailyViewActivity extends AppCompatActivity {

    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private ListView hourListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_view);
        initWidgets();
    }

    private void initWidgets() {
        monthDayText = findViewById(R.id.monthDayText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
        hourListView = findViewById(R.id.hourListView);
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
        startActivity(new Intent(this, EntryEditActivity.class));
    }
}