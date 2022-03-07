package com.example.calendarappexample;

import android.media.metrics.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Entry {
    public static ArrayList<Entry> entriesList = new ArrayList<>();

    public static ArrayList<Entry> entriesForDate(LocalDate date) {
        ArrayList<Entry> entries = new ArrayList<>();

        for(Entry entry : entriesList){
            if(entry.getDate().equals(date))
                entries.add(entry);
        }

        return entries;
    }

    public static ArrayList<Entry> entriesForDateAndTime(LocalDate date, LocalTime time)
    {
        ArrayList<Entry> entries = new ArrayList<>();

        for(Entry entry : entriesList)
        {
            int entryHour = entry.time.getHour();
            int cellHour = time.getHour();
            if(entry.getDate().equals(date) && entryHour == cellHour)
                entries.add(entry);
        }

        return entries;
    }

    private String name;
    private String text;
    private LocalDate date;
    private LocalTime time;

    public Entry(String name, String text, LocalDate date, LocalTime time) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
