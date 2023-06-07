package com.example.chatappv2.fragebogen;

import androidx.annotation.NonNull;
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

import com.example.chatappv2.R;
import com.example.chatappv2.modules.ModulesActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        addCommentEditTxt = findViewById(R.id.commentEditText);
        addRatingBtn = findViewById(R.id.addRatingButton);

        moduleSpinner = findViewById(R.id.moduleSpinner);

        String[] items = new String[]{"PG1", "AuD", "TGI", "MATHE1", "ITS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moduleSpinner.setAdapter(adapter);



        profSpinner = findViewById(R.id.professorSpinner);

        String[] itemsProfPG1 = new String[]{"Skorch", "Muller", "Jung", "Budi", "Roth"};
        String[] itemsProfAuD = new String[]{"Jung", "Alternbend", "Puki", "Meki", "Ikem"};
        String[] itemsProfTGI = new String[]{"THE", "GOAT", "MAIER"};
        String[] itemsProfMATHE1 = new String[]{"Hechler", "Piat"};
        String[] itemsProfITS = new String[]{"Rathgeb", "Heinemann", "Templar Assasin", "Invoker", "Meepo"};


        moduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String moduleSpinnerString = moduleSpinner.getSelectedItem().toString();
                switch (moduleSpinnerString) {
                    case "PG1": {
                        professorSelector(itemsProfPG1);
                        break;
                    }
                    case "AuD": {

                        professorSelector(itemsProfAuD);
                        break;
                    }
                    case "TGI": {

                        professorSelector(itemsProfTGI);
                        break;
                    }
                    case "MATHE1": {

                        professorSelector(itemsProfMATHE1);
                        break;
                    }
                    case "ITS": {

                        professorSelector(itemsProfITS);
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


            auth = FirebaseAuth.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();

            db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatappv2-cbaed-default-rtdb.firebaseio.com/");
            commentArrayList = new ArrayList<Comment>();
            postAdapter = new PostAdapter(FrageBogenActivity.this, commentArrayList);
            recyclerView.setAdapter(postAdapter);


            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    commentArrayList.clear();
                    for(DataSnapshot dataSnapshot : snapshot.child("Comments").getChildren()){
                        Comment newComment = dataSnapshot.getValue(Comment.class);
                        commentArrayList.add(newComment);


                    }

                    postAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //add comment
            addCommentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference commentReference = db.child("Comments").push();
                    addCommentBtn.setVisibility(View.INVISIBLE);
                    String commentContent = editTextComment.getText().toString();
                    String uName = user.getDisplayName();
                    Comment comment = new Comment(uName, commentContent);

                    commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(FrageBogenActivity.this, "Comment added",Toast.LENGTH_LONG).show();
                            editTextComment.setText("");
                            addCommentBtn.setVisibility(View.VISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FrageBogenActivity.this, "Comment fail to be added"+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
        }




    private void professorSelector(String[] items) {
        ArrayAdapter<String> adapterProf = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        adapterProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profSpinner.setAdapter(adapterProf);
    }
}