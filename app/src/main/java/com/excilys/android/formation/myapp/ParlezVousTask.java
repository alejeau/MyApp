package com.excilys.android.formation.myapp;

import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Thread.sleep;

public class ParlezVousTask extends android.os.AsyncTask<String, Integer, Long> {
    protected ParlezVousActivity activity;
    protected ProgressBar loadingWheel;
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
            new URL("http://www.google.com/");
        } catch (Exception e) {

        }
        HttpURLConnection urlConnection = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (Exception e) {

        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            ReadStream(in);
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
}
