package com.example.chatappv2.allProfs;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.chatappv2.R;
import com.example.chatappv2.Utils;

import java.util.ArrayList;
import java.util.Locale;

public class AllProfsActivity extends AppCompatActivity {

    private RecyclerView profsRecView;
    private ProfRecViewAdapter adapter;

    private SearchView searchView;
    private ImageView btnBack;


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_profs);

        profsRecView = findViewById(R.id.profsRecView);
        btnBack = findViewById(R.id.backBtn);
        adapter = new ProfRecViewAdapter(this, "allProfs");
        profsRecView.setAdapter(adapter);
        profsRecView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setProfs(Utils.getInstance(this).getAllProfs());

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void fileList(String text) {
        ArrayList<Professor> filteredList = new ArrayList<>();
        for(Professor profs : Utils.getInstance(this).getAllProfs()){
            if(profs.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(profs);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

        }else{
            adapter.setFilteredList(filteredList);
        }
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