package com.example.calendarappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

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
        censorEntry(entryText);

        Entry newEntry = new Entry(entryName, entryText, CalendarUtils.selectedDate, time);
        Entry.entriesList.add(newEntry);
        finish();
    }

    public String censorEntry(String entryText) {
        String[] strArray = entryText.split(" ");
        ArrayList<String> generatedMaleNames = new ArrayList<String>();
        ArrayList<String> generatedMaleNamesInUse = new ArrayList<String>();

        ArrayList<String> generatedFemaleNames = new ArrayList<String>();
        ArrayList<String> generatedFemaleNamesInUse = new ArrayList<String>();

        ArrayList<String> generatedNonbinaryNames = new ArrayList<String>();
        ArrayList<String> generatedNonbinaryNamesInUse = new ArrayList<String>();

        ArrayList<String> OGmaleNames = new ArrayList<String>();
        ArrayList<String> OGfemaleNames = new ArrayList<String>();
        ArrayList<String> OGnonbinaryNames = new ArrayList<String>();
        //generated Male Names
        Collections.addAll(generatedMaleNames, "Petros", "Wodajo", "Olatunji", "Linval", "Miloud", "Xiao", "Zhiji"
                Haitao
                Osman
                Mosaad
                Claude
                Clement
                Hector
                Eric
                Alain
                Amos
                Gideon
                Omar
                Isaac
                Abhijit
                Ajay
                Ira
                Raja
                Sahil
                Caden
                Felix
                Harold
                Samuel
                Ryan
                Akiva
                Joel
                Hiram
                Zalman
                Meir
                Hayato
                Hiro
                Azuma
                Bunji
                Chikara
                Abel
                Julio
                David
                Oswald
                Hugo)

        //generated Female Names

        //generated Nonbinary Names


        String[] censoredText = strArray;

        for(int i = 0; i < strArray.length; i++){
            String contentword = strArray[i];
            if((contentword.charAt(0) == '<') && contentword.contains(">")) {
                //actual init variables
                String name = strArray[i + 1];
                int generatednum;
                if(name.charAt(0) == '*') {
                    Random randint = new Random();
                    if(contentword.equals("<maleName>")){
                        OGmaleNames.add(name);
                        int malenum = OGmaleNames.indexOf(name);
                        generatednum = randint.nextInt(3);
                        String generatedMaleNam = generatedMaleNames.get(generatednum);
                        generatedMaleNames.remove(generatednum);
                        generatedMaleNamesInUse.add(malenum, generatedMaleNam);
                        censoredText[i] = "";
                    }
                    else if(contentword.equals("<femaleName>")){
                        OGfemaleNames.add(name);
                        int femalenum = OGfemaleNames.indexOf(name);
                        generatednum = randint.nextInt(3);
                        String generatedFemaleNam = generatedFemaleNames.get(generatednum);
                        generatedFemaleNames.remove(generatednum);
                        generatedFemaleNamesInUse.add(femalenum, generatedFemaleNam);
                        censoredText[i] = "";
                    }
                    else if(contentword.equals("<nonbinaryName>")){
                        OGnonbinaryNames.add(name);
                        int nonbinarynum = OGnonbinaryNames.indexOf(name);
                        generatednum = randint.nextInt(3);
                        String generatedNonbinaryNam = generatedNonbinaryNames.get(generatednum);
                        generatedNonbinaryNames.remove(generatednum);
                        generatedNonbinaryNamesInUse.add(nonbinarynum, generatedNonbinaryNam);
                        censoredText[i] = "";
                    }
                }
            }
        }
        //switching/censorship part
        int nameIndex;
        for (int j = 0; j < strArray.length; j++){
            if (OGmaleNames.contains(strArray[j])){
                nameIndex = OGmaleNames.indexOf(strArray[j]);
                censoredText[j] = generatedMaleNamesInUse.get(nameIndex);
            }
            else if (OGfemaleNames.contains(strArray[j])){
                nameIndex = OGfemaleNames.indexOf(strArray[j]);
                censoredText[j] = generatedFemaleNamesInUse.get(nameIndex);
            }
            else if (OGnonbinaryNames.contains(strArray[j])){
                nameIndex = OGnonbinaryNames.indexOf(strArray[j]);
                censoredText[j] = generatedNonbinaryNamesInUse.get(nameIndex);
            }
        }
        //convert it back into a String entry
        String censoredEntry = "";
        for (int k = 0; k < censoredText.length; k++) {
            if (k == 0) {
                censoredEntry = censoredText[k];
            }
            else {
                censoredEntry = censoredEntry + " " + censoredText[k];
            }
        }
        System.out.println(censoredEntry);
        //code end

}