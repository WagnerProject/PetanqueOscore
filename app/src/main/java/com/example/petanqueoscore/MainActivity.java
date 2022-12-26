package com.example.petanqueoscore;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView errorConnectAccountTextView;
    private EditText UsernameEditText;
    private EditText PasswordEditText;

    private Button ConnectBtn;
    private TextView CreateAccountBtn;

    private String username;
    private String password;
    private DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorConnectAccountTextView = findViewById(R.id.errorConnectAccountTextView);
        UsernameEditText = findViewById(R.id.UsernameEditText);
        PasswordEditText = findViewById(R.id.PasswordEditText);
        ConnectBtn = findViewById(R.id.ConnectBtn);
        CreateAccountBtn = findViewById(R.id.CreateAccountBtn);

        dataBaseManager = new DataBaseManager(getApplicationContext());

        ConnectBtn.setOnClickListener(view -> {
            username = UsernameEditText.getText().toString();
            password = PasswordEditText.getText().toString();

            connectUser();
            // LANCER LA REQUETE POUR CONNECTER L UTILISATEUR
        });

        CreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CreateAccountActivity = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(CreateAccountActivity);
                finish();
            }
        });
    }

    public void onApiResponse(JSONObject response) {
        Boolean success = null;
        String error = "";

        try {
            success = response.getBoolean("success");

            if (success==true) {
                Intent InterfaceActivity = new Intent(getApplicationContext(), com.example.petanqueoscore.InterfaceActivity.class);
                InterfaceActivity.putExtra("username", username);
                startActivity(InterfaceActivity);
                finish();

            } else {
                error = response.getString("error");
                errorConnectAccountTextView.setVisibility(View.VISIBLE);
                errorConnectAccountTextView.setText(error);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void connectUser(){
        // rejoindre le serveur FTP de bastien pour se connecter a connectuser.php
        String url  = "https://bastienforestier.fr/paul/actions/connectUser.php";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                onApiResponse(response);
                Toast.makeText(getApplicationContext(),"CONNEXION REUSSI", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        dataBaseManager.queue.add(jsonObjectRequest);

    }
}