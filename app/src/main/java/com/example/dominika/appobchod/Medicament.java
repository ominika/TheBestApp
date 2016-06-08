package com.example.dominika.appobchod;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Medicament extends AppCompatActivity { //implements GestureDetector.OnGestureListener

    String data[];
    TextView nameTextView;
    AutoCompleteTextView acTextView;
    GestureDetector detector;
    Button setMedicamentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);

        data = new String[]{
                "Donepex 10mg",
                "Donepex 5mg",
                "Memotropil 800mg",
                "Memotropil 1000mg",
                "Polocard 75mg",
                "Polocard 150mg",
                "Ketonal 50mg",
                "Ketonal 100mg"};

        nameTextView = (TextView) findViewById(R.id.NameTextView);
        nameTextView.setText(getIntent().getExtras().getString("PATIENT_ID"));

        acTextView = (AutoCompleteTextView) findViewById(R.id.MedicamentAutoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, data);
        acTextView.setThreshold(1); // ile znaków by otrzymać sugestie
        acTextView.setAdapter(adapter);

        setMedicamentButton = (Button) findViewById(R.id.SetMedicamentButton);
        setMedicamentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetMedicamentOnClick();
            }
        });
    }

    public void SetMedicamentOnClick() {
        acTextView.setText("");
        //TODO: Add Investigation to database
    }

}
