package com.example.mentalhealth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mentalhealth.placeholder.PlaceholderContent;

import java.util.ArrayList;

/**
 * Medication list fragment
 * A fragment that will present all the medications inside the database.
 *
 */
public class MedicationListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView medicationsrc;

    MedicationListAdaptor adapter;
    Button value;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MedicationListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MedicationListFragment newInstance(int columnCount) {
        MedicationListFragment fragment = new MedicationListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * Presents all the entries into an array that will displayed on a page.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_med_container, container, false);
        MedicationDBHelper medDB = new MedicationDBHelper(view.getContext());
        //swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        value = view.findViewById(R.id.button3);
        ListView l;
        // getting our course array
        // list from db handler class.
        ArrayList<MedicationModal> MedicationModalArrayList;
        MedicationModalArrayList = medDB.readMedications();



        ArrayList<String> Medication_list_data = new ArrayList<String>();
        for(MedicationModal i: MedicationModalArrayList){
            String temp = "Common Name: " + i.getCommonName() + " Brand Name: " + i.getBrandName()  + " Dosage: " + i.getDosage()+ " Dosage Unit:  " +i.getDosageUnit() + " Frequency: " + i.getFrequency();
            Medication_list_data.add(temp);
        }
        medicationsrc = (RecyclerView) view.findViewById(R.id.medsrc);
//        l = view.findViewById(R.id.medfraglist);
//        ArrayAdapter<String> arr;
//        arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Medication_list_data);
//        l.setAdapter(arr);

        medicationsrc.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter= new MedicationListAdaptor(view.getContext(), MedicationModalArrayList);
       // medicationsrc.setLayoutManager(new LinearLayoutManager(view.getContext()));


        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter= new MedicationListAdaptor(view.getContext(), MedicationModalArrayList);

            }


        });
//        updateData.setOnClickListener(new View.OnClickListener() {
//            /**
//             * get the string and then delete the record.
//             * @param v
//             */
//            public void onClick(View v){
//                ArrayList<MedicationModal> data = medDB.readMedications();
//                adapter.update(data);
//            }
//        });

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
//            @Override
//            public void onRefresh(){
//                swipeRefreshLayout.setRefreshing(false);
//                ArrayList<MedicationModal> difflist = medDB.readMedications();
//                adapter.update(difflist);
//            }
//        });

        medicationsrc.setAdapter(adapter);
        return view;
    }


}