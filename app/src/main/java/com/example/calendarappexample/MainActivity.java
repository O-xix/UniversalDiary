package com.example.calendarappexample;

import static com.example.calendarappexample.CalendarUtils.daysInMonthArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calendarappexample.ui.login.LoginActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    public static SQLiteDBHelper SQLiteDB;

    //Finding Date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LoginActivity.class));
        SQLiteDB = new SQLiteDBHelper(this);
        setContentView(R.layout.activity_main);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();
    }

    public void initWidgets(){
        //Find both views by id:
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTextView);
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yyyy");
        return date.format(formatter);
    }


    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        //Must always run on true.
        if(date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
            dailyAction(calendarRecyclerView);
        }
    }

    public void exploreAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyViewActivity.class));
    }
}