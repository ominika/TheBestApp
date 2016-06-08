package com.example.dominika.appobchod;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Console;
import java.util.List;

/**
 * Created by Beata on 21.04.2016.
 * Jestem magiczną klasą, nie próbuj mnie zrozumieć ;)
 */
public class PatientsListViewItemAdapter extends ArrayAdapter<String> {

    private int layout;
    List <String> data = null;
    Context context;

    public PatientsListViewItemAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        layout = resource;
        data = objects;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        RowHolder myRowHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layout,parent,false);
            RowHolder rowHolder = new RowHolder();
            rowHolder.patientName = (TextView) row.findViewById(R.id.list_item_text_view);
            rowHolder.patientName.setText(data.get(position));
            rowHolder.setInvestigationButton = (Button) row.findViewById(R.id.list_item_SetInvestigationButton);
            rowHolder.setMedicamentButton = (Button) row.findViewById(R.id.list_item_SetMedicamentButton);
            rowHolder.dischargeButton = (Button) row.findViewById(R.id.list_item_DischargeButton);
            rowHolder.showPatientCardButton = (Button) row.findViewById(R.id.list_item_ShowPatientCardButton);

            rowHolder.setInvestigationButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"Button was clicked for list item "+position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context.getApplicationContext(), Diagnostic.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("PATIENT_ID", data.get(position));
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });

            rowHolder.setMedicamentButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), Medicament.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("PATIENT_ID", data.get(position));
                    context.getApplicationContext().startActivity(intent);
                }
            });
            rowHolder.dischargeButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), Discharge.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("PATIENT_ID", data.get(position));
                    context.getApplicationContext().startActivity(intent);
                }
            });

            rowHolder.showPatientCardButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), FirstScreen.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("PATIENT_ID", data.get(position));
                    context.getApplicationContext().startActivity(intent);
                }
            });
            row.setTag(rowHolder);
        }
        else{
            myRowHolder = (RowHolder) row.getTag();
            myRowHolder.patientName.setText(getItem(position));
        }
        return row;
    }

    public class RowHolder{
        TextView patientName;
        Button setInvestigationButton;
        Button setMedicamentButton;
        Button dischargeButton;
        Button showPatientCardButton;
    }
}
