package com.example.chatappv2.fragebogen;

import android.widget.Toast;

import com.google.firebase.database.ServerValue;

public class Comment {
    String username;
    String comment;

    Object timestamp;

    public Comment(){

    }

    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
        this.timestamp = ServerValue.TIMESTAMP;
    }
    public Comment(String username, String comment, Object timestamp) {
        this.username = username;
        this.comment = comment;
        this.timestamp = timestamp;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}