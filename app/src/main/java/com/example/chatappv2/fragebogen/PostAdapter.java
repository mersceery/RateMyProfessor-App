package com.example.chatappv2.fragebogen;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    Context context;
    ArrayList<Comment> commentArrayList;

    public PostAdapter(Context context, ArrayList<Comment> commentArrayList) {
        this.context = context;
        this.commentArrayList = commentArrayList;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        Log.d("PostAdapter", "Comment being added to recyclerview");
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Comment comment = commentArrayList.get(position);
        Log.d("PostAdapter", "Comment being set");
        holder.comment.setText(comment.getComment());

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView comment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            comment = itemView.findViewById(R.id.tvComment);

        }
    }
}
