package com.example.calendarappexample;

import static com.example.calendarappexample.DailyViewActivity.selectedViewHour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HourEntryListActivity extends AppCompatActivity {
    private TextView hourTV;
    private ListView entriesForHourList;
    public static Entry selectedViewEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_entry_list);
        initWidgets();
        setHourView();
        setViewEntryAdapter();
        Context weekViewActivityContext = this;
        entriesForHourList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedViewEntry = (Entry) parent.getItemAtPosition(position);
                startActivity(new Intent(weekViewActivityContext, EntryViewActivity.class));
            }
        });
    }

    public void initWidgets(){
        //Find both views by id:
        hourTV = findViewById(R.id.HourTextTV);
        entriesForHourList = findViewById(R.id.EntriesForHourList);
    }

    private void setHourView() {
        hourTV.setText(CalendarUtils.formattedTime(selectedViewHour.getTime()));
        setViewEntryAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewEntryAdapter();
    }

    private void setViewEntryAdapter() {
        ArrayList<Entry> entriesForHour = selectedViewHour.getEntries();
        PublicEntryAdapter viewEntryAdapter = new PublicEntryAdapter(getApplicationContext(), entriesForHour);
        entriesForHourList.setAdapter(viewEntryAdapter);
    }
}