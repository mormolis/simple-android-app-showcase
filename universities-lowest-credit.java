package com.example.mormolis.listviewparsing;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(30000);

        AlertDialog.Builder warning = new AlertDialog.Builder(this);
        warning.setMessage("Προσοχή! Οι βάσεις που θα δείτε είναι του 2015.");
        AlertDialog warnMessage = warning.create();
        warnMessage.show();
    }//oncreat



    private void init(int vathmoulakia) {


        ArrayList<Moria> items = new ArrayList<Moria>();
        ListView listView = (ListView) findViewById(R.id.lvItems);


        try{
            //populating the ArrayList with CSV's Data (using the opencsv library
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("moria.csv")), ':');
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line

                    Moria temp = new Moria(nextLine[0], Integer.valueOf(nextLine[1]));
                    items.add(temp);


            }

            CustomAdapter itemsAdapter =
                    new CustomAdapter(this, items);


            listView.setAdapter(itemsAdapter);
        }catch (IOException e){
            e.printStackTrace();
        }




    } //init

 public void showUnis(View view){
     EditText editText;
     editText = (EditText) findViewById(R.id.moria_sxolon);
     String moria = editText.getText().toString();
     Toast toast;
     if( !moria.equals("")){
         int moriaNumber = Integer.valueOf(moria);
         if (moriaNumber > 0 && moriaNumber<=24000) {
             init(moriaNumber);
         } else {
             toast = Toast.makeText(getApplicationContext(), "Τα μόρια πρέπει να είναι από 1 μέχρι 24000", Toast.LENGTH_SHORT);
             toast.show();
         }
     }else{
         toast = Toast.makeText(getApplicationContext(), "Βάλε μία τιμή για να δεις σε ποιες σχολές περνάς", Toast.LENGTH_SHORT);
         toast.show();
     }
 } //showUnis


}//class
