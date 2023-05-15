package com.example.chatappv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatappv2.allProfs.AllProfsActivity;
import com.example.chatappv2.professorDetails.PostDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomescreenActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    private ImageView imgChat, imgPeople, btnPolymer;
    private CircleImageView imgProfileHomescreen;
    private TextView txtViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        String mobile = getIntent().getStringExtra("mobile");
        String email = getIntent().getStringExtra("email");


        imgProfileHomescreen = findViewById(R.id.imgProfileHomescreen);
        imgPeople = findViewById(R.id.imgPeople);
        txtViewAll = findViewById(R.id.txtViewAllProfs);
        btnPolymer = findViewById(R.id.btnPolymerr);
        btnPolymer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        imgPeople.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomescreenActivity.this, "See All Clicked!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), PostDetailActivity.class);
//                startActivity(intent);
//                //finish(); // close the loginActivity properly
//            }
//        });

        txtViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomescreenActivity.this, "View All Clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AllProfsActivity.class);
                startActivity(intent);
                //finish(); // close the loginActivity properly
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference();
        String path = "users/" + mobile +"/profile_pic";
        databaseReference.child(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get the profile_pic value as a String
                String profilePicUrl = dataSnapshot.getValue(String.class);
                Picasso.get().load(profilePicUrl).into(imgProfileHomescreen);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        imgChat = findViewById(R.id.imgChat);
        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomescreenActivity.this, "ChatApp Clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //finish(); // close the loginActivity properly
            }
        });

        recyclerViewList = findViewById(R.id.recViewHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        ArrayList<ListDomain> articles = new ArrayList<>();
        articles.add(new ListDomain("Prof 1", "andre_tate"));
        articles.add(new ListDomain("Prof 2", "face_palm"));
        articles.add(new ListDomain("Prof 3", "prof_thumbsup"));
        articles.add(new ListDomain("Prof 4", "spiderpepe"));
        articles.add(new ListDomain("Prof 5", "ben_affleck_sadge"));

        adapter = new DashboardAdapter(articles);
        recyclerViewList.setAdapter(adapter);
    }
}