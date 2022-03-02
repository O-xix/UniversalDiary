package com.example.calendarappexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Entry> {

    public EventAdapter(@NonNull Context context, List<Entry> entries) {
        super(context, 0, entries);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Entry entry = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView entryCellTV = convertView.findViewById(R.id.eventCellTV);

        String entryTitle = "\"" + entry.getName() + "\" at " + CalendarUtils.formattedTime(entry.getTime());
        entryCellTV.setText(entryTitle);
        return convertView;

    }
}
