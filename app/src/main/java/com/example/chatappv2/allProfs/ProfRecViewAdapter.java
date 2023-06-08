package com.example.chatappv2.allProfs;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.chatappv2.R;
import com.example.chatappv2.fragebogen.PostAdapter;
import com.example.chatappv2.profDetails.ProfDetailsActivity;
import com.example.chatappv2.professorDetails.PostDetailActivity;

import java.util.ArrayList;

public class ProfRecViewAdapter extends RecyclerView.Adapter<ProfRecViewAdapter.ViewHolder> {

    private static final String TAG = "ProfRecViewAdapter";
    private ArrayList<Professor> profs = new ArrayList<>();

    private Context mContext;

    private String parentActivity;

    public ProfRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    //ini biasa ny begini, copas aja katanya
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_prof, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfRecViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindVIewHolder: Called!");
        holder.txtProfName.setText(profs.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(profs.get(position).getImageUrl())
                .into(holder.imgProf);

        holder.txtProfShortDesc.setText(profs.get(position).getShortDesc());
        holder.ratingBarProf.setRating((float)profs.get(position).getRating());

        holder.imgProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return profs.size();
    }

    public void setProfs(ArrayList<Professor> profs) {
        this.profs = profs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgProf;
        private TextView txtProfName, txtProfShortDesc;
        private RatingBar ratingBarProf;

        //add comment
        private ImageView imgUserAddComment;
        private EditText editTxtAddComment;
        private Button btnAddComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProf = itemView.findViewById(R.id.imageViewProf);
            txtProfName = itemView.findViewById(R.id.txtProfName);
            txtProfShortDesc = itemView.findViewById(R.id.txtProfShortDesc);
            ratingBarProf = itemView.findViewById(R.id.ratingBarProf);

            //add comment
            imgUserAddComment = itemView.findViewById(R.id.postCommendPicture);
            editTxtAddComment = itemView.findViewById(R.id.typeComment);


        }
    }
    public void setFilteredList(ArrayList<Professor> filteredList){
        this.profs = filteredList;
        notifyDataSetChanged();
    }
}
