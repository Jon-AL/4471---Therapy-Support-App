package com.example.mentalhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MedicationListAdaptor extends RecyclerView.Adapter<medViewHolder> {
    ArrayList<MedicationModal> list = new ArrayList<MedicationModal>();
    Context context;

    public MedicationListAdaptor(Context context, ArrayList<MedicationModal> list){
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public medViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_card, parent, false);
        return new medViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull medViewHolder holder, int position) {
        //View cn = holder.itemView.findViewById(R.id.medCommonName).;


        holder.medCommonName.setText(list.get(position).getCommonName());
        holder.medBrandName.setText(list.get(position).getBrandName());
        holder.dosage.setText(list.get(position).getDosage());
        holder.dosageUnit.setText(list.get(position).getDosageUnit());
        holder.frequency.setText(list.get(position).getFrequency());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(ArrayList<MedicationModal> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

