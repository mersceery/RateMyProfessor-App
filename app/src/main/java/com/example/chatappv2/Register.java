package com.example.chatappv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://chatappv2-cbaed-default-rtdb.firebaseio.com/");

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish(); // close the loginActivity properly
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = findViewById(R.id.r_name);
        final EditText mobile = findViewById(R.id.r_mobile);
        final EditText email = findViewById(R.id.r_email);
        final EditText password = findViewById(R.id.r_password);
        final AppCompatButton registerBtn = findViewById(R.id.r_registerBtn);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final TextView loginNow = findViewById(R.id.loginNow);

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        mAuth = FirebaseAuth.getInstance();


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        //check if user already logged in
        if(!MemoryData.getData(this).isEmpty()){
            Intent intent = new Intent(Register.this, MainActivity.class);
            intent.putExtra("mobile", MemoryData.getData(this));
            intent.putExtra("name", MemoryData.getName(this));
            intent.putExtra("email", "");
            startActivity(intent);
            finish();
        }

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                progressDialog.show();

                final String nameTxt = name.getText().toString();
                final String mobileTxt = mobile.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();

                if(nameTxt.isEmpty() || mobileTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "All Fields Required!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    mAuth.createUserWithEmailAndPassword(emailTxt, passwordTxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        Toast.makeText(Register.this, "Account created.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            progressDialog.dismiss();

                            if(snapshot.child("users").hasChild(mobileTxt)){
                                Toast.makeText(Register.this, "Mobile already exist!", Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("users").child(mobileTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(mobileTxt).child("name").setValue(nameTxt);
                                databaseReference.child("users").child(mobileTxt).child("profile_pic").setValue("");

                                //save mobile to memory
                                MemoryData.saveData(mobileTxt, Register.this);

                                //save name to memory
                                MemoryData.saveName(nameTxt, Register.this);

                                Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Register.this, MainActivity.class);
                                intent.putExtra("mobile", mobileTxt);
                                intent.putExtra("name", nameTxt);
                                intent.putExtra("email", emailTxt);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });


    }
}
