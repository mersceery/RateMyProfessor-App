package com.example.chatappv2.profDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.fragebogen.Comment;
import com.example.chatappv2.fragebogen.PostAdapter;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.mainMenu.MainMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProfDetailsActivity extends AppCompatActivity {
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    PostAdapter postAdapter;
    private TextView klausurText;

    private TextView vorlesungText;

    private TextView praktikumText;
    private TextView profDesctv;
    private TextView profPhonenummbertv;
    private TextView profNametv;
    private TextView profEmailtv;
    private TextView profWebsitetv;
    private ImageView profImageiv;
    private ImageView bckButton;
    RecyclerView commentList;

    ArrayList<Comment> commentArrayList;



    private ImageView editProfileButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_details);
        profNametv = findViewById(R.id.profNameTxt);
        profDesctv = findViewById(R.id.profDetailsTxt);
        profImageiv = findViewById(R.id.profImage);
        profEmailtv = findViewById(R.id.emailTxt);
        profPhonenummbertv = findViewById(R.id.phonenumberTxt);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
        klausurText = findViewById(R.id.klausurRating2);
        vorlesungText = findViewById(R.id.klausurRating);
        praktikumText = findViewById(R.id.klausurRating3);
        commentList = findViewById(R.id.commentList);
        commentList.setLayoutManager(new LinearLayoutManager(this));
        String profName = getIntent().getStringExtra("NAME");
        String profDesc = getIntent().getStringExtra("SHORT_DESC");
        String profImage = getIntent().getStringExtra("IMAGE_URL");
        profNametv.setText(profName);
        profDesctv.setText(profDesc);
        Glide.with(ProfDetailsActivity.this)
                .asBitmap()
                .load(profImage)
                .into(profImageiv);
        commentArrayList = new ArrayList<>();
        postAdapter = new PostAdapter(getApplicationContext(), commentArrayList);
        commentList.setAdapter(postAdapter);
        fetchData(profName);
        bckButton = findViewById(R.id.backBtn);

        bckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //start navBar
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
        //end navBar
    }
    public void fetchData (String professorName) {
        DatabaseReference ratingsRef = FirebaseDatabase.getInstance().getReference()
                .child("Ratings");

        ratingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Data exists for the provided module and professor
                float totalKlausurRating = 0;
                float totalVorlesungRating = 0;
                float totalPraktikumRating = 0;
                int ratingCount = 0;
                for (DataSnapshot ratingSnapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot moduleSnapshot : ratingSnapshot.getChildren()){
                        for (DataSnapshot profSnapShot : moduleSnapshot.getChildren()) {
                            if (moduleSnapshot.getKey().equals(professorName)) {
                                float klausurRating = profSnapShot.child("Ratings").child("Klausur").getValue(Float.class);
                                float vorlesungRating = profSnapShot.child("Ratings").child("Vorlesung").getValue(Float.class);
                                float praktikumRating = profSnapShot.child("Ratings").child("Praktikum").getValue(Float.class);

                                // Accumulate the ratings
                                totalKlausurRating += klausurRating;
                                totalVorlesungRating += vorlesungRating;
                                totalPraktikumRating += praktikumRating;
                                ratingCount++;
                                String comment = profSnapShot.child("Comment").getValue(String.class);
                                String userName = "";
                                Comment commentObject = new Comment(userName, comment);
                                commentArrayList.add(commentObject);
                            }
                        }
                    }

                    // Calculate the average ratings
                    float averageKlausurRating = totalKlausurRating / ratingCount;
                    float averageVorlesungRating = totalVorlesungRating / ratingCount;
                    float averagePraktikumRating = totalPraktikumRating / ratingCount;

                    DecimalFormat decimalFormat = new DecimalFormat("#.#");
                    String formattedKlausurRating = decimalFormat.format(averageKlausurRating);
                    String formattedVorlesungRating = decimalFormat.format(averageVorlesungRating);
                    String formattedPraktikumRating = decimalFormat.format(averagePraktikumRating);

                    klausurText.setText(formattedKlausurRating);
                    vorlesungText.setText(formattedVorlesungRating);
                    praktikumText.setText(formattedPraktikumRating);
                    Log.d("ProfDetailsActivity", "Comments retrieved successfully");
                    for(Comment comment : commentArrayList){
                        Log.d("ProfDetailsActivity",comment.getComment());
                    }
                    postAdapter.notifyDataSetChanged();
                    Log.d("ProfdetailsActivity", "Notifying");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });
    }


}
