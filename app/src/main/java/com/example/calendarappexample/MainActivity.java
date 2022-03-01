package com.example.calendarappexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    //Finding Date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public static ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
        //To get days in month.
        ArrayList<LocalDate> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        //Getting first day of month.

        LocalDate firstOfMonth = CalendarUtils.selectedDate.withDayOfMonth(1);
            //Returns the day of the first day as an int between 1-7.
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        //Grid, 7x7 objects, cycling through them?
        for(int i = 1; i <= 42; i++) {
            if (i < dayOfWeek || i > (daysInMonth + dayOfWeek)) {
                daysInMonthArray.add(null);
            }
            else {
                daysInMonthArray.add(LocalDate.parse(String.valueOf(i - dayOfWeek)));
            }
        }
        return daysInMonthArray;
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
    public void onItemClick(int position, String dayText) {
        //Date Selection Text:
        String message = "Selected Date " + dayText + " " + monthYearFromDate(CalendarUtils.selectedDate);
        //A Toast is like a fade-in, fade-out text message, like an error message; make the on-item click listener work with your recycler view.
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void weeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }
}