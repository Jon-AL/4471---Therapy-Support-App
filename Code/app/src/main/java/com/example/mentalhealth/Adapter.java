package com.example.mentalhealth;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter class will hold, and bind specific views.
 */
public class Adapter  extends RecyclerView.Adapter<Adapter.viewHolder> {

    Context context;

    /**
     * Adaptor constructor.
     * @param context
     */
    public Adapter(Context context) {

        this.context = context;
    }

    /**
     * Holds the view and returns the specific view.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new viewHolder(view);
    }

    /**
     *  Holds the viewholder by position.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            /**
             * When clicked, the specific id will respond.
             * @param view
             */
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, holder.imageView);

                popup.inflate(R.menu.rcmenu);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();
            }
        });


    }

    /**
     * Getter for item count
     * @return the number 10
     */
    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * Initializing the image options.
     */
    static class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        /**
         * contain the views.
         * @param itemView
         */
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img_options);

        }
    }
}

