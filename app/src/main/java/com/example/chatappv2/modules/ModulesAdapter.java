package com.example.chatappv2.modules;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chatappv2.R;
import com.example.chatappv2.allProfs.AllProfsActivity;

import java.util.ArrayList;

public class ModulesAdapter extends RecyclerView.Adapter<ModulesAdapter.MyViewHolder> implements ModuleRecViewInterface {
    Context context;
    private final ModuleRecViewInterface moduleRecViewInterface;
    ArrayList<Module> moduleArrayList;
    private int moduleNumber;

    public int getModuleFilter() {
        return moduleFilter;
    }

    public void setModuleFilter(int moduleFilter) {
        this.moduleFilter = moduleFilter;
    }

    int moduleFilter;
    public int getModuleNumber() {
        return moduleNumber;
    }

    public void setModuleNumber(int moduleNumber) {
        this.moduleNumber = moduleNumber;
    }

    @Override
    public void onItemClick(int position) {

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView moduleName;


        public MyViewHolder(@NonNull View itemView, ModuleRecViewInterface moduleRecViewInterface) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.tvModule);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (moduleRecViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            moduleRecViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


    public ModulesAdapter(Context context, ArrayList<Module> moduleArrayList, ModuleRecViewInterface moduleRecViewInterface) {
        this.context = context;
        this.moduleArrayList = moduleArrayList;
        this.moduleRecViewInterface = moduleRecViewInterface;
    }



    @Override
    public ModulesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_modules, null );
        return new ModulesAdapter.MyViewHolder(v, moduleRecViewInterface);
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

    public void setFilteredList(ArrayList<Module> filteredList){
        this.moduleArrayList = filteredList;
        notifyDataSetChanged();
    }

}
