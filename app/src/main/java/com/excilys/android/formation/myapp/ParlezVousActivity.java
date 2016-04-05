package com.excilys.android.formation.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ParlezVousActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    public final static String EXTRA_USERNAME = "com.excilys.android.formation.myapp.USERNAME";
    public final static String EXTRA_PASSWORD = "com.excilys.android.formation.myapp.PASSWORD";

    private final String TAG = ParlezVousActivity.class.getSimpleName();
    private EditText usernameField;
    private EditText passwordField;

    SharedPreferences settings = null;


    String user = null;
    String pass = null;

    boolean silentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parlez_vous);
        // Restore preferences
        settings = getPreferences(MODE_PRIVATE);
        this.silentMode = settings.getBoolean("silentMode", false);
        this.user = settings.getString(EXTRA_USERNAME, "Name");
        this.pass = settings.getString(EXTRA_PASSWORD, "");
        this.usernameField = (EditText) findViewById(R.id.editTextName);
        this.passwordField = (EditText) findViewById(R.id.editTextPassword);
        this.usernameField.setText(user);
        this.passwordField.setText(pass);
    }

    public void Send(View v) {
        boolean isValid = isValid();
        if (isValid) {
            user = this.usernameField.getText().toString();
            pass = this.passwordField.getText().toString();
            ParlezVousTask pvt = new ParlezVousTask(this);
            pvt.execute(user, pass);
            Intent intent = new Intent(this, NavActivity.class);
            intent.putExtra(EXTRA_USERNAME, user);
            intent.putExtra(EXTRA_PASSWORD, pass);
            startActivity(intent);
        } else {
            String s = "Please fill all the fields!";
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void Clear(View v) {
        this.user = settings.getString("username", "Name");
        this.pass = settings.getString("password", "");
        this.usernameField = (EditText) findViewById(R.id.editTextName);
        this.passwordField = (EditText) findViewById(R.id.editTextPassword);
        this.usernameField.setText(user);
        this.passwordField.setText(pass);
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

    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("silentMode", this.silentMode);
        editor.putString(EXTRA_USERNAME, user);
        editor.putString(EXTRA_PASSWORD, pass);
        // Commit the edits!
        editor.commit();
    }
}
