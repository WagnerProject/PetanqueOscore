package com.example.petanqueoscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    private TextView alreadyHasAccountBtn;
    private TextView errorCreateAccountTextView;
    private Button createAccountBtn;

    private EditText UsernameEditText;
    private EditText PasswordEditText;

    private String username;
    private String password;
    private  DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        alreadyHasAccountBtn = findViewById(R.id.AlreadyHasAccountBtn);
        errorCreateAccountTextView = findViewById(R.id.errorCreateAccountTextView);
        dataBaseManager = new DataBaseManager(getApplicationContext());
        createAccountBtn = findViewById(R.id.CreateAccountBtn);
        UsernameEditText = findViewById(R.id.CreateUsernameEditText);
        PasswordEditText = findViewById(R.id.CreatePasswordEditText);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = UsernameEditText.getText().toString();
                password = PasswordEditText.getText().toString();

                createAccount();
                // LANCER LA REQUETE POUR INSCRIRE L UTILISATEUR
            }
        });

        alreadyHasAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConnectToAccountActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ConnectToAccountActivity);
            }
        });
    }

    public void onApiResponse(JSONObject response){
        Boolean success = null;
        String error = "";

        try {

            success = response.getBoolean("success");

            if (success == true) {
                Intent InterfaceActivity = new Intent(getApplicationContext(), com.example.petanqueoscore.InterfaceActivity.class);
                InterfaceActivity.putExtra("username", username);
                startActivity(InterfaceActivity);
                finish();

            } else {
                error = response.getString("error");
                errorCreateAccountTextView.setVisibility(View.VISIBLE);
                errorCreateAccountTextView.setText(error);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
        public void createAccount() {
            String url  = "https://bastienforestier.fr/paul/actions/createAccount.php";

            Map<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);
            JSONObject parameters = new JSONObject(params);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    onApiResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

            dataBaseManager.queue.add(jsonObjectRequest);

        }

    }
