package com.example.dominika.appobchod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
    }

    public void DoctorButtonOnClick(View view)
    {
        Intent intent = new Intent(FirstScreen.this, Interview.class);
        startActivity(intent);
    }

    public void NurseButtonOnClick(View view)
    {
        Intent intent = new Intent(FirstScreen.this, ShowPatientsList.class);
        startActivity(intent);
    }

    public void TechnicalButtonOnClick(View view)
    {
        Intent intent = new Intent(FirstScreen.this, ShowAllergensList.class);
        startActivity(intent);
    }
}
