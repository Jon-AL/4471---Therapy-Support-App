package com.example.mentalhealth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class SymptomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText symptomDateEdt, symptomDescriptionEdt, symptomNameEdt, oldsystemNameEdt;
    private Button addSymptomBtn, readSymptomBtn, deleteSymptomBtn, updateSymptomBtn;
    public SymptomDBHelper SymptomdbHelper;
    RecyclerView symptoms;

    Adapter adapter;

    public SymptomFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SymptomFragment newInstance(String param1, String param2) {
        SymptomFragment fragment = new SymptomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_symptom, container, false);

        ArrayList<MoodModal> SymptomModalArrayList;
        symptomDateEdt = view.findViewById(R.id.idEdtSymptomdate);
        symptomDescriptionEdt = view.findViewById(R.id.idEdtSymptomDescription);
        symptomNameEdt = view.findViewById(R.id.idEdtSymptomName);
        oldsystemNameEdt = view.findViewById(R.id.idEdtOldSymptomDescription);

        addSymptomBtn = view.findViewById(R.id.idBtnAddSymptom);
        readSymptomBtn = view.findViewById(R.id.idBtnReadSymptom);
        deleteSymptomBtn = view.findViewById(R.id.idBtnDeleteSymptom);
        updateSymptomBtn = view.findViewById(R.id.idBtnUpdateSymptom);

        // creating a new dbhandler class
        // and passing our context to it.
        SymptomdbHelper = new SymptomDBHelper(view.getContext());

        // below line is to add on click listener for our add course button.
        addSymptomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String symptomDate = symptomDateEdt.getText().toString();
                String symptomDescription = symptomDescriptionEdt.getText().toString();
                String symptomName = symptomNameEdt.getText().toString();
                // validating if the text fields are empty or not.
                if (symptomDate.isEmpty() && symptomDescription.isEmpty() && symptomName.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                SymptomdbHelper.addNewSymptom(symptomName,symptomDate ,symptomDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(view.getContext(), "Symptom has been added.", Toast.LENGTH_SHORT).show();
                symptomDateEdt.setText("");
                symptomDescriptionEdt.setText("");
                symptomNameEdt.setText("");
                oldsystemNameEdt.setText("");
            }
        });

        readSymptomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                ListView l;
                // getting our course array
                // list from db handler class.
                ArrayList<SymptomModal> SymptomModalArrayList;
                SymptomModalArrayList = SymptomdbHelper.readSymptoms();

                ArrayList<String > Symptom_list_data = new ArrayList<String>();
                for(SymptomModal i: SymptomModalArrayList){
                    String temp = i.getName() + " " + i.getDate() + " " +i.getDescription();
                    Symptom_list_data.add(temp);
                }
                // System.out.print(MoodModalArrayList.get(1).toString());
                l = view.findViewById(R.id.symptomlist);
                ArrayAdapter<String> arr;
                arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Symptom_list_data);
                l.setAdapter(arr);

                // Intent i = new Intent(v.getContext(), ViewMoods.class);
                // startActivity(i);
            }
        });

        deleteSymptomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String moodRating = symptomDateEdt.getText().toString();
                String moodDescription = symptomDescriptionEdt.getText().toString();
                String symptomName = symptomNameEdt.getText().toString();
                SymptomdbHelper.deleteSymptom(symptomName);
            }
        });

        updateSymptomBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String symptomDate = symptomDateEdt.getText().toString();
                String symptomDescription = symptomDescriptionEdt.getText().toString();
                String symptomName = symptomNameEdt.getText().toString();
                String oldSymptomName = oldsystemNameEdt.getText().toString();

                SymptomdbHelper.updateSymptom(symptomName, symptomDate, symptomDescription, oldSymptomName);
                symptomDateEdt.setText("");
                symptomDescriptionEdt.setText("");
                symptomNameEdt.setText("");
                oldsystemNameEdt.setText("");
            }
        });


        adapter=new Adapter(view.getContext());

        //moodrc.setAdapter(adapter);

        //return view;
     //   symptoms = view.findViewById(R.id.symptomsrc);

      //  symptoms.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter=new Adapter(view.getContext());
        //symptoms.setAdapter(adapter);

        return view;



    }
}