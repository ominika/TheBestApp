package com.example.dominika.appobchod;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ConnectionGetMedicament extends AsyncTask<String, Void, String> implements LinkToConnect{

    private Context context;
    private String file_name = "get_medicament.php";

    public AsyncResponse delegate = null;

    public ConnectionGetMedicament(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            //data = "?pesel=" + URLEncoder.encode(patientPesel, "UTF-8");
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
        String [] meds = null;

            if (jsonStr != null) {
                try {
                    JSONArray mJsonArray = new JSONArray(result);
                    JSONObject mJsonObject = new JSONObject();
                    meds = new String[mJsonArray.length()];
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        mJsonObject = mJsonArray.getJSONObject(i);
                        meds[i] = mJsonObject.getString("id_lek");
                        meds[i] = mJsonObject.getString("nazwa");
                        meds[i] += " ";
                        meds[i] += mJsonObject.getString("substancja");
                        meds[i] += "\n";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Brak leków w bazie", Toast.LENGTH_SHORT).show();
            }
        delegate.processFinish(meds);
    }
}
