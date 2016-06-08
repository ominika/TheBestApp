package com.example.dominika.appobchod;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ConnectionInsertInterview extends AsyncTask<String, Void, String> implements LinkToConnect{
    private Context context;
    private String file_name = "add_patient.php";

    public ConnectionInsertInterview(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String patientName = params[0];
        String patientSurname = params[1];
        String patientPesel = params[2];
        String patientAddress = params[3];
        String patientCity = params[4];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?pesel=" + URLEncoder.encode(patientPesel, "UTF-8");
            data += "&imie=" + URLEncoder.encode(patientName, "UTF-8");
            data += "&nazwisko=" + URLEncoder.encode(patientSurname, "UTF-8");
            data += "&adres=" + URLEncoder.encode(patientAddress, "UTF-8");
            data += "&miasto=" + URLEncoder.encode(patientCity, "UTF-8");

            Log.d("tekst", data);
            link = partial_link + file_name + data;

            Log.d("tekst", link);
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();

            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("query_result");
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "Dodano do bazy", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Próba łączenia z bazą zakończona błędem", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Brak danych", Toast.LENGTH_SHORT).show();
        }

        //TODO-BEATA (?) żeby otworzyło się okno z listą pacjentów - onCreate - zeby baza na nowo się wczytała
    }

}
