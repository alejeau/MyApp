package com.excilys.android.formation.myapp;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ParlezVousTask extends android.os.AsyncTask<String, Integer, Long> {
    protected ParlezVousActivity activity;
    protected ProgressBar loadingWheel;
    String result = "";
    ParlezVousTask(ParlezVousActivity activity){
        this.activity = activity;
        loadingWheel = (ProgressBar) this.activity.findViewById(R.id.loadingWheel);
    }

    @Override
    protected void onPreExecute() {
        this.loadingWheel.setVisibility(View.VISIBLE);
    }

    @Override
    protected Long doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://www.google.com/");
//            url = new URL("http://[host]:[port]/connect/" + params[0] + "/" + params[1] + "");
        } catch (Exception e) {

        }
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {

        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String res = InputStreamToString.convert(in);
            this.result = res;
        } catch (Exception e) {
        } finally {
            urlConnection.disconnect();
        }

        return 1L;
    }

    @Override
    protected void onPostExecute(Long result) {
        this.loadingWheel.setVisibility(View.GONE);
    }

    public String getResult(){
        return  this.result;
    }
}
