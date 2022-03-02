package com.example.calendarappexample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Entry {
    public static ArrayList<Entry> entriesList = new ArrayList<>();

    public static ArrayList<Entry> eventsForDate(LocalDate date) {
        ArrayList<Entry> entries = new ArrayList<>();

        for(Entry entry : entriesList){
            if(entry.getDate().equals(date))
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
