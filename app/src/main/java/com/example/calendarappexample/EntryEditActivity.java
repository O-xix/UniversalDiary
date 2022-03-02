package com.example.calendarappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EntryEditActivity extends AppCompatActivity {

    private EditText entryNameET, entryTextET;
    private TextView entryDateTV, entryTimeTV;

    private LocalTime time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        time = LocalTime.now();
        entryDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        entryTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets() {
        entryTextET = findViewById(R.id.entryTextET);
        entryNameET = findViewById(R.id.entryNameET);
        entryDateTV = findViewById(R.id.entryDateTV);
        entryTimeTV = findViewById(R.id.entryTimeTV);
    }

    public void saveEntryAction(View view) {
        String entryName = entryNameET.getText().toString();
        String entryText = entryTextET.getText().toString();
        Entry newEntry = new Entry(entryName, entryText, CalendarUtils.selectedDate, time);
        Entry.entriesList.add(newEntry);
        finish();
    }

    public void publishEntryAction(View view) {
        String entryName = entryNameET.getText().toString();
        String entryText = entryTextET.getText().toString();
        String entryPublishText;
        //convert into publishable entry
        for(entryText.length) {
            //code start
            String[] strArray = text.split(" ");
            ArrayList<String> OGmaleNames;
            ArrayList<String> generatedMaleNames = [Kris, Rosa, Shreya];
            ArrayList<String> generatedMaleNamesInUse;
            ArrayList<String> OGfemaleNames;
            ArrayList<String> OGnonbinaryNames;
            Scanner content = new Scanner(text);
            for(int i = 0; i <= strArray.length; i++){
                if(content.hasNext() == true) {
                    String contentword = content.next();
                    int contentwordlength = contentword.length();
                    if((contentword.charAt(0) == '<') && contentword.contains(">")) {
                        //actual init variables
                        String name = content.next();
                        if(name.charAt(0) = '*') {
                            Random randint = new Random();
                            if(contentword = ("<maleName>")){
                                OGmaleNames.add(name);
                                int malenum = OGmaleNames.indexOf(name);
                                generatednum = randint.nextInt(3);
                                String generatedMaleNam = generatedMaleNames.get(generatednum);
                                generatedMaleNames.remove(generatednum);
                                generatedMaleNamesInUse.add(malenum, name);

                            }
                            else if(contentword = ("<femaleName>")){
                                OGfemaleNames.add(name);
                                int femalenum = OGfemaleNames.indexOf(name);
                                generatednum = randint.nextInt(3);
                                String generatedFemaleNam = generatedFemaleNames.get(generatednum);
                                generatedFemaleNames.remove(generatednum);
                                generatedFemaleNamesInUse.add(femalenum, name);
                            }
                            else if(contentword = ("<nonbinaryName>")){
                                OGnonbinaryNames.add(name);
                                int nonbinarynum = OGnonbinaryNames.indexOf(name);
                                generatednum = randint.nextInt(3);
                                String generatedNonbinaryNam = generatedNonbinaryNames.get(generatednum);
                                generatedNonbinaryNames.remove(generatednum);
                                generatedNonbinaryNamesInUse.add(nonbinarynum, name);

                            }
                        }
                    }
                }
            }
            //code end
        }
        Entry newEntry = new Entry(entryName, entryText, CalendarUtils.selectedDate, time);
        Entry.entriesList.add(newEntry);
        finish();
    }
}