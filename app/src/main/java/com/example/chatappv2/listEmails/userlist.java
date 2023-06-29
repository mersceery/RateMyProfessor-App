package com.example.chatappv2.listEmails;

import androidx.annotation.NonNull;
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
import com.example.chatappv2.mainMenu.MainMenu;
import com.example.chatappv2.sendEmail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;

    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;
    private ImageView btnBack;
    private SearchView searchView;

    ArrayList<User> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emails);
        btnBack = findViewById(R.id.backBtn);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
        recyclerView = findViewById(R.id.emailList);
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatappv2-cbaed-default-rtdb.firebaseio.com/");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list, this);
        recyclerView.setAdapter(myAdapter);
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

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if (dataSnapshot.getKey().equals("users")) {
                            String getEmail = child.child("email").getValue(String.class);
                            String getName = child.child("name").getValue(String.class);
                            String getRole = child.child("role").getValue(String.class);
                            String getProfilePicUrl = child.child("profile_pic").getValue(String.class);


                            if (getEmail == null || getEmail.isEmpty()) {
                                getEmail = "N/A";
                            }
                            if (getName == null || getName.isEmpty()) {
                                getName = "N/A";
                            }
                            if (getRole == null || getRole.isEmpty()) {
                                getRole = "N/A";
                            }

                            User user = new User(getEmail, getName, getRole, getProfilePicUrl);
                            list.add(user);
                            filteredList.add(user);
                        }

                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        for(User users : list){
            if(users.getFirstName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(users);
            }
            else if(users.getLastName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(users);
            }
            else if(users.getAge().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(users);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();

        }else{
            myAdapter.setFilteredList(filteredList);
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(userlist.this, sendEmail.class);

        intent.putExtra("EMAIL", filteredList.get(position).getFirstName());
        intent.putExtra("NAME", filteredList.get(position).getLastName());
        startActivity(intent);
    }
}