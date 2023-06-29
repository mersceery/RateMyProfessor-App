package com.example.chatappv2.fragebogen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chatappv2.EditProfile;
import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;
import com.example.chatappv2.listEmails.userlist;
import com.example.chatappv2.mainMenu.MainMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class FrageBogenActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";
    FirebaseUser user;
    FirebaseAuth auth;
    DatabaseReference db;
    ArrayList<Comment> commentArrayList;
    PostAdapter postAdapter;
    RecyclerView recyclerView;
    ImageView addCommentBtn;
    EditText editTextComment;

    private Spinner moduleSpinner;
    private Spinner profSpinner;

    private TextView selectModuleTxt;
    private TextView selectProfTxt;
    private TextView klausurTxt;
    private TextView vorlesungTxt;
    private TextView praktikumTxt;
    private TextView commentTxt;

    private RatingBar klausurRating;
    private RatingBar vorlesungRating;
    private RatingBar praktikumRating;
    private ImageView chatButton;
    private ImageView homeButton;
    private ImageView login1Button;

    private float klausurRatingValue;
    private float vorlesungRatingValue;
    private float praktikumRatingValue;

    private ImageView editProfileButton2;

    private EditText addCommentEditTxt;
    private Button addRatingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frage_bogen);

        selectModuleTxt = findViewById(R.id.selectModuleTextView);
        selectProfTxt = findViewById(R.id.selectProfessorTextView);
        klausurTxt = findViewById(R.id.klausurTextView);
        vorlesungTxt = findViewById(R.id.vorlesungTextView);
        praktikumTxt = findViewById(R.id.praktikumTextView);
        commentTxt = findViewById(R.id.commentTextView);

        klausurRating = findViewById(R.id.klausurRatingBar);
        vorlesungRating = findViewById(R.id.vorlesungRatingBar);
        praktikumRating = findViewById(R.id.praktikumRatingBar);
        klausurRating.setIsIndicator(false);
        vorlesungRating.setIsIndicator(false);
        praktikumRating.setIsIndicator(false);

        addCommentEditTxt = findViewById(R.id.commentEditText);
        addRatingBtn = findViewById(R.id.addRatingButton);

        moduleSpinner = findViewById(R.id.moduleSpinner);

        String[] items = new String[]{"PG1", "AuD", "TGI", "MATHE1", "ITS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moduleSpinner.setAdapter(adapter);
        chatButton = findViewById(R.id.chat_button);
        homeButton = findViewById(R.id.home_button);
        login1Button = findViewById(R.id.login1_button);
        editProfileButton2 = findViewById(R.id.settings_button);
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


        profSpinner = findViewById(R.id.professorSpinner);

        String[] itemsProfPG1 = new String[]{"Skroch"};
        String[] itemsProfAuD = new String[]{"Jung", "Alternbend"};
        String[] itemsProfTGI = new String[]{"Maier"};
        String[] itemsProfMATHE1 = new String[]{"Hechler", "Romana Piat", "Pepe the Frog"};
        String[] itemsProfITS = new String[]{"Rathgeb"};

        String selectedProfessor = getIntent().getStringExtra("SELECTED_PROFESSOR");
        String selectedModule2 = getIntent().getStringExtra("SELECTED_MODULE");


        int selectionPosition = adapter.getPosition(selectedModule2);
        moduleSpinner.setSelection(selectionPosition);

        moduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String moduleSpinnerString = moduleSpinner.getSelectedItem().toString();
                switch (moduleSpinnerString) {
                    case "PG1": {
                        professorSelector(itemsProfPG1, selectedProfessor);
                        break;
                    }
                    case "AuD": {
                        professorSelector(itemsProfAuD, selectedProfessor);
                        break;
                    }
                    case "TGI": {
                        professorSelector(itemsProfTGI, selectedProfessor);
                        break;
                    }
                    case "MATHE1": {
                        professorSelector(itemsProfMATHE1, selectedProfessor);
                        break;
                    }
                    case "ITS": {
                        professorSelector(itemsProfITS, selectedProfessor);
                        break;
                    }
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedModule = moduleSpinner.getSelectedItem().toString();
                String selectedProf = profSpinner.getSelectedItem().toString();
                float klausurRatingValue = klausurRating.getRating();
                float vorlesungRatingValue = vorlesungRating.getRating();
                float praktikumRatingValue = praktikumRating.getRating();
                String comment = addCommentEditTxt.getText().toString();

                // Save the ratings and comment to Firebase
                saveRatingsAndComment(selectedModule, selectedProf, klausurRatingValue, vorlesungRatingValue, praktikumRatingValue, comment);
            }
        });
    }

    private void professorSelector(String[] items, String selectedProfessor) {
        ArrayAdapter<String> adapterProf = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapterProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profSpinner.setAdapter(adapterProf);

        int selectedProfessorPosition = adapterProf.getPosition(selectedProfessor);
        profSpinner.setSelection(selectedProfessorPosition);
    }

    private void saveRatingsAndComment(String selectedModule, String selectedProf, float klausurRatingValue,
                                       float vorlesungRatingValue, float praktikumRatingValue, String comment) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference().child("Ratings");

        if (user != null) {
            if (!comment.isEmpty()) {
                String userId = user.getUid();
                DatabaseReference moduleRatingsRef = db.child(selectedModule);
                DatabaseReference professorRatingsRef = moduleRatingsRef.child(selectedProf);

                // Create a new unique key for each rating entry
                String ratingId = professorRatingsRef.push().getKey();

                // Save the ratings
                DatabaseReference ratingsRef = professorRatingsRef.child(ratingId).child("Ratings");
                ratingsRef.child("Klausur").setValue(klausurRatingValue);
                ratingsRef.child("Vorlesung").setValue(vorlesungRatingValue);
                ratingsRef.child("Praktikum").setValue(praktikumRatingValue);

                // Save the comment
                DatabaseReference commentRef = professorRatingsRef.child(ratingId).child("Comment");
                commentRef.setValue(comment);
                Toast.makeText(FrageBogenActivity.this, "Ratings and comment saved successfully", Toast.LENGTH_SHORT).show();
            }
            else if(comment.isEmpty()){
                Toast.makeText(FrageBogenActivity.this, "Comment section cannot be empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(FrageBogenActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }



}

