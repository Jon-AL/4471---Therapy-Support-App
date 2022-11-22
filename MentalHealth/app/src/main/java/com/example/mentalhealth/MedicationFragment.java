package com.example.mentalhealth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText medicationNameEdt, brandNameEdt, dosageEdt, dosageUnitEdt, frequencyEdt, oldsystemNameEdt;
    private Button addMedicationBtn, readMedicationBtn, deleteMedicationBtn, updateMedicationBtn;
    public MedicationDBHelper MedicationdbHelper;

    RecyclerView medicationrc;

    Adapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedicationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicationFragment newInstance(String param1, String param2) {
        MedicationFragment fragment = new MedicationFragment();
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

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_medication, container, false);


        ArrayList<MoodModal> MedicationModalArrayList;

        medicationNameEdt = view.findViewById(R.id.idEdtMedicationName);
        brandNameEdt = view.findViewById(R.id.idEdtBrandName);
        dosageEdt = view.findViewById(R.id.idEdtquantity);
        dosageUnitEdt = view.findViewById(R.id.idEdtQuantityUnit);
        frequencyEdt = view.findViewById(R.id.idEdtfrequency);
        oldsystemNameEdt = view.findViewById(R.id.idEdtOldName);

        addMedicationBtn = view.findViewById(R.id.idBtnAddMedication);
        readMedicationBtn = view.findViewById(R.id.idBtnReadMedication);
        deleteMedicationBtn = view.findViewById(R.id.idBtnDeleteMedication);
        updateMedicationBtn = view.findViewById(R.id.idBtnUpdateMedication);

        // creating a new dbhandler class
        // and passing our context to it.
        MedicationdbHelper = new MedicationDBHelper(view.getContext());

        // below line is to add on click listener for our add course button.
        addMedicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String medName = medicationNameEdt.getText().toString();
                String commonName = brandNameEdt.getText().toString();
                String dosage = dosageEdt.getText().toString();
                String dosageUnit = dosageUnitEdt.getText().toString();
                String frequency = frequencyEdt.getText().toString();
                // validating if the text fields are empty or not.
                if (medName.isEmpty() && commonName.isEmpty() && dosage.isEmpty() && dosageUnit.isEmpty() && frequency.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                MedicationdbHelper.addNewMedication(medName,commonName, dosage, dosageUnit, frequency);

                // after adding the data we are displaying a toast message.
                Toast.makeText(view.getContext(), "Medication has been added.", Toast.LENGTH_SHORT).show();
                medicationNameEdt.setText("");
                brandNameEdt.setText("");
                dosageEdt.setText("");
                dosageUnitEdt.setText("");
                frequencyEdt.setText("");
                oldsystemNameEdt.setText("");
            }
        });

        readMedicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                ListView l;
                // getting our course array
                // list from db handler class.
                ArrayList<MedicationModal> MedicationModalArrayList;
                MedicationModalArrayList = MedicationdbHelper.readMedications();

                ArrayList<String> Medication_list_data = new ArrayList<String>();
                for(MedicationModal i: MedicationModalArrayList){
                    String temp = i.getCommonName() + " " + i.getBrandName()  + " " + i.getDosage()+ " " +i.getDosageUnit() + " " + i.getFrequency();
                    Medication_list_data.add(temp);
                }
                // System.out.print(MoodModalArrayList.get(1).toString());
                l = view.findViewById(R.id.medlist);
                ArrayAdapter<String> arr;
                arr = new ArrayAdapter<String>(view.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Medication_list_data);
                l.setAdapter(arr);

                view.setContentView(R.layout.fragment_activity_view_medication);
                // Intent i = new Intent(v.getContext(), ViewMoods.class);
                // startActivity(i);
            }
        });

        deleteMedicationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                String brandName = brandNameEdt.getText().toString();
                MedicationdbHelper.deleteRecord(brandName);
            }
        });

        updateMedicationBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String medName = medicationNameEdt.getText().toString();
                String brandName = brandNameEdt.getText().toString();
                String dosage = dosageEdt.getText().toString();
                String dosageUnit = dosageUnitEdt.getText().toString();
                String frequency = frequencyEdt.getText().toString();
                String oldMedicationName = oldsystemNameEdt.getText().toString();

                MedicationdbHelper.updateMedication(oldMedicationName, medName, brandName, dosage, dosageUnit, frequency);
                medicationNameEdt.setText("");
                brandNameEdt.setText("");
                dosageEdt.setText("");
                dosageUnitEdt.setText("");
                frequencyEdt.setText("");
                oldsystemNameEdt.setText("");
            }
        });
        adapter=new Adapter(view.getContext());
        return view;
    }
}