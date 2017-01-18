package com.t4t.serviceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.t4t.serviceapp.Activities.PreQuestions;
import com.t4t.serviceapp.Database.IncompleteDatabase;
import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.Pojo.DataPojo;
import com.t4t.serviceapp.R;

import java.util.ArrayList;

import static com.t4t.serviceapp.Activities.MainActivity.REQUEST_CODE_NEW_FRAGMENT;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class NewFragment extends Fragment {
    Spinner mEquipmentSpinner, mServiceTypeSpinner;
    EditText mJobTicketEditText, mEquipmentNumber;
    public final static String JOB_TICKET_CONSTANT = "JOB_TICKET_CONSTANT";
    public final static String EQUIPMENT_NUMBER_CONSTANT = "EQUIPMENT_NUMBER_CONSTANT";
    public final static String SELECTED_EQUIPMENT_TYPE = "SELECTED_EQUIPMENT_TYPE";
    public final static String SELECTED_SERVICE_TYPE = "SELECTED_SERVICE_TYPE";

    private Button mNextButton;
    private ArrayList<DataPojo> dataList;
    private View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.new_fragment, container, false);
        init(mainView);


        mServiceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                mEquipmentSpinner.requestFocus();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });


        dataList = getActivity().getIntent().getParcelableArrayListExtra("DataList");
        ArrayAdapter<String> equipment = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.equipment_list));
        equipment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEquipmentSpinner.setAdapter(equipment);

        ArrayAdapter<String> equipment2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.service_list));
        equipment2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mServiceTypeSpinner.setAdapter(equipment2);
        GeneralHelpers.setIsSurveyInCompleted(getActivity(), false);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    IncompleteDatabase db = new IncompleteDatabase(getActivity());

                    if (!db.isJobAlreadyExisting((mEquipmentNumber.getText().toString().trim()).replaceFirst("^0+(?!$)", ""), (mJobTicketEditText.getText().toString().trim()).replaceFirst("^0+(?!$)", ""))) {
                        Intent intent = new Intent(getActivity(), PreQuestions.class);
                        intent.putExtra(JOB_TICKET_CONSTANT, "" + (mJobTicketEditText.getText().toString().trim()).replaceFirst("^0+(?!$)", ""));
                        intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + (mEquipmentNumber.getText().toString().trim()).replaceFirst("^0+(?!$)", ""));
                        intent.putExtra(SELECTED_EQUIPMENT_TYPE, mEquipmentSpinner.getSelectedItemPosition());
                        intent.putExtra(SELECTED_SERVICE_TYPE, mServiceTypeSpinner.getSelectedItemPosition());
                        intent.putParcelableArrayListExtra("DataList", dataList);
                        startActivityForResult(intent, REQUEST_CODE_NEW_FRAGMENT);
                    } else {
                        GeneralHelpers.showPopUp(getActivity(), "This job ticket exists in saved list");
                    }
                }
            }
        });

        return mainView;

    }

    private void init(View view) {
        mEquipmentSpinner = (Spinner) view.findViewById(R.id.spinnerCaptionType);
        mServiceTypeSpinner = (Spinner) view.findViewById(R.id.spinnerServiceType);
        mJobTicketEditText = (EditText) view.findViewById(R.id.editTextJobTicket);
        mEquipmentNumber = (EditText) view.findViewById(R.id.editTextEquipmentNo);
        mNextButton = (Button) view.findViewById(R.id.buttonSubmit);
        mJobTicketEditText.setError(null);
        mEquipmentNumber.setError(null);
    }


    private boolean validateFields() {
        boolean valid = true;

        String jobTicket = mJobTicketEditText.getText().toString().trim();

        if (TextUtils.isEmpty(jobTicket)) {
            mJobTicketEditText.setError("Required.");
            valid = false;
        } else {
            mJobTicketEditText.setError(null);
        }

        String equipmentNumber = mEquipmentNumber.getText().toString().trim();

        if (TextUtils.isEmpty(equipmentNumber)) {
            mEquipmentNumber.setError("Required.");
            valid = false;
        }

        return valid;
    }

    @Override
    public void onResume() {
        super.onResume();
        mJobTicketEditText.setText("");
        mJobTicketEditText.setError(null);
        mEquipmentNumber.setText("");
        mEquipmentNumber.setError(null);
        ArrayAdapter<String> equipment = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.equipment_list));
        equipment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEquipmentSpinner.setAdapter(equipment);


        ArrayAdapter<String> equipment2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.service_list));
        equipment2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mServiceTypeSpinner.setAdapter(equipment2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NEW_FRAGMENT) {

        }
        mJobTicketEditText.setText("");
        mJobTicketEditText.setError(null);
        mEquipmentNumber.setText("");
        mEquipmentNumber.setError(null);
    }

}
