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

public class PublicEntryAdapter extends ArrayAdapter<Entry> {
    public PublicEntryAdapter(@NonNull Context context, List<Entry> publicEntries) {
        super(context, 0, publicEntries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Entry entry = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.entry_cell, parent, false);

        TextView entryCellTV = convertView.findViewById(R.id.entryCellTV);

        String entryTitle = "\"" + entry.getTitle() + "\" at " + CalendarUtils.formattedTime(entry.getTime());
        entryCellTV.setText(entryTitle);
        return convertView;

    }
}
