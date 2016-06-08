package com.example.dominika.appobchod;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Interview extends AppCompatActivity {

    private EditText etName, etSurname, etPesel, etAddress, etCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview);

        Button next = (Button) findViewById(R.id.button_add_alergy);
        etName = (EditText) findViewById(R.id.patientName);
        etSurname = (EditText) findViewById(R.id.patientSurname);
        etPesel = (EditText) findViewById(R.id.patientPesel);
        etAddress = (EditText) findViewById(R.id.patientAddress);
        etCity = (EditText) findViewById(R.id.patientCity);

        assert next != null;
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Interview.this, ShowAllergensList.class);
                startActivity(intent);
            }
        });
    }

    public void addPatientToDataBase(View v) {
        String patientName = etName.getText().toString();
        String patientSurname = etSurname.getText().toString();
        String patientPesel = etPesel.getText().toString();
        String patientAddress = etAddress.getText().toString();
        String patientCity = etCity.getText().toString();

        new ConnectionInsertInterview(this).execute(patientName, patientSurname, patientPesel, patientAddress, patientCity);
    }

    public void ShowAllerginScreen(View w){
        Intent intent = new Intent(Interview.this, ShowAllergensList.class);
        startActivity(intent);
    }

    //TODO walidacja pesel
    /*private boolean isValidPesel(View v) {
        boolean check = false;
        EditText psl = (EditText) findViewById(R.id.patientPESEL);
        String pesel = psl.getText().toString();

        if(pesel.matches("[0-9]{11}$")) {
            if(pesel.length() < 6 || pesel.length() > 13) {
                check = false;
                Log.e("Validation","Not Valid PESEL");
            }
            else {
                check = true;
            }
        }
        else {
            check=false;
        }
        return check;
    }*/
}
