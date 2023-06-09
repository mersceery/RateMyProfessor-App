package com.example.chatappv2.profDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.MainActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.mainMenu.MainMenu;

public class ProfDetailsActivity extends AppCompatActivity {
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;

    private ImageView editProfileButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_details);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
        //start navBar
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish(); // close the loginActivity properly
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                finish(); // close the loginActivity properly
            }
        });

        login1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish(); // close the loginActivity properly
            }
        });

    editProfileButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
                finish(); // close the loginActivity properly
            }
      });
        //end navBar

    }
}