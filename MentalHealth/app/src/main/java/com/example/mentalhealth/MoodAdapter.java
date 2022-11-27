package com.example.mentalhealth;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<MoodModal> MoodModalArrayList;
    private Context context;

    // constructor
    public MoodAdapter(ArrayList<MoodModal> MoodModalArrayList, Context context) {
        this.MoodModalArrayList = MoodModalArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        MoodModal modal = MoodModalArrayList.get(position);
        holder.moodRatingTV.setText(modal.getMoodRating());
        holder.moodDescriptionTV.setText(modal.getMoodDescription());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateMoodActivity.class);

                // below we are passing all our values.
                i.putExtra("Rating", modal.getMoodRating());
                i.putExtra("description", modal.getMoodDescription());


                // starting our activity.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return MoodModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView moodRatingTV, moodDescriptionTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            moodRatingTV = itemView.findViewById(R.id.idTVMoodRating);
            moodDescriptionTV = itemView.findViewById(R.id.idTVMoodDescription);

        }
    }
}
