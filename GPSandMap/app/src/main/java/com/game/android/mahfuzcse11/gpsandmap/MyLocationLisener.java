package com.game.android.mahfuzcse11.gpsandmap;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MahfuzCSE'11 on 23-Jun-17.
 */

public class MyLocationLisener implements LocationListener {


    Context context;

    public MyLocationLisener(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {

        String locationString = "Logititute : " + String.valueOf(location.getLongitude()) + "Latitute : " + String.valueOf(location.getLatitude());

        Toast.makeText(context, locationString, Toast.LENGTH_LONG).show();


        String url = "http://10.0.2.2/android2/tracking.php?id=5555&log='" + String.valueOf(location.getLongitude()) + "'&lat='" + String.valueOf(location.getLatitude()) + "'";


        new MyAsyncTaskgetNews().execute(url);


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(context, "GPS  status is Changed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(context, "GPS is Enabled", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "GPS is Disabled", Toast.LENGTH_LONG).show();
    }


    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000);//set timeout to 5 seconds

                try {
                    //getting the response data
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    NewsData = ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData);
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            } catch (Exception ex) {
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {

            try {
                Toast.makeText(context, progress[0], Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
            }


        }

        protected void onPostExecute(String result2) {


        }


    }

    // this method convert any stream to string
    public static String ConvertInputToStringNoChange(InputStream inputStream) {

        BufferedReader bureader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String linereultcal = "";

        try {
            while ((line = bureader.readLine()) != null) {

                linereultcal += line;

            }
            inputStream.close();


        } catch (Exception ex) {
        }

        return linereultcal;
    }
}
