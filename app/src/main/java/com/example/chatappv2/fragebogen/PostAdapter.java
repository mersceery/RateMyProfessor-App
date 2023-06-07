package com.example.chatappv2.fragebogen;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappv2.R;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Comment comment;
    Context context;
    ArrayList<Comment> commentArrayList;

    public PostAdapter(Context context, ArrayList<Comment> commentArrayList) {
        this.context = context;
        this.commentArrayList = commentArrayList;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = commentArrayList.get(position);
        holder.username.setText(comment.username);
        holder.comment.setText(comment.comment);

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView comment, username;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            comment = itemView.findViewById(R.id.tvComment);
            username = itemView.findViewById(R.id.tvUsername);

        }
    }
}