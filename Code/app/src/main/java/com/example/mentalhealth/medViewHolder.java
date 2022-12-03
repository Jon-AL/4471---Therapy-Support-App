package com.example.mentalhealth;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Holds the views and respective value text holders.
 */
public class medViewHolder extends RecyclerView.ViewHolder{
    TextView medCommonName;
    TextView medBrandName;
    TextView frequency;
    TextView dosage;
    TextView dosageUnit;

    /**
     * Initialize the views
     * @param itemView
     */
    public medViewHolder(View itemView){
        super(itemView);
        medCommonName = (TextView) itemView.findViewById(R.id.medCommonName);
        medBrandName = (TextView) itemView.findViewById(R.id.medBrandName);
        frequency = (TextView) itemView.findViewById(R.id.medfrequency);
        dosage = (TextView) itemView.findViewById(R.id.meddosage);
        dosageUnit = (TextView) itemView.findViewById(R.id.meddosageunit);
    }
}
