package com.excilys.android.formation.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ParlezVousActivity extends AppCompatActivity {
    private final String TAG = ParlezVousActivity.class.getSimpleName();
    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parlez_vous_main);
        usernameField = (EditText) findViewById(R.id.editTextName);
        passwordField = (EditText) findViewById(R.id.editTextPassword);
    }

    public void Send(View v) {
        String s = "";
        if (isValid()) {
            s += "Username: \"";
            s += this.usernameField.getText().toString();
            s += "\", password: \"";
            s += this.passwordField.getText().toString();
            s += "\"";
        } else {
            s = "Please fill all the fields!";
        }

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void Clear(View v) {
        this.usernameField.setText(R.string.textFieldUserName);
        this.passwordField.setText(R.string.textFieldPassword);
    }

    private boolean isValid() {
        String s = this.usernameField.getText().toString();
        if (s.equals("")) {
            return false;
        }

        s = this.passwordField.getText().toString();
        if (s.equals("")) {
            return false;
        }

        return true;
    }
}
