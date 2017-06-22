package com.game.android.mahfuzcse11.jsontutorials;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String jsonString = "{'info':{'name':'Mahfuz','age':20},jobs:[{'id':'1','title':'Java','Description':'this is java post'},{'id':'2','title':'C#','Description':'this is C# post'},{'id':'1','title':'Python','Description':'this is Python post'}]}";


        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            JSONObject jsonInfo = jsonObject.getJSONObject("info");

            String name = jsonInfo.getString("name");
            int age = jsonInfo.getInt("age");


            JSONArray jsonArrayJobs = jsonObject.getJSONArray("jobs");


            for (int i = 0; i < jsonArrayJobs.length(); i++) {

                JSONObject jobs = jsonArrayJobs.getJSONObject(i);

                int id = jobs.getInt("id");
                String title = jobs.getString("title");
                String description = jobs.getString("Description");

            }


        } catch (JSONException e) {

        }


    }
}
