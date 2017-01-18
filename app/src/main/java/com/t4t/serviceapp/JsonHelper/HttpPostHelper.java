package com.t4t.serviceapp.JsonHelper;

import android.content.Context;
import android.util.Log;

import com.t4t.serviceapp.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpPostHelper {


    public static String getResponsefromURL(String method, Context context) {
        InputStream is = null;
        String result = "";
        try {
            URL url = new URL(method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            connection.setReadTimeout(100000);

            is = connection.getInputStream();
        } catch (Exception e) {

            Log.e("log 1", "Error in http connection " + e.toString());

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 20);//20=8

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            is.close();

            result = sb.toString();

        } catch (Exception e) {
            Log.e("log 2", "Error converting result " + e.toString());
        }
        return result;
    }

    public static String requestLogin(String method, Context context, String urlParameters) {
        InputStream is = null;
        String result = "";
        try {
            URL url = new URL(context.getString(R.string.SERVER_ADDRESS) + method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

//            connection.setRequestProperty("Content-Length", "" +
//                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setReadTimeout(100000);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            is = connection.getInputStream();
        } catch (Exception e) {
            Log.e("log 1", "Error in http connection " + e.toString());

        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception e) {
            Log.e("log 2", "Error converting result " + e.toString());
        }
        return result;
    }

}
