package com.gitwatch.gitwatch.infrastructure.github.Helpers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bziswn on 23.05.2017.
 */

public class AsyncJsonTask extends AsyncTask<String, Void, String> {

    private static String TAG = "Suche";

    @Override
    protected String doInBackground(String... params) {
        String msg = "";
        try{
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int code = conn.getResponseCode();
            msg = IOUtils.toString(conn.getInputStream());
            Log.i(TAG, Integer.toString(code));
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
        return  msg;
    }
}
