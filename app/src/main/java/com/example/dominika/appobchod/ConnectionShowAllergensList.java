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

public class ConnectionShowAllergensList extends AsyncTask<String, Void, String> implements LinkToConnect{

    private Context context;
    private String file_name = "get_allergen.php";

    public AsyncResponse delegate = null;

    public ConnectionShowAllergensList(Context context) {
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
        String [] allergens = null;

            if (jsonStr != null) {
                try {
                    JSONArray mJsonArray = new JSONArray(result);
                    JSONObject mJsonObject = new JSONObject();
                    allergens = new String[mJsonArray.length()];
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        mJsonObject = mJsonArray.getJSONObject(i);
                        allergens[i] = mJsonObject.getString("nazwa");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Brak alergenów", Toast.LENGTH_SHORT).show();
            }
        delegate.processFinish(allergens);
    }
}
