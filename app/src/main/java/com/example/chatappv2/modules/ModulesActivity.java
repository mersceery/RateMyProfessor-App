package com.example.chatappv2.modules;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.allProfs.AllProfsActivity;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.mainMenu.MainMenu;

import java.util.ArrayList;

public class ModulesActivity extends AppCompatActivity implements  ModuleRecViewInterface{

    RecyclerView recyclerView;
    ModulesAdapter modulesAdapter;
    ArrayList<Module> moduleArrayList;

    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;

    private ImageView bckButton;
    private SearchView searchView;
    ArrayList<Module> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules);
        recyclerView = findViewById(R.id.moduleRecView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        moduleArrayList = new ArrayList<Module>();
        modulesAdapter = new ModulesAdapter(ModulesActivity.this, moduleArrayList, this);
        Module module1 = new Module("AUD");
        Module module5 = new Module("TGI");
        Module module3 = new Module("MATHE 1");
        Module module4 = new Module("PG 1");
        Module module2 = new Module("ITS");
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
                Intent intent = new Intent(getApplicationContext(), userlist.class);
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

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus(); //avoid edit text in lower level API jaga" doang remove the cursor einfach
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileList(newText);
                return false;
            }
        });

    }

    private void fileList(String text) {
        filteredList.clear();
        for(Module modules : moduleArrayList){
            if(modules.getModuleName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(modules);
            }
        }

        if(filteredList.isEmpty()){
            modulesAdapter.setFilteredList(moduleArrayList);
        }else{
            modulesAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), AllProfsActivity.class);
        if(!filteredList.isEmpty()) {
            intent.putExtra("ModuleName", filteredList.get(position).getModuleName());
        }
        else{
            intent.putExtra("ModuleName", moduleArrayList.get(position).getModuleName());
        }
        startActivity(intent);
    }
}