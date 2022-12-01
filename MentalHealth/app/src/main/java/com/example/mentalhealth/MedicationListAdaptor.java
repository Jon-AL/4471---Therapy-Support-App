package com.example.mentalhealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * MedicationListAdaptor deals with the recycler view.
 * This helps make the lists nicer to deal with.
 */
public class MedicationListAdaptor extends RecyclerView.Adapter<medViewHolder> {
    ArrayList<MedicationModal> list = new ArrayList<MedicationModal>();
    Context context;

    /**
     * Initializes the context.
     * @param context
     * @param list
     */
    public MedicationListAdaptor(Context context, ArrayList<MedicationModal> list){
        this.context = context;
        this.list = list;
    }


    /**
     * Creates the fragment view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public medViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.med_card, parent, false);
        return new medViewHolder(view);
    }

    /**
     * Bind the numbers to the views.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull medViewHolder holder, int position) {
        //View cn = holder.itemView.findViewById(R.id.medCommonName).;


        holder.medCommonName.setText(list.get(position).getCommonName());
        holder.medBrandName.setText(list.get(position).getBrandName());
        holder.dosage.setText(list.get(position).getDosage());
        holder.dosageUnit.setText(list.get(position).getDosageUnit());
        holder.frequency.setText(list.get(position).getFrequency());

    }

    /**
     * Get the count of the list size
     * @return list size int
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Update data with new array
     * @param data
     */
    public void update(ArrayList<MedicationModal> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Adds to the recycler view
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

