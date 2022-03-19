package com.example.calendarappexample;

import java.time.LocalTime;
import java.util.ArrayList;

class HourEntry {
    LocalTime time;
    ArrayList<Entry> entries;

    public HourEntry(LocalTime time, ArrayList<Entry> entries) {
        this.time = time;
        this.entries = entries;

    }

    public LocalTime getTime()
    {
        return time;
    }

    public void setTime(LocalTime time)
    {
        this.time = time;
    }

    public ArrayList<Entry> getEntries()
    {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries)
    {
        this.entries = entries;
    }
}
