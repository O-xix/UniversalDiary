package com.example.calendarappexample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class PublishedEntry extends Entry {
    public static ArrayList<PublishedEntry> publicEntriesList = new ArrayList<>();

    public static ArrayList<PublishedEntry> publicEntryForDate(LocalDate date) {
        ArrayList<PublishedEntry> publicEntries = new ArrayList<>();

        for(PublishedEntry publicEntry : publicEntriesList){
            if(publicEntry.getDate().equals(date))
                publicEntries.add(publicEntry);
        }

        return publicEntries;
    }

    private String comments;
    private int numComments;

    public PublishedEntry(String name, String text, String comments, int numComments, LocalDate date, LocalTime time) {
        super(name, text, date, time);
        this.comments = comments;
        this.numComments = numComments;
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
