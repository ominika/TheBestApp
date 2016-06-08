package com.example.dominika.appobchod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowPatientsList extends AppCompatActivity implements AsyncResponse {

    //TODO private, public ...
    private ListView list;
    String[] items;
    ArrayList<String> patients = new ArrayList<String>();
    EditText editText;
    PatientsListViewItemAdapter adapter;
    int editTextLastLength;

    ConnectionShowPatientsList asyncTask = new ConnectionShowPatientsList(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patients_list);

        list = (ListView) findViewById(R.id.listView);
        editText = (EditText)findViewById(R.id.findPatientTextEdit);
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
        patients = new ArrayList<>(Arrays.asList(items));
        adapter = new PatientsListViewItemAdapter(this, R.layout.patient_listview_item, patients);
        list.setAdapter(adapter);
    }

    public void searchItem(String textToSearch){
        if(editTextLastLength > textToSearch.length())
            initList();
        editTextLastLength = textToSearch.length();
        for(String item:items){
            if(!item.toLowerCase().contains(textToSearch.toLowerCase()))
                patients.remove(item);
        }

        adapter.notifyDataSetChanged();
    }

    public void AddNewPatient(View view)
    {
        Intent intent = new Intent(ShowPatientsList.this, Interview.class);
        startActivity(intent);
    }
}
