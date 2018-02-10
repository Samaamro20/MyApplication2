package com.example.samaamro.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final TextView mTextView = (TextView) findViewById(R.id.text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        String sounds ="http://samakamal.esy.es/viewsounds.php";
        List<String> allSounds = new ArrayList<String>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, sounds,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String sound=response.toString();
                        JSONArray cast = response.getJSONArray(0);


                        mTextView.setText(response);
//                        Log.d("mutee",response);
                        // Do something with the response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);


        for (int i=0; i<cast.length(); i++) {
            JSONObject actor = cast.getJSONObject(i);
            String name = actor.getString("name");
            allNames.add(name);
        }
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, sounds,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        mTextView.setText("Response is: "+ response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
//            }
//        });
//        queue.add(stringRequest);
    }
}
