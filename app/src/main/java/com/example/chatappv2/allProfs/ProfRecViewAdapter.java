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
    private final RecViewProfInterface recViewProfInterface;
    private static final String TAG = "ProfRecViewAdapter";
    private ArrayList<Professor> profs = new ArrayList<>();

    private Context mContext;

    private String parentActivity;

    public ProfRecViewAdapter(Context mContext, String parentActivity, RecViewProfInterface recViewProfInterface1) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
        this.recViewProfInterface = recViewProfInterface1;
    }

    @NonNull
    @Override
    //ini biasa ny begini, copas aja katanya
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_prof, null);
        return new ViewHolder(view, recViewProfInterface);
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


        //add comment
        private ImageView imgUserAddComment;
        private EditText editTxtAddComment;
        private Button btnAddComment;

        public ViewHolder(@NonNull View itemView, RecViewProfInterface recViewProfInterface) {
            super(itemView);
            imgProf = itemView.findViewById(R.id.imageViewProf);
            txtProfName = itemView.findViewById(R.id.txtProfName);
            txtProfShortDesc = itemView.findViewById(R.id.txtProfShortDesc);


            imgProf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recViewProfInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recViewProfInterface.onItemClicked(position);
                        }
                    }
                }
            });

        }
    }
    public void setFilteredList(ArrayList<Professor> filteredList){
        this.profs = filteredList;
        notifyDataSetChanged();
    }
}

