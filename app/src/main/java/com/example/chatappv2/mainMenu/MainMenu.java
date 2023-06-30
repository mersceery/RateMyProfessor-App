package com.example.chatappv2.mainMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.allProfs.AllProfsActivity;
import com.example.chatappv2.fragebogen.FrageBogenActivity;
import com.example.chatappv2.listEmails.CircleTransform;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.modules.ModulesActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainMenu extends AppCompatActivity {

    private ImageView editProfileImg;
    private Button viewAllButton;
    private ImageView rateProfessorButton;
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;

    private ImageView professor1;
    private ImageView professor2;
    private ImageView professor3;

    private TextView professor1txt;
    private TextView professor2txt;
    private TextView professor3txt;
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    private TextView welcomeTxt;

    private ImageView modul1;
    private ImageView modul2;
    private ImageView modul3;
    private ImageView modul4;
    private ImageView modul5;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        welcomeTxt = findViewById(R.id.greeting_textview);
        editProfileImg = findViewById(R.id.editProfileImg);

        if(currentUser != null){
            databaseReference =FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatappv2-cbaed-default-rtdb.firebaseio.com/");
            user = FirebaseAuth.getInstance().getCurrentUser();
            uid = user.getUid();
            String userEmail = user.getEmail();
            String teacherRole = "Teacher";
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    outerloop:{
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                if (dataSnapshot.getKey().equals("users")) {
                                    String getEmail = child.child("email").getValue(String.class);
                                    String getName = child.child("name").getValue(String.class);
                                    String getRole = child.child("role").getValue(String.class);
                                    String getImgUrl = child.child("profile_pic").getValue(String.class);

                                    if(getImgUrl.isEmpty()){
                                        Picasso.get().load(R.drawable.defaultprofilepicture).into(editProfileImg);
                                    } else {
                                        Picasso.get().load(getImgUrl).transform(new CircleTransform()).placeholder(R.drawable.defaultprofilepicture).into(editProfileImg);
                                    }

                                    if(teacherRole.equals(getRole)){
                                        rateProfessorButton.setVisibility(View.GONE);
                                    } else{
                                        rateProfessorButton.setVisibility(View.VISIBLE);
                                    }
                                    if (getEmail == null || getEmail.isEmpty()) {
                                        getEmail = "N/A";
                                    }
                                    if (getName == null || getName.isEmpty()) {
                                        getName = "N/A";
                                    }
                                    if (userEmail.equals(getEmail)) {
                                        welcomeTxt.setText("Welcome " + getName);
                                        break outerloop;
                                    }else{
                                        welcomeTxt.setText("Welcome Guest");
                                    }

                                }

                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            welcomeTxt.setText("Welcome Guest");
        }

        // Find views by their IDs
        viewAllButton = findViewById(R.id.view_all_button);
        rateProfessorButton = findViewById(R.id.rate_professor_button);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.editProfile_button2);
        professor1 = findViewById(R.id.professor1);
        professor2 = findViewById(R.id.professor2);
        professor3 = findViewById(R.id.professor3);

        professor1txt = findViewById(R.id.professor1name);
        professor2txt = findViewById(R.id.professor2name);
        professor3txt = findViewById(R.id.professor3name);

        modul1 = findViewById(R.id.module1);
        modul2 = findViewById(R.id.module2);
        modul3 = findViewById(R.id.module3);
        modul4 = findViewById(R.id.module4);
        modul5 = findViewById(R.id.module5);


        // Set click listeners for the buttons
        modul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AllProfsActivity.class);
                intent.putExtra("ModuleName", "AUD");
                MainMenu.this.startActivity(intent);
            }
        });
        modul2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AllProfsActivity.class);
                intent.putExtra("ModuleName", "ITS");
                MainMenu.this.startActivity(intent);
            }
        });

        modul3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AllProfsActivity.class);
                intent.putExtra("ModuleName", "MATHE 1");
                MainMenu.this.startActivity(intent);
            }
        });
        modul4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AllProfsActivity.class);
                intent.putExtra("ModuleName", "PG 1");
                MainMenu.this.startActivity(intent);
            }
        });
        modul5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, AllProfsActivity.class);
                intent.putExtra("ModuleName", "TGI");
                MainMenu.this.startActivity(intent);
            }
        });
        editProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                startActivity(intent);
                 // close the loginActivity properly
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ModulesActivity.class);
                startActivity(intent);
                 // close the loginActivity properly
            }
        });

        rateProfessorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null) {
                    // User is not logged in, display toast message
                    Toast.makeText(getApplicationContext(), "You need to be logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), FrageBogenActivity.class);
                    startActivity(intent);
                    // close the loginActivity properly
                }
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), userlist.class);
                startActivity(intent);
                 // close the loginActivity properly
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                 // close the loginActivity properly
            }
        });

        login1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                 // close the loginActivity properly
            }
        });

        editProfileButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null) {
                    // User is not logged in, display toast message
                    Toast.makeText(getApplicationContext(), "You need to be logged in", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                    startActivity(intent);
                    // close the loginActivity properly
                }
            }
        });
        retrieveAndProcessTopRatings();
    }
    private void retrieveAndProcessTopRatings() {
        DatabaseReference ratingsRef = FirebaseDatabase.getInstance().getReference().child("Ratings");

        ratingsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Float> professorRatings = new HashMap<>();

                // Iterate through all modules
                for (DataSnapshot moduleSnapshot : dataSnapshot.getChildren()) {
                    String module = moduleSnapshot.getKey();

                    // Iterate through all professors within the module
                    for (DataSnapshot professorSnapshot : moduleSnapshot.getChildren()) {
                        String professor = professorSnapshot.getKey();
                        float totalRating = 0;
                        int ratingCount = 0;

                        // Iterate through all ratings for the professor
                        for (DataSnapshot ratingSnapshot : professorSnapshot.getChildren()) {
                            String ratingId = ratingSnapshot.getKey();

                            // Retrieve the rating values
                            float klausurRating = ratingSnapshot.child("Ratings").child("Klausur").getValue(Float.class);
                            float vorlesungRating = ratingSnapshot.child("Ratings").child("Vorlesung").getValue(Float.class);
                            float praktikumRating = ratingSnapshot.child("Ratings").child("Praktikum").getValue(Float.class);

                            // Calculate the average rating for the professor
                            float averageRating = (klausurRating + vorlesungRating + praktikumRating) / 3;

                            // Accumulate the total rating and count
                            totalRating += averageRating;
                            ratingCount++;
                        }

                        // Calculate the average rating for the professor
                        float professorAverageRating = totalRating / ratingCount;

                        // Update the professor's rating in the map
                        professorRatings.put(professor, professorAverageRating);
                    }
                }

                // Sort the professors based on their average ratings
                List<Map.Entry<String, Float>> sortedProfessorRatings = new ArrayList<>(professorRatings.entrySet());
                Collections.sort(sortedProfessorRatings, new Comparator<Map.Entry<String, Float>>() {
                    @Override
                    public int compare(Map.Entry<String, Float> entry1, Map.Entry<String, Float> entry2) {
                        // Sort in descending order
                        return Float.compare(entry2.getValue(), entry1.getValue());
                    }
                });

                // Get the top 3 professors with the highest ratings
                List<Map.Entry<String, Float>> top3Professors = sortedProfessorRatings.subList(0, Math.min(3, sortedProfessorRatings.size()));

                // Update the professor1txt, professor2txt, and professor3txt
                if (top3Professors.size() >= 1) {
                    professor1txt.setText(top3Professors.get(0).getKey());
                }

                if (top3Professors.size() >= 2) {
                    professor2txt.setText(top3Professors.get(1).getKey());
                }

                if (top3Professors.size() >= 3) {
                    professor3txt.setText(top3Professors.get(2).getKey());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });

    }
}

