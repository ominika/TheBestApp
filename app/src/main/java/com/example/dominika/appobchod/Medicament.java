package com.example.dominika.appobchod;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Medicament extends AppCompatActivity implements AsyncResponse { //implements GestureDetector.OnGestureListener

    String data[];
    String data2[];
    String pesel = null;
    String help = "fill";
    TextView nameTextView;
    AutoCompleteTextView acTextView;
    GestureDetector detector;
    Button setMedicamentButton;
    private ArrayAdapter itemsAdapter;
    private ListView listView;

    ConnectionGetMedicament asyncTask = new ConnectionGetMedicament(this);
    ConnectionGetPatientMedicament asyncTask2 = new ConnectionGetPatientMedicament(this);
    ConnectionAddPatientMedicament asyncTask3 = new ConnectionAddPatientMedicament(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);

        nameTextView = (TextView) findViewById(R.id.NameTextView);
        nameTextView.setText(getIntent().getExtras().getString("PATIENT_ID"));

        setMedicamentButton = (Button) findViewById(R.id.SetMedicamentButton);
        setMedicamentButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetMedicamentOnClick();
                //TODO /n zeby sie nie dodawał przy wyborze leku z listy
            }
        });


        getPatientMedicamet();
        createConnection();
    }

    public void SetMedicamentOnClick() {
        acTextView.setText("");
        help = "fill";
        getPesel();
        asyncTask3.delegate = this;
        asyncTask3.execute(pesel);

    }

    private void getPatientMedicamet() {
        getPesel();

        if (pesel != null) {
            asyncTask2.delegate = this;
            asyncTask2.execute(pesel);
        }
        else
            return;
    }

    private void fillAdapter() {
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data2);
        listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(itemsAdapter);
    }

    public void createConnection() {
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    private void init() {
        acTextView = (AutoCompleteTextView) findViewById(R.id.MedicamentAutoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, data);
        acTextView.setThreshold(1); // ile znaków by otrzymać sugestie
        acTextView.setAdapter(adapter);
    }

    private void getPesel() {
        String nameSurnamePesel = nameTextView.getText().toString();
        String [] chunks = nameSurnamePesel.split("\\r?\\n");
        pesel = chunks.length == 2 ? chunks [1] : null;
    }

    @Override
    public void processFinish(String[] output) {
        if (help == "fill") {
            data2 = output;
            fillAdapter();
            help = "init";
        }
        else {
            data = output;
            init();
        }
    }
}
