package com.example.mentalhealth;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medication_list, container, false);
        MedicationDBHelper medDB = new MedicationDBHelper(view.getContext());

        ListView l;
        // getting our course array
        // list from db handler class.
        ArrayList<MedicationModal> MedicationModalArrayList;
        MedicationModalArrayList = medDB.readMedications();

        ArrayList<String> Medication_list_data = new ArrayList<String>();
        for(MedicationModal i: MedicationModalArrayList){
            String temp = i.getCommonName() + " " + i.getBrandName()  + " " + i.getDosage()+ " " +i.getDosageUnit() + " " + i.getFrequency();
            Medication_list_data.add(temp);
        }

        l = view.findViewById(R.id.medfraglist);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Medication_list_data);
        l.setAdapter(arr);

        return view;
    }
}