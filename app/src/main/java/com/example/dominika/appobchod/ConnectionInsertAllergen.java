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

public class ConnectionInsertAllergen extends AsyncTask<String, Void, String> implements LinkToConnect  {

    private Context context;
    private String file_name = "signin.php";

    public ConnectionInsertAllergen(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String userName = params[0];
        String passWord = params[1];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?user=" + URLEncoder.encode(userName, "UTF-8");
            data += "&pass=" + URLEncoder.encode(passWord, "UTF-8");

            link = partial_link + file_name + data;
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
                if (query_result.equals("SUCCESSIN")) {
                    Toast.makeText(context, "Rejestracja pomyślna - zaloguj się teraz", Toast.LENGTH_SHORT).show();
                } else if (query_result.equals("SUCCESSLOG")) {
                    Toast.makeText(context, "Logowanie pomyślne", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, FirstScreen.class);
                    context.startActivity(intent);
                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Próba łączenia z bazą zakończona błędem", Toast.LENGTH_SHORT).show();
                    Log.d("DEB5",query_result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Upss!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Brak danych", Toast.LENGTH_SHORT).show();
        }
    }
}
