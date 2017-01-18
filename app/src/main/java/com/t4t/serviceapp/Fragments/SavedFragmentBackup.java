package com.t4t.serviceapp.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.t4t.serviceapp.Activities.SurveyActivity;
import com.t4t.serviceapp.Database.IncompleteDatabase;
import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.Pojo.IncompletePojo;
import com.t4t.serviceapp.R;

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

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class SavedFragmentBackup extends Fragment {


    private View mainView;
    private ListView mListView;
    private ArrayList<IncompletePojo> dataList;
    private String[] equipmentTypeList, serviceList;
    int servicePosition, equipmentPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.saved_fragment, container, false);
        mListView = (ListView) mainView.findViewById(R.id.listView);
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
        getActivity().recreate();
    }
}