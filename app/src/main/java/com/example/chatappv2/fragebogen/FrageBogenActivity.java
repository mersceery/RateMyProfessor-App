package com.example.chatappv2.fragebogen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chatappv2.R;

public class FrageBogenActivity extends AppCompatActivity {

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

    }
}