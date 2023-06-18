package com.example.chatappv2.allProfs;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.Utils;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.mainMenu.MainMenu;
import com.example.chatappv2.profDetails.ProfDetailsActivity;

import java.util.ArrayList;

public class AllProfsActivity extends AppCompatActivity implements RecViewProfInterface{
    ArrayList<Professor> filteredList = new ArrayList<>();
    private RecyclerView profsRecView;
    private ProfRecViewAdapter adapter;
    String modulePosition;
    private SearchView searchView;
    private ImageView btnBack;
    private ArrayList<Professor> allProfs;
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_profs);
        allProfs = new ArrayList<Professor>();
        modulePosition = getIntent().getStringExtra("ModuleName");
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);

        profsRecView = findViewById(R.id.profsRecView);
        btnBack = findViewById(R.id.backBtn);
        adapter = new ProfRecViewAdapter(this, "allProfs", this);
        profsRecView.setAdapter(adapter);
        profsRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setProfs(Utils.getInstance(this).getAllProfs(modulePosition));
        allProfs = Utils.getInstance(this).getAllProfs(modulePosition);
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
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void fileList(String text) {
        filteredList.clear();
        for(Professor profs : allProfs){
            if(profs.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(profs);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

        }else{
            adapter.setFilteredList(filteredList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(AllProfsActivity.this, ProfDetailsActivity.class);
        if(!filteredList.isEmpty()) {
            intent.putExtra("NAME", filteredList.get(position).getName());
            intent.putExtra("SHORT_DESC", filteredList.get(position).getShortDesc());
            intent.putExtra("IMAGE_URL", filteredList.get(position).getImageUrl());
        }
        else{
            intent.putExtra("NAME", allProfs.get(position).getName());
            intent.putExtra("SHORT_DESC", allProfs.get(position).getShortDesc());
            intent.putExtra("IMAGE_URL", allProfs.get(position).getImageUrl());

        }
        startActivity(intent);

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case android.R.id.home:
//                onBackPressed();
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
