package com.example.chatappv2.modules;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatappv2.LoginActivity;
import com.example.chatappv2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ModulesAdapter extends RecyclerView.Adapter<ModulesAdapter.MyViewHolder> {
    Context context;
    Module module;
    ArrayList<Module> moduleArrayList;

    private TextView moduleName;



    public ModulesAdapter(Context context, ArrayList<Module> moduleArrayList) {
        this.context = context;
        this.moduleArrayList = moduleArrayList;
    }



    @Override
    public ModulesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_modules, parent, false);
        return new ModulesAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModulesAdapter.MyViewHolder holder, int position) {
        Module module1 = moduleArrayList.get(position);
        holder.moduleName.setText(module1.ModuleName);

    }


    @Override
    public int getItemCount() {
        return moduleArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView moduleName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.tvModule);
        }
    }

}