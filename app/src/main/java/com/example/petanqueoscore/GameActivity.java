package com.example.petanqueoscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private Button gamebtn;
    Spinner Spinnerpartenaire;
    ArrayList<String> Spinnerlist = new ArrayList<>();
    ArrayAdapter<String> Partadapter;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        requestQueue = Volley.newRequestQueue(this);
        Spinnerpartenaire = findViewById(R.id.Spinnerpartenaire);
        String url = "https://bastienforestier.fr/paul/actions/Playeringame.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            try {
                JSONArray jsonArray = response.getJSONArray("users");
                for(int i = 0; i<jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nom = jsonObject.optString("username");
                    Spinnerlist.add(nom);
                    Partadapter = new ArrayAdapter<>(GameActivity.this, android.R.layout.simple_spinner_dropdown_item);
                    Spinnerpartenaire.setAdapter(Partadapter);
                }
                } catch (JSONException e) {
                e.printStackTrace();
            }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}