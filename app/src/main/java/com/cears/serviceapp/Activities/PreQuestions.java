package com.cears.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.GeneralHelpers;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.GetAllJobSiteSubResponse;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.PreQuestionModel;
import com.cears.serviceapp.models.QuestionSubResponse;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.cears.serviceapp.Fragments.NewJobFragment.ALL_PRE_QUESTIONS;
import static com.cears.serviceapp.Fragments.NewJobFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.JOB_TICKET_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_SERVICE_TYPE;


public class PreQuestions extends BaseActivity {
    private TextView mEquipmentNumberTextView, mServiceTypeTextView, mEquipmentTypeTextView, mQuestionTextView, mAdditionalAnswerField, mJobTicketNumberTextView, mQDMasterTextView;
    //  String[] questions;
    private String[] equipmentTypeList, serviceList;
    private int currentPositionQuestion = 0;
    private RadioGroup radioGroup;
    //   private ArrayList<DataPojo> dataList;

    private String serviceType, equipmentType;
    private String jobTicketNumber, EquipmentNumber;
    private String maintainance = "";
    private List<QuestionSubResponse> filteredQuestionList = new ArrayList<QuestionSubResponse>();
    public GeneralDatabase db1 = new GeneralDatabase(this);

    public List<PreQuestionModel> allPreQuestionlist;
    public List<GetAllJobSiteSubResponse> getAllJobSites = new ArrayList<GetAllJobSiteSubResponse>();
    public List<GetAllJob_Jobs> getAllJob_jobses = new ArrayList<GetAllJob_Jobs>();
    String Dat = "", site_id = "";
    GetAllJob_Jobs currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.mipmap.icon);
        setActionBarTitle("" + getResources().getString(R.string.app_name));
        //    questions = getResources().getStringArray(R.array.pre_question_list);
        init();
        GeneralHelpers.setIsSurveyInCompleted(this, false);
        allPreQuestionlist = db1.getAllPreQuestion();
        ShowQuestion(0);

        SimpleDateFormat simpledateformat = new SimpleDateFormat();
        Calendar calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        Dat = simpledateformat.format(calendar.getTime());

        getAllJob_jobses = db1.getAllJob();
        getAllJobSites = db1.getAllJob_Site();
    }


    public void onNextPreQuestionClick(View view) {
        String answer = "";

        if (radioGroup.getCheckedRadioButtonId() != -1) {
            if (radioGroup.getCheckedRadioButtonId() == R.id.radioButtonYes) {
                answer = "1";
            } else {
                answer = "0";
            }

            allPreQuestionlist.get(currentPositionQuestion).setAnswer(answer);
            Log.e("", "Set AnswersList " + allPreQuestionlist.get(currentPositionQuestion).getAnswer());

            if (currentPositionQuestion < allPreQuestionlist.size()) {
                currentPositionQuestion = currentPositionQuestion + 1;
                ShowQuestion(currentPositionQuestion);
                radioGroup.clearCheck();
            }

        } else {
            Toasty.info(getBaseContext(), "Please select an option", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_pre_questions;
    }

    private void ShowQuestion(int position) {
        if (currentPositionQuestion == allPreQuestionlist.size()) {

            db1.updateSiteDate(site_id);
            //        GeneralHelpers.saveDataToGeneralDB(this, jobTicketNumber, EquipmentNumber, serviceList[servicePosition], serviceList[servicePosition], answeredList);
            if (maintainance != null && maintainance.equalsIgnoreCase("maintainance")) {
                Intent i = new Intent(this, MaintenanceActivity.class);
                i.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                i.putExtra("job", currentJob);
                i.putExtra(ALL_PRE_QUESTIONS, (Serializable) allPreQuestionlist);
                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                i.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentType);
                i.putExtra(SELECTED_SERVICE_TYPE, serviceType);
                startActivity(i);
                finish();
            } else {
                Intent i = new Intent(this, SurveyActivity.class);
                i.putExtra("save", "save");
                i.putExtra("Q_type", "Q");
                i.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                i.putExtra("job", currentJob);
                i.putExtra(ALL_PRE_QUESTIONS, (Serializable) allPreQuestionlist);
                if (filteredQuestionList != null && filteredQuestionList.size() > 0) {
                    i.putExtra("sectionlist", (Serializable) filteredQuestionList);
                    Log.e("CustomAdapter", "AdapterQuestionList " + filteredQuestionList.size());
                }

                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                i.putExtra(SELECTED_EQUIPMENT_TYPE, equipmentType);
                i.putExtra(SELECTED_SERVICE_TYPE, serviceType);
                // startActivityForResult(i, REQUEST_CODE_NEW_FRAGMENT);
                startActivity(i);
                finish();
            }


        } else {
            mQuestionTextView.setText(allPreQuestionlist.get(currentPositionQuestion).getPreQuestion());
            Log.e("", "PreQuelstion AnswersList " + allPreQuestionlist.get(currentPositionQuestion).getAnswer());
        }

    }

    private void init() {

        filteredQuestionList = (List<QuestionSubResponse>) getIntent().getSerializableExtra("sectionlist");
        maintainance = getIntent().getStringExtra("maintainance");
        site_id = getIntent().getStringExtra("siteID");
        currentJob = (GetAllJob_Jobs) getIntent().getSerializableExtra("job");

        jobTicketNumber = getIntent().getStringExtra(JOB_TICKET_CONSTANT);
        EquipmentNumber = getIntent().getStringExtra(EQUIPMENT_NUMBER_CONSTANT);
        serviceType = getIntent().getStringExtra(SELECTED_SERVICE_TYPE);
        equipmentType = getIntent().getStringExtra(SELECTED_EQUIPMENT_TYPE);

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
        mServiceTypeTextView.setText("Maintenance Type  :  " + serviceType);
        mEquipmentTypeTextView.setText("Equipment  :  " + equipmentType);
        mJobTicketNumberTextView.setText("Job Ticket Number  :  " + jobTicketNumber);

    }


}
