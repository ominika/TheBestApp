package com.example.dominika.appobchod;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    //ConnectionAddAllergen asyncTaskAdd = new ConnectionAddAllergen(this);

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

        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OnEndButtonClick();
            }
        });
    }

    public void createConnection() {
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    private void addNewAllergenToDataBase() {
        String allergenName = editText.getText().toString();
        new ConnectionAddAllergen(this).execute(allergenName);
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

    public void searchItem(String textToSearch){
        if(editTextLastLength > textToSearch.length())
            initList();
        editTextLastLength = textToSearch.length();
        for(String item:items){
            if(!item.contains(textToSearch)) {
                allergens.remove(item);
            }
        }

        adapter.notifyDataSetChanged();
    }

    public void AddNewAllergen(View view)
    {
        if (allergens.size() == 0) {
            addNewAllergenToDataBase();
            Intent intent = new Intent(ShowAllergensList.this, ShowAllergensList.class);
            startActivity(intent);

        } else {
            return;
        }
    }

    public void OnEndButtonClick(){
        List<String> myList = adapter.choosen(); // w myList lista wybranych
        //te petle pozniej usun!!!!!:D DŻOLO :D
        for(int i=0; i<myList.size(); i++)
            Toast.makeText(this, myList.get(i), Toast.LENGTH_SHORT).show();
        //TU DODANIE DO BAZY
        super.onBackPressed();
    }
}
