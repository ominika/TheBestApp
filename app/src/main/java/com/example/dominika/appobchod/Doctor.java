package com.example.dominika.appobchod;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Dominika on 21.04.2016.
 */
public class Doctor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_1_ll);
    }

    public void order(View v) {
        String xx = "";

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("patient_hospital.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            int col = s.getColumns();

            for (int i = 0; i<row; i++) {
                for (int j = 0; j<col; j++) {
                    Cell c = s.getCell(j,i);
                    xx += c.getContents();
                }
                xx += "\n";
            }
            display(xx);
        } catch (Exception e) {

        }
    }
    public void display(String value) {
        TextView x = (TextView) findViewById(R.id.patients_hospital);
        x.setText(value);
    }
}
