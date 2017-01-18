package com.t4t.serviceapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.t4t.serviceapp.Database.SubmitDatabase;
import com.t4t.serviceapp.Pojo.SubmitPojo;
import com.t4t.serviceapp.R;

import java.util.ArrayList;
import java.util.Collections;

import static com.t4t.serviceapp.Activities.MainActivity.REQUEST_CODE_SUBMITTED_FRAGMENT;

/**
 * Created by VIKAS SAHU on 29/12/16.
 */

public class SubmittedFragment extends Fragment {

    private View mainView;
    private ListView mListView;
    private ArrayList<SubmitPojo> dataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = (View) inflater.inflate(R.layout.submitted_fragment, container, false);
        mListView = (ListView) mainView.findViewById(R.id.listView);
        dataList = new ArrayList<SubmitPojo>();
        getData();
        return mainView;

    }

    private void getData() {
        ArrayList<SubmitPojo> tempDataList = new ArrayList<SubmitPojo>();
        SubmitDatabase submitDB = new SubmitDatabase(getActivity());
        tempDataList = submitDB.getAllSubmittedData();
        submitDB.close();
        Collections.reverse(tempDataList);
        int length = 0;
        boolean flag = false;
        String jobTicketOld = "";
        for (int i = 0; i < tempDataList.size(); i++) {
            SubmitPojo submitPojo = new SubmitPojo();

            if (jobTicketOld.equals("") && flag == false) {
                submitPojo.setJobTicketNumber(tempDataList.get(i).getJobTicketNumber());
                submitPojo.setEquipNumber(tempDataList.get(i).getEquipNumber());
                submitPojo.setServiceType(tempDataList.get(i).getServiceType());
                submitPojo.setEquipmentType(tempDataList.get(i).getEquipmentType());
                dataList.add(submitPojo);
                jobTicketOld = tempDataList.get(i).getJobTicketNumber();
                flag = true;
            }

            if (!jobTicketOld.equals(tempDataList.get(i).getJobTicketNumber())) {
                jobTicketOld = "";
                length = length + 1;
                flag = false;
            }
            if (length == 10) {
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_submit_custom_adapter, null);
            CardView cardView = (CardView) view.findViewById(R.id.card_view);

            TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
            TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
            TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
            TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);

            mJobTextView.setText("Job Ticket Number: " + dataList.get(i).getJobTicketNumber());
            mEpmNumberTextView.setText("Equip # : " + dataList.get(i).getEquipNumber());
            mEpmTypeTextView.setText("Equipment Type Ticket : " + dataList.get(i).getEquipmentType());
            mServiceTypeTextView.setText("Service Type : " + dataList.get(i).getServiceType());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return view;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SUBMITTED_FRAGMENT) {
            Log.e("SavedFragment", "onActivityResult");
        }
    }
}