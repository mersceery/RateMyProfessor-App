package com.example.chatappv2.mainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.chatappv2.R;

public class MainMenu extends AppCompatActivity {

    private ImageButton loginButton;
    private Button viewAllButton;
    private ImageButton rateProfessorButton;
    private ImageButton chatButton;
    private ImageButton homeButton;
    private ImageButton login1Button;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Find views by their IDs
        loginButton = findViewById(R.id.login_button);
        viewAllButton = findViewById(R.id.view_all_button);
        rateProfessorButton = findViewById(R.id.rate_professor_button);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        settingsButton = findViewById(R.id.settings_button);

        // Set click listeners for the buttons
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login button functionality
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform view all button functionality
            }
        });

        rateProfessorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform rate professor button functionality
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform chat button functionality
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform home button functionality
            }
        });

        login1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login1 button functionality
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform settings button functionality
            }
        });
    }
}
