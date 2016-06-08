package com.example.dominika.appobchod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Discharge extends AppCompatActivity {

    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discharge);

        nameTextView = (TextView) findViewById(R.id.NameTextView);
        nameTextView.setText(getIntent().getExtras().getString("PATIENT_ID"));
    }
}
