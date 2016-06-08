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

public class ConnectionAddAllergen extends AsyncTask<String, Void, String> implements LinkToConnect {
    private Context context;
    private String file_name = "add_allergen.php";

    public ConnectionAddAllergen(Context context) {
        this.context = context;
    }

    //public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... params) {
        String allergenName = params[0];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?nazwa=" + URLEncoder.encode(allergenName, "UTF-8");

            link = partial_link + file_name + data;
            Log.d("LINK", link);
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
                    Toast.makeText(context, "goooooooood", Toast.LENGTH_SHORT).show();
                } /*else if (query_result.equals("SUCCESSLOG")) {
                    Toast.makeText(context, "Logowanie pomyślne", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FirstScreen.class);
                    context.startActivity(intent);
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Próba łączenia z bazą zakończona błędem", Toast.LENGTH_SHORT).show();
                    Log.d("DEB5",query_result);
                }*/
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Brak danych", Toast.LENGTH_SHORT).show();
        }
    }
}
