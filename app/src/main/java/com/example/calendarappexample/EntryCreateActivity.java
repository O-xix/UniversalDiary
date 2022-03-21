package com.example.calendarappexample;

import static com.example.calendarappexample.MainActivity.SQLiteDB;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EntryCreateActivity extends AppCompatActivity {

    private EditText entryNameET, entryTextET;
    private TextView entryDateTV, entryTimeTV;

    private LocalTime time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_create);
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
        Entry newEntry = new Entry(entryName, entryText, "", 0, CalendarUtils.selectedDate, time);
        Entry.entriesList.add(newEntry);
        finish();
    }

    public void publishEntryAction(View view) {
        String entryName = entryNameET.getText().toString();
        String entryText = entryTextET.getText().toString();
        String entryPublishText;
        //convert into publishable entry
        entryPublishText = censorEntry(entryText);

        Entry newEntry = new Entry(entryName, entryText, "", 0, CalendarUtils.selectedDate, time);
        Entry newPublicEntry = new Entry(entryName, entryPublishText, "", 0, CalendarUtils.selectedDate, time);
        Entry.entriesList.add(newEntry);
        Entry.publicEntriesList.add(newPublicEntry);

        SQLiteDB.insertentry(entryName, entryText, "", 0, CalendarUtils.formattedDate(CalendarUtils.selectedDate), CalendarUtils.formattedTime(time));

        finish();
    }

    public String censorEntry(String entryText) {
        String[] strArray = entryText.split(" " );
        ArrayList<String> generatedMaleNames = new ArrayList<String>();
        ArrayList<String> generatedMaleNamesInUse = new ArrayList<String>();

        ArrayList<String> generatedFemaleNames = new ArrayList<String>();
        ArrayList<String> generatedFemaleNamesInUse = new ArrayList<String>();

        ArrayList<String> generatedNonbinaryNames = new ArrayList<String>();
        ArrayList<String> generatedNonbinaryNamesInUse = new ArrayList<String>();

        ArrayList<String> OGmaleNames = new ArrayList<String>();
        ArrayList<String> OGfemaleNames = new ArrayList<String>();
        ArrayList<String> OGnonbinaryNames = new ArrayList<String>();
        //generated Male Names"
        Collections.addAll(generatedMaleNames, "Petros", "Wodajo", "Olatunji", "Linval", "Miloud", "Xiao", "Zhiji", "Haitao", "Osman", "Mosaad", "Claude", "Clement", "Hector", "Eric", "Alain", "Amos", "Gideon", "Omar", "Isaac", "Abhijit", "Ajay", "Ira", "Raja", "Sahil", "Caden", "Felix", "Harold", "Samuel", "Ryan", "Akiva", "Joel", "Hiram", "Zalman", "Meir", "Hayato", "Hiro", "Azuma", "Bunji", "Chikara", "Abel", "Julio", "David", "Oswald", "Hugo" );

        //generated Female Names
        Collections.addAll(generatedFemaleNames, "Mei", "Yumei", "Ai", "Yanmin", "Shujiao", "Miriam", "Merita", "Iset", "Pepi", "Henutta", "Karen", "Diane", "Selma", "Verona", "Terisa", "Alice", "Edith", "Heloise", "Isabelle", "Jeanne", "Hilda", "Frida", "Silke", "Nadine", "Lucia", "Eve", "Mila", "Edna", "Johanna", "Mia", "Bryna", "Ciara", "Deirdre", "Eden", "Sarisse", "Anu", "Gita", "Nidhi", "Vidya", "Kalki", "Eiko", "Fuka", "Kawai", "Mari", "Kumi", "Deborah", "Hannah", "Sarah", "Maria", "Tehila", "Aurora", "Carolina", "Kira", "Anastasia", "Veronica", "Claudia", "Erika", "Fatima", "Iris", "Marisa", "Tanisha", "Nahla", "Ayoka", "Thandi", "Ama" );
        //generated Nonbinary Names
        Collections.addAll(generatedNonbinaryNames, "Ivory", "Armani", "Makena", "Kara", "Ashe", "Nana", "Tolu", "Bassey", "Abim", "Ade", "Farah", "Ismat", "Amani", "Nasim", "Rhayan", "Aviv", "Elia", "Liel", "Ori", "Shay", "Harshal", "Nehal", "Niral", "Rajan", "Kira", "Akira", "Jun", "Katsumi", "Mikoto", "Mirai", "Nika", "Tiam", "Arya", "Cemre", "Nur", "Linh", "Anh", "Tuon", "Khane", "Nhan", "Amaiur", "Joa", "Ibiur", "Sahat", "Erait", "Matija", "Sasa", "Borna", "Vanja", "Minja", "Alex", "Jean", "Robin", "Morgan", "Vivian", "Adrie", "Daan", "Gerdi", "Jopi", "Rinke", "Blair", "Emerson", "Jesse", "Marion", "Riley", "Ariel", "Kari", "Noe", "Sirius", "Vieno", "Beau", "Celeste", "Lois", "Marron", "Stephane", "Luca", "Eris", "Nyx", "Paris", "Doria", "Casey", "Flan", "Ailbhe", "Slaine", "Naoise", "Nadir", "Niomar", "Ivic", "Jose", "Kris", "Jax" );

        String[] censoredText = strArray;

        for (int i = 0; i < strArray.length; i++) {
            String contentword = strArray[i];
            if ((contentword.charAt(0) == '~') && contentword.charAt(contentword.length() - 1) == '~') {
                //actual init variables
                String name = strArray[i + 1];
                int generatednum;
                if (name.charAt(0) == '*') {
                    Random randint = new Random();
                    if (contentword.equals("~maleName~" )) {
                        OGmaleNames.add(name);
                        int malenum = OGmaleNames.indexOf(name);
                        generatednum = randint.nextInt(42);
                        String generatedMaleNam = generatedMaleNames.get(generatednum);
                        generatedMaleNames.remove(generatednum);
                        generatedMaleNamesInUse.add(malenum, generatedMaleNam);
                        censoredText[i] = "";
                    }
                    else if (contentword.equals("~femaleName~" )) {
                        OGfemaleNames.add(name);
                        int femalenum = OGfemaleNames.indexOf(name);
                        generatednum = randint.nextInt(65);
                        String generatedFemaleNam = generatedFemaleNames.get(generatednum);
                        generatedFemaleNames.remove(generatednum);
                        generatedFemaleNamesInUse.add(femalenum, generatedFemaleNam);
                        censoredText[i] = "";
                    }
                    else if (contentword.equals("~nonbinaryName~" )) {
                        OGnonbinaryNames.add(name);
                        int nonbinarynum = OGnonbinaryNames.indexOf(name);
                        generatednum = randint.nextInt(91);
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
        for (int j = 0; j < strArray.length; j++) {
            if (OGmaleNames.contains(strArray[j])) {
                nameIndex = OGmaleNames.indexOf(strArray[j]);
                censoredText[j] = generatedMaleNamesInUse.get(nameIndex);
            }
            else if (OGfemaleNames.contains(strArray[j])) {
                nameIndex = OGfemaleNames.indexOf(strArray[j]);
                censoredText[j] = generatedFemaleNamesInUse.get(nameIndex);
            }
            else if (OGnonbinaryNames.contains(strArray[j])) {
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
        return censoredEntry;
        //code end
    }
}