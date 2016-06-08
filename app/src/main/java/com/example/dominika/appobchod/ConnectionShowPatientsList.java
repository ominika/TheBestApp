package com.example.dominika.appobchod;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionShowPatientsList extends AsyncTask<String, Void, String> implements LinkToConnect{

    private Context context;
    private String file_name = "get_patient.php";

    public AsyncResponse delegate = null;

    public ConnectionShowPatientsList(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String link;
        BufferedReader bufferedReader;
        String result;

        try {
            link = partial_link + file_name;

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
        String [] patients = null;

            if (jsonStr != null) {
                try {
                    JSONArray mJsonArray = new JSONArray(result);
                    JSONObject mJsonObject = new JSONObject();
                    patients = new String[mJsonArray.length()];
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        mJsonObject = mJsonArray.getJSONObject(i);
                        patients[i] = mJsonObject.getString("imie");
                        patients[i] += " ";
                        patients[i] += mJsonObject.getString("nazwisko");
                        patients[i] += "\n";
                        patients[i] += mJsonObject.getString("pesel");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Wszyscy zdrowi", Toast.LENGTH_SHORT).show();
            }
        delegate.processFinish(patients);
    }
}
