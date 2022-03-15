package com.example.calendarappexample;

import android.media.metrics.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Entry {
    public static ArrayList<Entry> entriesList = new ArrayList<>();
    public static ArrayList<Entry> publicEntriesList = new ArrayList<>();

    public static ArrayList<Entry> entriesForDate(LocalDate date) {
        ArrayList<Entry> entries = new ArrayList<>();

        for(Entry entry : entriesList){
            if(entry.getDate().equals(date))
                entries.add(entry);
        }

        return entries;
    }

    public static ArrayList<Entry> entriesForDateAndTime(LocalDate date, LocalTime time) {
        ArrayList<Entry> entries = new ArrayList<>();

        for(Entry entry : entriesList) {
            int entryHour = entry.time.getHour();
            int cellHour = time.getHour();
            if(entry.getDate().equals(date) && entryHour == cellHour)
                entries.add(entry);
        }

        return entries;
    }

    public static ArrayList<Entry> publicEntriesForDate(LocalDate selectedDate) {
        ArrayList<Entry> publicEntries = new ArrayList<>();

        for(Entry publicEntry : publicEntriesList){
            if(publicEntry.getDate().equals(selectedDate))
                publicEntries.add(publicEntry);
        }

        return publicEntries;
    }

    private String name;
    private String text;
    private LocalDate date;
    private LocalTime time;
    private String comments;
    private int numComments;

    public Entry(String name, String text, String comments, int numComments, LocalDate date, LocalTime time) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.numComments = numComments;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }
}
