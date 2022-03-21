package com.example.calendarappexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends ArrayAdapter<HourEntry> {
    public HourAdapter(@NonNull Context context, List<HourEntry> hourEvents) {
        super(context, 0, hourEvents);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HourEntry entry = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hour_cell, parent, false);

        setHour(convertView, entry.time);
        setEntries(convertView, entry.entries);

        return convertView;
    }

    private void setHour(View convertView, LocalTime time) {
        TextView timeTV = convertView.findViewById(R.id.timeTV);
        timeTV.setText(CalendarUtils.formattedShortTime(time));
    }

    private void setEntries(View convertView, ArrayList<Entry> entries) {
        TextView entry1 = convertView.findViewById(R.id.entry1);
        TextView entry2 = convertView.findViewById(R.id.entry2);
        TextView entry3 = convertView.findViewById(R.id.entryMore);

        if(entries.size() == 0) {
            hideEntry(entry1);
            hideEntry(entry2);
            hideEntry(entry3);
        }
        else if(entries.size() == 1) {
            setEntry(entry1, entries.get(0));
            hideEntry(entry2);
            hideEntry(entry3);
        }
        else if(entries.size() == 2) {
            setEntry(entry1, entries.get(0));
            setEntry(entry2, entries.get(1));
            hideEntry(entry3);
        }
        else if(entries.size() == 3) {
            setEntry(entry1, entries.get(0));
            setEntry(entry2, entries.get(1));
            setEntry(entry3, entries.get(2));
        }
        else {
            setEntry(entry1, entries.get(0));
            setEntry(entry2, entries.get(1));
            entry3.setVisibility(View.VISIBLE);
            String eventsNotShown = String.valueOf(entries.size() - 2);
            eventsNotShown += " More Events";
            entry3.setText(eventsNotShown);
        }
    }

    private void setEntry(TextView textView, Entry entry) {
        textView.setText(entry.getTitle());
        textView.setVisibility(View.VISIBLE);
    }

    private void hideEntry(TextView tv)
    {
        tv.setVisibility(View.INVISIBLE);
    }

}













