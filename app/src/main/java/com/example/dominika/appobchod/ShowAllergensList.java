package com.example.dominika.appobchod;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/*
TODO: Musisz sobie dodać w widoku teraz dwa przyciski
tu Muzyka - kto to ma dodać???
ZAPISZ który przeleci przez wszystkie elementy, sprawdzi czy są selected i doda do bazy
DODAJ nowy allergen i okno wprowadzenia nowego alergeniu
 */
public class ShowAllergensList extends AppCompatActivity implements AsyncResponse {

    private Context context;
    private ListView list;
    String[] items;
    ArrayList<String> allergens = new ArrayList<String>();
    EditText editText;
    AllergensListViewItemAdapter adapter;
    int editTextLastLength;

    ConnectionShowAllergensList asyncTask = new ConnectionShowAllergensList(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allergens_list);

        list = (ListView) findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.findAllerginTextEdit);
        editTextLastLength = 0;

        createConnection();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                    initList();
                else
                    searchItem(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void createConnection() {
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    public void processFinish(String[] itemsBack){
        items = itemsBack;
        initList();
    }

    public void initList()
    {
        allergens = new ArrayList<>(Arrays.asList(items));
        Collections.sort(allergens);
        adapter = new AllergensListViewItemAdapter(this, R.layout.allergin_list_view_item,  allergens);
        list.setAdapter(adapter);
    }

    /*public void initList()
    {
        items = new String[]{"Alergenik1", "Alegren2", "Pelargina", "Paracetamol", "Muuuuzyka"};
        allergens = new ArrayList<>(Arrays.asList(items));
        Collections.sort(allergens);
        //for(int i=0; i<9; i++)
        //data.add(new PatientListViewItem(i,cars[i]));
        adapter = new AllergensListViewItemAdapter(this, R.layout.allergin_list_view_item,  allergens);
        list.setAdapter(adapter);
    }*/

    public void searchItem(String textToSearch){
        if(editTextLastLength > textToSearch.length())
            initList();
        editTextLastLength = textToSearch.length();
        for(String item:items){
            if(!item.contains(textToSearch)) {
                allergens.remove(item);
                Log.d("aller", String.valueOf(allergens));
            }
        }

        adapter.notifyDataSetChanged();
    }

    public void addNewAllergen(View w) {
        Toast.makeText(context, "kuczaki", Toast.LENGTH_SHORT).show();
        if (String.valueOf(allergens) == null) {
            Toast.makeText(context, "nuullll", Toast.LENGTH_SHORT).show();
            //addNewAllergenToDataBase();
            //createConnection();
        } else {
            Toast.makeText(context, "Wprowadź nową nazwę alergenu", Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewAllergenToDataBase() {
        Intent intent = new Intent(ShowAllergensList.this, Interview.class);
        startActivity(intent);
        Toast.makeText(context, "Wprowadź nową nazwę alergenu!!!", Toast.LENGTH_SHORT).show();
        //new ConnectionInsertAllergen(this).execute(editText.toString());
    }
}
