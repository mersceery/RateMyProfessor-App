package com.example.chatappv2.modules;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.MainActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.mainMenu.MainMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ModulesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ModulesAdapter modulesAdapter;
    ArrayList<Module> moduleArrayList;

    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;

    private ImageView bckButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        recyclerView = findViewById(R.id.moduleRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moduleArrayList = new ArrayList<Module>();
        modulesAdapter = new ModulesAdapter(ModulesActivity.this, moduleArrayList);
        Module module1 = new Module("Aud");
        Module module2 = new Module("TGI");
        Module module3 = new Module("Mathe 1");
        Module module4 = new Module("PG 1");
        Module module5 = new Module("ITS");
        moduleArrayList.add(module1);
        moduleArrayList.add(module2);
        moduleArrayList.add(module3);
        moduleArrayList.add(module4);
        moduleArrayList.add(module5);
        recyclerView.setAdapter(modulesAdapter);


        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
        bckButton = findViewById(R.id.backBtn);

        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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

    }
}