package com.t4t.serviceapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.t4t.serviceapp.Activities.MainActivity;
import com.t4t.serviceapp.Activities.SurveyActivity;
import com.t4t.serviceapp.Database.GeneralDatabase;
import com.t4t.serviceapp.Database.IncompleteDatabase;
import com.t4t.serviceapp.Database.LocalDatabase;
import com.t4t.serviceapp.Database.SubmitDatabase;
import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.JsonHelper.CallWebServiceInBackground;
import com.t4t.serviceapp.JsonHelper.GetJSONListener;
import com.t4t.serviceapp.Pojo.DatabasePojo;
import com.t4t.serviceapp.Pojo.IncompletePojo;
import com.t4t.serviceapp.Pojo.SubmitPojo;
import com.t4t.serviceapp.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.t4t.serviceapp.Activities.MainActivity.PRE_ANSWER_CONSTANT;
import static com.t4t.serviceapp.Activities.MainActivity.REQUEST_CODE_SAVED_FRAGMENT;
import static com.t4t.serviceapp.Fragments.NewFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.t4t.serviceapp.Fragments.NewFragment.JOB_TICKET_CONSTANT;
import static com.t4t.serviceapp.Fragments.NewFragment.SELECTED_EQUIPMENT_TYPE;
import static com.t4t.serviceapp.Fragments.NewFragment.SELECTED_SERVICE_TYPE;
import static com.t4t.serviceapp.GeneralHelpers.setIsSurveyInCompleted;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class SavedFragment extends Fragment implements GetJSONListener {


    private View mainView;
    private ListView mListView;
    private ArrayList<IncompletePojo> dataList;
    private String[] equipmentTypeList, serviceList;
    int servicePosition, equipmentPosition, apiCount = 0, apiCountTemp = 0;
    private ProgressDialog progressDialog;
    String jobTicketNumber = "";
    String preAnswer = "";
    String serviceIdTemp = "";
    Boolean isErrorred = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.saved_fragment, container, false);
        mListView = (ListView) mainView.findViewById(R.id.listView);
        progressDialog = new ProgressDialog(getActivity());
        dataList = new ArrayList<IncompletePojo>();
        getData();

        return mainView;

    }

    private void getData() {
        equipmentTypeList = getResources().getStringArray(R.array.equipment_list);
        serviceList = getResources().getStringArray(R.array.service_list);
        ArrayList<IncompletePojo> tempDataList = new ArrayList<IncompletePojo>();
        IncompleteDatabase submitDB = new IncompleteDatabase(getActivity());
        tempDataList = submitDB.getAllInCompletedData();
        submitDB.close();
        Collections.reverse(tempDataList);
        boolean flag = false;
        int length = 0;
        String jobTicketOld = "";
        for (int i = 0; i < tempDataList.size(); i++) {
            IncompletePojo submitPojo = new IncompletePojo();

            if (jobTicketOld.equals("") && flag == false) {
                submitPojo.setJobTicketNumber(tempDataList.get(i).getJobTicketNumber());
                submitPojo.setEquipNumber(tempDataList.get(i).getEquipNumber());
                submitPojo.setServiceType(tempDataList.get(i).getServiceType());
                submitPojo.setEquipmentType(tempDataList.get(i).getEquipmentType());
                submitPojo.setPreAnswer("" + tempDataList.get(i).getPreAnswer());


                if (!isExpired(tempDataList.get(i).getDateString())) {

                    dataList.add(submitPojo);
                }

                jobTicketOld = tempDataList.get(i).getJobTicketNumber();
                flag = true;
            }

            if (!jobTicketOld.equals(tempDataList.get(i).getJobTicketNumber())) {
                jobTicketOld = "";
                length = length + 1;
                flag = false;
            }
            if (length == 30) {
                break;
            }
        }

        if (dataList.size() > 0) {
            mainView.findViewById(R.id.textViewDataNotFound).setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mListView.setAdapter(new CustomAdapter(getActivity()));
        } else {
            mainView.findViewById(R.id.textViewDataNotFound).setVisibility(View.VISIBLE);
        }
    }

    public class CustomAdapter extends BaseAdapter {
        Context mContext;

        public CustomAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_save_custom_adapter, null);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);
            TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
            TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
            TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
            TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);
            TextView mDeleteTextView = (TextView) view.findViewById(R.id.textViewDelete);
            TextView mSubmitTextView = (TextView) view.findViewById(R.id.textViewSubmit);

            mJobTextView.setText("Job Ticket Number : " + dataList.get(position).getJobTicketNumber());
            mEpmNumberTextView.setText("Equip # : " + dataList.get(position).getEquipNumber());
            mEpmTypeTextView.setText("Equipment Type Ticket : " + dataList.get(position).getEquipmentType());
            mServiceTypeTextView.setText("Service Type : " + dataList.get(position).getServiceType());

            jobTicketNumber = dataList.get(position).getJobTicketNumber();

            ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
            GeneralDatabase generalDb = new GeneralDatabase(getActivity());
            techNameList = generalDb.getGeneralData(jobTicketNumber);

            if (techNameList.size() > 0) {
                if (!techNameList.get(0).getSignatureLink().equals("")) {

                    mSubmitTextView.setVisibility(View.VISIBLE);
                } else {
                    mSubmitTextView.setVisibility(View.GONE);
                }
            } else {
                mSubmitTextView.setVisibility(View.GONE);
            }

            //To delete data
            mDeleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert;
                    alert = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog));
                    alert.setTitle("Confirmation");
                    alert.setMessage("Are you sure you want to delete it");
                    alert.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteOperation(position, mContext);
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();


                        }
                    });
                    alert.show();


                }
            });


            mSubmitTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert;
                    alert = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Dialog));
                    alert.setTitle("Confirmation");
                    alert.setMessage("Are you sure you want to submit ?");
                    alert.setNegativeButton("SUBMIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
                                jobTicketNumber = dataList.get(position).getJobTicketNumber();
                                preAnswer = dataList.get(position).getPreAnswer();

                                apiCallForService();
                            }
                            dialog.dismiss();
                        }
                    });
                    alert.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();


                        }
                    });
                    alert.show();
                }
            });

            if (techNameList.size() > 0) {
                if (techNameList.get(0).getSignatureLink().equals("")) {
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            GeneralHelpers.setIsSurveyInCompleted(getActivity(), true);
                            findServiceNumber();
                            Intent intent = new Intent(getActivity(), SurveyActivity.class);
                            intent.putExtra(JOB_TICKET_CONSTANT, "" + dataList.get(position).getJobTicketNumber());
                            intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + dataList.get(position).getEquipNumber());
                            intent.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentPosition);
                            intent.putExtra(SELECTED_SERVICE_TYPE, servicePosition);
                            intent.putExtra(PRE_ANSWER_CONSTANT, "" + dataList.get(position).getPreAnswer());
                            startActivityForResult(intent, REQUEST_CODE_SAVED_FRAGMENT);
                        }
                    });
                }
            } else {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GeneralHelpers.setIsSurveyInCompleted(getActivity(), true);
                        findServiceNumber();
                        Intent intent = new Intent(getActivity(), SurveyActivity.class);
                        intent.putExtra(JOB_TICKET_CONSTANT, "" + dataList.get(position).getJobTicketNumber());
                        intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + dataList.get(position).getEquipNumber());
                        intent.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentPosition);
                        intent.putExtra(SELECTED_SERVICE_TYPE, servicePosition);
                        intent.putExtra(PRE_ANSWER_CONSTANT, "" + dataList.get(position).getPreAnswer());
                        startActivityForResult(intent, REQUEST_CODE_SAVED_FRAGMENT);
                    }
                });
            }


            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SAVED_FRAGMENT) {
        }
    }

    private void findServiceNumber() {
        servicePosition = 1;
        equipmentPosition = 1;
        for (int i = 0; i < serviceList.length; i++) {
            if (dataList.get(0).getServiceType().equals(serviceList[i])) {
                servicePosition = i;

            }
        }

        for (int i = 0; i < equipmentTypeList.length; i++) {
            if (dataList.get(0).getEquipmentType().equals(equipmentTypeList[i])) {
                equipmentPosition = i;
            }
        }

    }

    // save data after submission mode
    private void saveSubmitDataToDB() {
        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(getActivity());
        SubmitDatabase submitDB = new SubmitDatabase(getActivity());

        dataList = localDB.getAllLocalData(jobTicketNumber);
        for (int i = 0; i < dataList.size(); i++) {
            DatabasePojo pojo = dataList.get(i);

            SubmitPojo submitPojo = new SubmitPojo();
            submitPojo.setQuestion(pojo.getQuestion());
            submitPojo.setId(pojo.getId());
            submitPojo.setServiceType(pojo.getServiceType());
            submitPojo.setEquipmentType(pojo.getEquipmentType());
            submitPojo.setEquipNumber(pojo.getEquipNumber());
            submitPojo.setJobTicketNumber(pojo.getJobTicketNumber());
            submitPojo.setGroup(pojo.getGroup());
            submitPojo.setQuestionType(pojo.getQuestionType());
            submitPojo.setSignatureLink("" + pojo.getSignatureLink());
            submitPojo.setName(pojo.getName());
            submitPojo.setPreAnswer("" + dataList.get(0).getPreAnswer());
            submitPojo.setUnit("" + pojo.getUnit());
            submitPojo.setDateString("" + GeneralHelpers.currentDate(new Date()));
            submitPojo.setIsPhoto(pojo.getIsPhoto());

            submitPojo.setPartDescription("" + pojo.getPartDescription());
            submitPojo.setPartNumber("" + pojo.getPartNumber());
            submitPojo.setPartQuantity("" + pojo.getPartQuantity());
            submitPojo.setManPower("" + pojo.getManPower());
            submitPojo.setIsRepaired("" + pojo.getIsRepaired());

            if (pojo.getQuestionType().equals("Q")) {
                submitPojo.setAnswer(pojo.getOptionText());
                if (pojo.getIsPhoto().equals("P")) {
                    submitPojo.setPhotoLink(pojo.getPhotoLink());

                } else {
                    submitPojo.setPhotoLink("");
                }
            } else {
                submitPojo.setAnswer(pojo.getAdditionalTextFiled());
                submitPojo.setPhotoLink("");
            }
            submitDB.addSubmitDataToDb(submitPojo);
        }
        submitDB.close();
        localDB.close();


        //delete from  incomplete list after save
        IncompleteDatabase db = new IncompleteDatabase(getActivity());
        db.deleteInCompleteEntry(jobTicketNumber);
        db.close();

    }

    //comparing 30 days old
    private boolean isExpired(String date) {

        Boolean result = false;

        try {
            Date today = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(today);
            cal.add(Calendar.DAY_OF_MONTH, -30);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date savedDate = format.parse(date);

            if (cal.getTimeInMillis() > savedDate.getTime()) {
                result = true;
            } else {
                result = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void deleteOperation(int position, Context mContext) {
        IncompleteDatabase db = new IncompleteDatabase(mContext);
        db.deleteInCompleteEntry(dataList.get(position).getJobTicketNumber());
        db.close();
        GeneralHelpers.setCurrentFragment(getActivity(), 1);
        ((MainActivity) getActivity()).setView();

    }

    @Override
    public void onRemoteCallComplete(String jsonFromWSCall) {
        String response = "";
        String apiCallType = "";
        String serviceId = "";
        //("Response", "" + jsonFromWSCall);
        try {
            JSONObject jsonObject = new JSONObject(jsonFromWSCall);

            response = jsonObject.get("response").toString();
            apiCallType = jsonObject.get("api").toString();
            serviceId = jsonObject.get("id").toString();

            //check response either service or for question
            if (response.equals("success") && apiCallType.equals("service")) {
                if (!serviceId.equals("")) {
                    serviceIdTemp = serviceId;
                    apiCallForQuestion("" + serviceId);
                }
            } else if (response.equals("success") && apiCallType.equals("question")) {
                apiCountTemp = apiCountTemp + 1;
                progressDialog.setMessage("Submitting " + apiCountTemp + " of " + apiCount);
                if (apiCount == apiCountTemp) {
                    apiCallForSubmitVerification(serviceIdTemp);
                }
            } else if (response.equals("success") && apiCallType.equals("approve")) {

                progressDialog.setMessage("Successful");
                GeneralHelpers.showPopUp(getActivity(), "Your data submitted successfully.");
                GeneralHelpers.setCurrentFragment(getActivity(), 2);
                saveSubmitDataToDB();

                if (progressDialog != null) {
                    progressDialog.cancel();
                }
                ((MainActivity) getActivity()).setView();
//                getActivity().finish();
            } else {

                if (progressDialog != null) {
                    showSubmissionFailedMsg();
                    progressDialog.setMessage("Submission failed.");
                    GeneralHelpers.setCurrentFragment(getActivity(), 0);
                    progressDialog.cancel();
                }

            }


        } catch (Exception e) {
            //("SurveyActivity", "Error : ");

            if (progressDialog != null) {
                GeneralHelpers.setCurrentFragment(getActivity(), 0);
                progressDialog.setMessage("Submission failed.");
                showSubmissionFailedMsg();
                progressDialog.cancel();
            }
            e.printStackTrace();
        }

    }

    private void showSubmissionFailedMsg() {
        if (!isErrorred) {
            GeneralHelpers.showPopUp(getActivity(), "Sorry submission failed.\nPlease try again later ");
            isErrorred = true;
        }
    }

    private void apiCallForService() {

        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        //api calling to submit basic service detail and we will get a service id along response to send with question api call
        progressDialog.show();

        if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);

            String urlParameters = null;

            ArrayList<DatabasePojo> techNameList = new ArrayList<DatabasePojo>();
            GeneralDatabase generalDb = new GeneralDatabase(getActivity());
            techNameList = generalDb.getGeneralData(jobTicketNumber);


            try {
                urlParameters = "Pre_Answers=" + dataList.get(0).getPreAnswer() + "&Post_Answers=" + dataList + "&survey_start_time=" + techNameList.get(0).getSurveyStartTime() + "&JobNumber=" + jobTicketNumber + "&EquipmentNumber=" + techNameList.get(0).getEquipNumber()
                        + "&ServiceType=" + techNameList.get(0).getServiceType() + "&customer_name=A" + techNameList.get(0).getCustomerName() + "&technician_name=sd" + techNameList.get(0).getTechName() + "&site_name=as" + techNameList.get(0).getSiteName() +
                        serviceList[servicePosition] + "&EquipmentType=" + equipmentTypeList[equipmentPosition] + "&SignatureArray=" + techNameList.get(0).getSignatureLink();
                //("Question API Parameters", "" + urlParameters);

            } catch (Exception e) {

                e.printStackTrace();
            }

            serviceInBackground.execute(getString(R.string.API_SERVICE), urlParameters);
        }

    }

    private void apiCallForQuestion(String serviceId) {

        ArrayList<DatabasePojo> dataList = new ArrayList<DatabasePojo>();
        LocalDatabase localDB = new LocalDatabase(getActivity());
        dataList = localDB.getAllLocalData(jobTicketNumber);
        localDB.close();

        for (int i = 0; i < dataList.size(); i++) {

            String answer = "";
            if (dataList.get(i).getQuestionType().equals("Q")) {
                answer = "" + dataList.get(i).getOptionText();
            } else {
                answer = "" + dataList.get(i).getAdditionalTextFiled();
            }

            if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
                CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);
                String urlParameters = null;

                try {


                    //To solve degree issue at server side
                    String unit = "";
                    if (dataList.get(i).getUnit().trim().equals("°F")) {
                        unit = "0F";

                    } else if (dataList.get(i).getUnit().trim().equals("°C")) {
                        unit = "0C";

                    } else {
                        unit = "" + dataList.get(i).getUnit().toString();
                    }

                    apiCount = dataList.size();
                    apiCountTemp = 0;
                    String photoLink = "";
                    if (dataList.get(i).getIsPhoto().equals("1")) {
                        LocalDatabase dB = new LocalDatabase(getActivity());
                        photoLink = dB.getImageById(jobTicketNumber, dataList.get(i).getId());
                    }
                    urlParameters = "id=" + dataList.get(i).getId() + "&equipment_required=" + dataList.get(i).getPartQuantity() + "&manpower_required=" + dataList.get(i).getManPower() + "&part_description=" + dataList.get(i).getPartDescription()
                            + "&part_number=" + dataList.get(i).getPartNumber() + "&is_repaired=" + dataList.get(i).getIsRepaired() + "&token_number=" + jobTicketNumber + "&equipment_type=" + dataList.get(0).getEquipmentType() + "&service_type="
                            + serviceList[servicePosition] + "&group=" + dataList.get(i).getGroup() + "&question=" + dataList.get(i).getQuestion() + "&answer=" + answer + "&isPhoto=" + dataList.get(i).getIsPhoto()
                            + "&questionType=" + dataList.get(i).getQuestionType() + "&service_id=" + serviceId + "&unit=" + unit + "&photo=" + photoLink;

                    //("Question URL  ", "" + urlParameters);
                } catch (Exception e) {

                    e.printStackTrace();

                }
                serviceInBackground.execute(getString(R.string.API_QUESTION), urlParameters);
            }
        }
    }

    private void apiCallForSubmitVerification(String serviceId) {
        if (GeneralHelpers.isOnlineWithoutExit(getActivity())) {
            CallWebServiceInBackground serviceInBackground = new CallWebServiceInBackground(getActivity(), this);
            String urlParameters = null;

            try {
                urlParameters = "approveid=" + serviceId;
                serviceInBackground.execute(getString(R.string.API_SUBMIT_VERIFY), urlParameters);

            } catch (Exception e) {

                e.printStackTrace();

            }
            //("SurveyActivity", "approveid : " + serviceId);

        }
    }


}