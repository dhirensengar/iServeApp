package com.cears.serviceapp.Fragments;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.cears.serviceapp.Activities.PreQuestions;
import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.Database.IncompleteDatabase;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.Pojo.DataPojo;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.EquipmentTypeResponse;
import com.cears.serviceapp.models.EquipmentTypeSubResponse;
import com.cears.serviceapp.models.ServiceTypeResponse;
import com.cears.serviceapp.models.ServiceTypeSubResponse;
import com.cears.serviceapp.webservice.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cears.serviceapp.Activities.MainActivity.REQUEST_CODE_NEW_FRAGMENT;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class NewJobFragment extends Fragment {
    Spinner mEquipmentSpinner, mServiceTypeSpinner;
    EditText mJobTicketEditText, mEquipmentNumber;
    public final static String JOB_TICKET_CONSTANT = "JOB_TICKET_CONSTANT";
    public final static String EQUIPMENT_NUMBER_CONSTANT = "EQUIPMENT_NUMBER_CONSTANT";
    public final static String SELECTED_EQUIPMENT_TYPE = "SELECTED_EQUIPMENT_TYPE";
    public final static String ALL_PRE_QUESTIONS = "PRE_QUESTIONS";
    public final static String SELECTED_SERVICE_TYPE = "SELECTED_SERVICE_TYPE";
    private Button mNextButton;
    private ArrayList<DataPojo> dataList;
    private View mainView;

    GeneralDatabase db1;

    List<EquipmentTypeSubResponse> typeModels;
    List<ServiceTypeSubResponse> serviceTypeModels;

    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<String> arrayList1 = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.new_fragment, container, false);
        init(mainView);

        db1 = new GeneralDatabase(getActivity());

        getEquipmentypeApi();
        getServiceListApi();

        typeModels = db1.getEquipmentype();
        serviceTypeModels = db1.getServiceType();

        for (int i = 0; i < typeModels.size(); i++) {
            arrayList.add(typeModels.get(i).getEquipmentType());
            Log.e("TAG", "iServ response: Array Serivce " + arrayList);
        }

        for (int i = 0; i < serviceTypeModels.size(); i++) {
            arrayList1.add(serviceTypeModels.get(i).getServiceName());
            Log.e("TAG", "iServ response: 1 Array Serivce " + arrayList1);
        }


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
        ArrayAdapter<String> equipment = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        equipment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEquipmentSpinner.setAdapter(equipment);

        ArrayAdapter<String> equipment2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList1);
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


    public void getEquipmentypeApi() {

        if (AppGlobal.isNetwork(getActivity())) {

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_equipment_type");
            hashMap.put("request", "");
            hashMap.put("auth", auth);


            new RestClient(getActivity()).getInstance().get().equipmentype(hashMap).enqueue(new Callback<EquipmentTypeResponse>() {
                @Override
                public void onResponse(Call<EquipmentTypeResponse> call, Response<EquipmentTypeResponse> response) {

                    Log.e("TAG", "iServ response: " + new Gson().toJson(hashMap));

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                db1.deleteEquipmentType();

                                Log.e("TAG", "iServ response: " + new Gson().toJson(response.body()));

                                if (response.body().getData().size() > 0) {

                                    EquipmentTypeSubResponse equipmentTypeModel = new EquipmentTypeSubResponse();

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        equipmentTypeModel.setId(response.body().getData().get(i).getId());
                                        equipmentTypeModel.setEquipmentType(response.body().getData().get(i).getEquipmentType());
                                        db1.insertEquipmentType(equipmentTypeModel);
                                    }
                                    db1.close();
                                }


                            } else {
                                Toasty.error(getActivity(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getActivity(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<EquipmentTypeResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }


    public void getServiceListApi() {

        if (AppGlobal.isNetwork(getActivity())) {

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(getActivity(), AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_service_list");
            hashMap.put("request", "");
            hashMap.put("auth", auth);


            new RestClient(getActivity()).getInstance().get().servicelist(hashMap).enqueue(new Callback<ServiceTypeResponse>() {
                @Override
                public void onResponse(Call<ServiceTypeResponse> call, Response<ServiceTypeResponse> response) {

                    Log.e("TAG", "iServ response: " + new Gson().toJson(hashMap));

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                db1.deleteServiceType();

                                Log.e("TAG", "iServ response: " + new Gson().toJson(response.body()));

                                if (response.body().getData().size() > 0) {

                                    ServiceTypeSubResponse serviceTypeModel = new ServiceTypeSubResponse();

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        serviceTypeModel.setId(response.body().getData().get(i).getId());
                                        serviceTypeModel.setServiceName(response.body().getData().get(i).getServiceName());
                                        db1.insertServicelist(serviceTypeModel);

                            /*            Log.e("TAG", "iServ response: SERVICETYPE ID" + serviceTypeModel.getId());
                                        Log.e("TAG", "iServ response: SERVICETYPE " + serviceTypeModel.getServiceName());*/
                                    }
                                    db1.close();

                                }


                            } else {
                                Toasty.error(getActivity(), " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getActivity(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ServiceTypeResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

}
