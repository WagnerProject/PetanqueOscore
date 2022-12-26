package com.example.petanqueoscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterfaceActivity extends AppCompatActivity {

    private TextView WelcomeTextView;
    private String username;
    private Button Gamebtn;
    private Button TableauG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        Gamebtn = findViewById(R.id.gamebtn);
        TableauG = findViewById(R.id.ConnectScoreG);
        WelcomeTextView = findViewById(R.id.WelcometextView);

        username = getIntent().getStringExtra("username");

        WelcomeTextView.setText("Bienvenue sur ton profil, "+username+" !");

        TableauG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConnectToAccountActivity = new Intent(getApplicationContext(), TableauScore.class);
                startActivity(ConnectToAccountActivity);
            }
        });

        Gamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConnectToAccountActivity = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(ConnectToAccountActivity);
            }
        });
    }
}