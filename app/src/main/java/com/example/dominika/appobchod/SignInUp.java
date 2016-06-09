package com.example.dominika.appobchod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInUp extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);

        etUserName = (EditText) findViewById(R.id.etUser);
        etPassword = (EditText) findViewById(R.id.etPass);

        password = (EditText) findViewById(R.id.etPass);
        password.setTransformationMethod(new PasswordTransformationMethod());

        etUserName.setText("kasia");
        etPassword.setText("kasia");
    }

    public void signup(View v) {
        String userName = etUserName.getText().toString();
        String passWord = etPassword.getText().toString();

        Toast.makeText(this, "Logowanie...", Toast.LENGTH_SHORT).show();
        new ConnectionSignInUp(this).execute(userName, passWord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
