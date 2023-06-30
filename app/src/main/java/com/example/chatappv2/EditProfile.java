package com.example.chatappv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chatappv2.listEmails.CircleTransform;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.mainMenu.MainMenu;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextName;
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;
    private ImageView editProfileButton2;

    private ImageView bckButton;

    private Button logoutBtn;
    private Button saveChangesBtn;

    private Button changeProfilePicBtn;

    private ImageView profilePic;

    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        logoutBtn = findViewById(R.id.btnLogout2);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
        bckButton = findViewById(R.id.backBtn);
        editTextEmail = findViewById(R.id.EditEmailEditTxt);
        editTextName = findViewById(R.id.editNameEditTxt);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        changeProfilePicBtn = findViewById(R.id.changeProfilePicBtn);
        profilePic = findViewById(R.id.profilePictureinEditProfile);
        changeProfilePicBtn = findViewById(R.id.changeProfilePicBtn);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        checkLoggedInUser(currentUser);

        if(currentUser != null) {
            String userEmail = currentUser.getEmail();
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
            usersRef.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        DataSnapshot snapshot = dataSnapshot.getChildren().iterator().next();
                        String userKey = snapshot.getKey();
                        String name = snapshot.child("name").getValue(String.class);
                        String profilePicUrl = snapshot.child("profile_pic").getValue(String.class);
                        if(profilePicUrl.isEmpty()){
                            Picasso.get().load(R.drawable.defaultprofilepicture).transform(new CircleTransform()).into(profilePic);
                        } else {
                            Picasso.get().load(profilePicUrl).transform(new CircleTransform()).placeholder(R.drawable.defaultprofilepicture).into(profilePic);
                        }
                        editTextName.setText(name);
                        editTextEmail.setText(userEmail);
                        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String newEmail = editTextEmail.getText().toString().trim();
                                String newName = editTextName.getText().toString().trim();
                                usersRef.child(userKey).child("email").setValue(newEmail);
                                usersRef.child(userKey).child("name").setValue(newName);
                                currentUser.updateEmail(newEmail);
                                /* updateEmail() sometimes doesn't work as expected because as documentation says: To set a user's email address,
                                the user must have signed in recently. So make sure to log out and then login again when you first start the app
                                with an already signed in user or else you might mess up the database (email is updated in realtime database but not in
                                firebase auth)*/
                                Toast.makeText(EditProfile.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        }

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
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                //  finish();
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

        changeProfilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
            }
        });
    }

    private void checkLoggedInUser(FirebaseUser currentUser) {
        if (currentUser == null) {
            // User not logged in, show pop-up message
            Toast.makeText(EditProfile.this, "You need to be logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditProfile.this, MainMenu.class);
            startActivity(intent);
            // User logged in, proceed to EditProfile activity
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            Picasso.get().load(imageUri).transform(new CircleTransform()).placeholder(R.drawable.defaultprofilepicture).into(profilePic);
            uploadImageToFirebaseStorageAndDatabase(imageUri);
        }
    }
    private void uploadImageToFirebaseStorageAndDatabase(Uri imageUri) {
        // Create a reference to the Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the current user's email
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // Generate a unique filename for the image
        String filename = userEmail + "_" + System.currentTimeMillis();

        // Create a reference to the image file in Firebase Storage
        StorageReference imageRef = storageRef.child("profile_pics").child(filename);

        // Upload the image to Firebase Storage
        imageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully
                    // Get the download URL of the uploaded image
                    imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // Save the download URL to Firebase Realtime Database
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
                        usersRef.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        String imageUserKey = snapshot.getKey();
                                        usersRef.child(imageUserKey).child("profile_pic").setValue(uri.toString());
                                        Toast.makeText(EditProfile.this, "Profile picture updated successfully", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // User not found
                                    Toast.makeText(EditProfile.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Handle any errors
                                Toast.makeText(EditProfile.this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }).addOnFailureListener(e -> {
                        // Failed to get the download URL
                        Toast.makeText(EditProfile.this, "Failed to upload profile picture", Toast.LENGTH_SHORT).show();
                    });
                })
                .addOnFailureListener(e -> {
                    // Failed to upload the image
                    Toast.makeText(EditProfile.this, "Failed to upload profile picture", Toast.LENGTH_SHORT).show();
                });
    }


}