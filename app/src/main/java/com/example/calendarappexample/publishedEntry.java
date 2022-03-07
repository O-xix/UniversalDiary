package com.example.calendarappexample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class publishedEntry extends Entry {
    public static ArrayList<publishedEntry> publicEntriesList = new ArrayList<>();

    public static ArrayList<publishedEntry> publicEntryForDate(LocalDate date) {
        ArrayList<publishedEntry> publicEntries = new ArrayList<>();

        for(publishedEntry publicEntry : publicEntriesList){
            if(publicEntry.getDate().equals(date))
                publicEntries.add(publicEntry);
        }

        return publicEntries;
    }

    private String name;
    private String text;
    private String comments;
    private int numComments;
    private LocalDate date;
    private LocalTime time;

    public publishedEntry(String name, String text, String comments, int numComments, LocalDate date, LocalTime time) {
        super(name, text, date, time);
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
