package com.t4t.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.t4t.serviceapp.GeneralHelpers;
import com.t4t.serviceapp.Pojo.DataPojo;
import com.t4t.serviceapp.R;

import java.util.ArrayList;

import static com.t4t.serviceapp.Activities.MainActivity.PRE_ANSWER_CONSTANT;
import static com.t4t.serviceapp.Activities.MainActivity.REQUEST_CODE_NEW_FRAGMENT;
import static com.t4t.serviceapp.Fragments.NewFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.t4t.serviceapp.Fragments.NewFragment.JOB_TICKET_CONSTANT;
import static com.t4t.serviceapp.Fragments.NewFragment.SELECTED_EQUIPMENT_TYPE;
import static com.t4t.serviceapp.Fragments.NewFragment.SELECTED_SERVICE_TYPE;

/**
 * Created by VIKAS SAHU on 03/01/17.
 */

public class PreQuestions extends BaseActivity {
    private TextView mEquipmentNumberTextView, mServiceTypeTextView, mEquipmentTypeTextView, mQuestionTextView, mAdditionalAnswerField, mJobTicketNumberTextView, mQDMasterTextView;
    String[] questions;
    private String[] equipmentTypeList, serviceList;
    private int currentPositionQuestion;
    private RadioGroup radioGroup;
    private ArrayList<DataPojo> dataList;
    private int servicePosition, equipmentTypePosition;
    private String jobTicketNumber, EquipmentNumber;
    private String answeredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));
        currentPositionQuestion = 0;
        questions = getResources().getStringArray(R.array.pre_question_list);
        init();
        GeneralHelpers.setIsSurveyInCompleted(this,false);
        ShowQuestion(0);

    }

    public void onNextPreQuestionClick(View view) {
        String answer = "";

        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonYes) {
                answer = "Yes";
            } else {
                answer = "No";
            }
            if (currentPositionQuestion == 0) {
                answeredList = "" + answer;
            } else {
                answeredList = answeredList.concat("," + answer);

            }
            if (currentPositionQuestion < 22) {
                currentPositionQuestion = currentPositionQuestion + 1;
                ShowQuestion(currentPositionQuestion);
                radioGroup.clearCheck();
            }

        } else {
            Toast.makeText(getBaseContext(), "Please select an option", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected int getLayoutResource() {
        return R.layout.layout_pre_questions;
    }

    private void ShowQuestion(int position) {
        if (currentPositionQuestion == 22) {

            GeneralHelpers.saveDataToGeneralDB(this, jobTicketNumber, EquipmentNumber, serviceList[servicePosition], serviceList[servicePosition], answeredList);
            Intent intent = new Intent(this, SurveyActivity.class);
            intent.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
            intent.putExtra(PRE_ANSWER_CONSTANT, "" + answeredList);
            intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
            intent.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentTypePosition);
            intent.putExtra(SELECTED_SERVICE_TYPE, servicePosition);
            intent.putParcelableArrayListExtra("DataList", dataList);
            startActivityForResult(intent, REQUEST_CODE_NEW_FRAGMENT);
            finish();
        } else {
            mQuestionTextView.setText("" + (position + 1) + " : "+questions[position]);
        }

    }

    private void init() {
        jobTicketNumber = getIntent().getStringExtra(JOB_TICKET_CONSTANT);
        EquipmentNumber = getIntent().getStringExtra(EQUIPMENT_NUMBER_CONSTANT);
        servicePosition = getIntent().getIntExtra(SELECTED_SERVICE_TYPE, 1);
        equipmentTypePosition = getIntent().getIntExtra(SELECTED_EQUIPMENT_TYPE, 1);

        mEquipmentNumberTextView = (TextView) findViewById(R.id.textViewEquipmentNumber);
        mServiceTypeTextView = (TextView) findViewById(R.id.textViewServiceType);
        mEquipmentTypeTextView = (TextView) findViewById(R.id.textViewEquipmentType);
        mQuestionTextView = (TextView) findViewById(R.id.textViewQuestions);


        mJobTicketNumberTextView = (TextView) findViewById(R.id.textViewJobTicketNumber);
        mQDMasterTextView = (TextView) findViewById(R.id.textViewQDMaster);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupPre);

        equipmentTypeList = getResources().getStringArray(R.array.equipment_list);
        serviceList = getResources().getStringArray(R.array.service_list);

        mEquipmentNumberTextView.setText("Equipment Number  :  " + EquipmentNumber);
        mServiceTypeTextView.setText("Maintenance Type  :  " + serviceList[servicePosition]);
        mEquipmentTypeTextView.setText("Equipment  :  " + equipmentTypeList[equipmentTypePosition]);
        mJobTicketNumberTextView.setText("Job Ticket Number  :  " + jobTicketNumber);
        dataList = new ArrayList<DataPojo>();
        dataList = getIntent().getParcelableArrayListExtra("DataList");

    }
}
