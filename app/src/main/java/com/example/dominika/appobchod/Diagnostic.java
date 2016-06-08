package com.example.dominika.appobchod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Diagnostic extends AppCompatActivity {

    String data[];
    ArrayList <String> icd9 = new ArrayList<String>();
    AutoCompleteTextView acTextView;
    TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);
        data = new String []{
                "A01 Badanie ogólne moczu (profil)",
                "A03 Badanie płynu mózgowo-rdzeniowego",
                "A05 Badanie płynu z jamy ciała (opłucnej, otrzewnej)",
                "A07 Białko w moczu",
                "A09 Bilirubina w moczu",
                "A11 Ciała ketonowe w moczu",
                "A12 Ciężar właściwy moczu",
                "A13 Erytrocyty/ hemoglobina w moczu",
                "A14 Leukocyty w moczu",
                "A15 Glukoza w moczu",
                "A17 Krew utajona w kale",
                "A19 Osad moczu",
                "A21 Pasożyty/ jaja pasożytów w kale",
                "A23 Resztki pokarmowe w kale",
                "A25 Urobilinogen w moczu"};


        acTextView = (AutoCompleteTextView) findViewById(R.id.InvestigationAutoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, data);
        acTextView.setThreshold(1); // ile znaków by otrzymać sugestie
        acTextView.setAdapter(adapter);

        nameTextView = (TextView) findViewById(R.id.NameTextView);
        nameTextView.setText(getIntent().getExtras().getString("PATIENT_ID"));

        Button setInvestigationButton = (Button) findViewById(R.id.SetInvestigationButton);
        assert setInvestigationButton != null;
        setInvestigationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setInvestigationOnClick();
            }
        });
    }
    public void initList(){
        icd9 = new ArrayList<>(Arrays.asList(data));
    }

    public void setInvestigationOnClick(){
        Switch mySwitch = (Switch) findViewById(R.id.CITOSwitch);
        mySwitch.setChecked(false);
        acTextView.setText("");
        //TODO: Add Investigation to database
    }
}
