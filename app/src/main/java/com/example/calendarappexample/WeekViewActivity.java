package com.example.calendarappexample;

import static com.example.calendarappexample.CalendarUtils.daysInWeekArray;
import static com.example.calendarappexample.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView publicEntryListView;
    public static String pubname;
    public static String pubtext;
    public static String pubdate;
    public static String pubtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
        setPublicEntryAdapter();
        Context weekViewActivityContext = this;
        publicEntryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry selectedItem = (Entry) parent.getItemAtPosition(position);
                pubname = selectedItem.getName();
                pubtext = selectedItem.getText();
                pubdate = CalendarUtils.formattedDate(selectedItem.getDate());
                pubtime = CalendarUtils.formattedTime(selectedItem.getTime());
                startActivity(new Intent(weekViewActivityContext, PublicEntryComment.class));
            }
        });
    }

    public void initWidgets(){
        //Find both views by id:
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
        publicEntryListView = findViewById(R.id.publicEntryListView);
    }

    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setPublicEntryAdapter();
    }

    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        //Must always run on true.
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPublicEntryAdapter();
    }

    private void setPublicEntryAdapter() {
        ArrayList<Entry> publishedDailyEntries = Entry.publicEntriesForDate(CalendarUtils.selectedDate);
        PublicEntryAdapter publicEntryAdapter = new PublicEntryAdapter(getApplicationContext(), publishedDailyEntries);
        publicEntryListView.setAdapter(publicEntryAdapter);
    }
}