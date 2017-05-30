package com.gitwatch.gitwatch.infrastructure.github.Services;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.gitwatch.gitwatch.infrastructure.github.Helpers.AlertHelper;
import com.gitwatch.gitwatch.infrastructure.github.Helpers.NetworkStateHelper;

import org.apache.commons.io.IOUtils;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AsyncJsonTask extends AsyncTask<String, Void, String> {

    private static String TAG = "Lade";

    @Override
    protected String doInBackground(String... params) {
        String msg = "";
        try{
            URL url = new URL(params[0]);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            int code = conn.getResponseCode();
            msg = IOUtils.toString(conn.getInputStream());
            Log.i(TAG, Integer.toString(code));
        } catch (Exception e) {
            Log.v(TAG, e.toString());
        }
        return  msg;
    }
}
