package com.cears.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.R;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.models.QuestionsEquipmentType;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.cears.serviceapp.Fragments.NewJobFragment.EQUIPMENT_NUMBER_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.JOB_TICKET_CONSTANT;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_EQUIPMENT_TYPE;
import static com.cears.serviceapp.Fragments.NewJobFragment.SELECTED_SERVICE_TYPE;

public class MaintenanceActivity extends AppCompatActivity {

    TextView tvCondensor, tvCompressor, tvCooler, tvDataReading, tvCustomerSignature;
    private String[] equipmentTypeList, serviceList;
    private String serviceType, equipmentType;
    private String jobTicketNumber, EquipmentNumber;
    public GeneralDatabase db1 = new GeneralDatabase(this);

    private List<QuestionSubResponse> sectionIDlist = new ArrayList<>();
    private ArrayList<QuestionsEquipmentType> questionsEquipmentTypes = new ArrayList<QuestionsEquipmentType>();

    private List<QuestionSubResponse> sectionIDlist1 = new ArrayList<>();
    private List<QuestionSubResponse> sectionIDlist2 = new ArrayList<>();
    private List<QuestionSubResponse> sectionIDlist3 = new ArrayList<>();
    private List<QuestionSubResponse> allMaintainenceQuestion = new ArrayList<>();
    private List<QuestionSubResponse> sectionListDataReading = new ArrayList<>();

    private ArrayList<QuestionSubResponse> questionArrayList = new ArrayList<QuestionSubResponse>();

    public List<GetAllJob_Jobs> getAllJob_jobses = new ArrayList<GetAllJob_Jobs>();
    GetAllJob_Jobs currentJob;
    String Dat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        initview();

        Intent i = this.getIntent();
        if (i != null) {
            jobTicketNumber = i.getStringExtra(JOB_TICKET_CONSTANT);
            Log.e("Maintainance ", "" + jobTicketNumber);
            EquipmentNumber = i.getStringExtra(EQUIPMENT_NUMBER_CONSTANT);
            Log.e("Maintainance ", "" + EquipmentNumber);
            serviceType = i.getStringExtra(SELECTED_SERVICE_TYPE);
            Log.e("Maintainance ", "" + serviceType);
            equipmentType = i.getStringExtra(SELECTED_EQUIPMENT_TYPE);
            Log.e("Maintainance ", "" + equipmentType);

            currentJob = (GetAllJob_Jobs) i.getSerializableExtra("job");
        }

        questionArrayList = db1.getQuestions();
        filterQuestList();
        filterQuestBySectionID();

        //separateQuestionList();
        //    filterQuestList();
        setclick();

        SimpleDateFormat simpledateformat = new SimpleDateFormat();
        Calendar calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
        Dat = simpledateformat.format(calendar.getTime());

        getAllJob_jobses = db1.getAllJob();
    }

    private void separateQuestionList() {

        for (int i = 0; i < questionArrayList.size(); i++) {

            if (currentJob.getEquipmentType() != null && currentJob.getEquipmentType().getId() != null)

                if (questionArrayList.get(i).getType().toLowerCase().contains("Q".toLowerCase())
                        && questionArrayList.get(i).getMajor().equals("1") /*|| questionArrayList.get(i).getMinor().equals("1") || questionArrayList.get(i).getCleaning().equals("1")*/
                        && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                    sectionIDlist1.add(questionArrayList.get(i));
                } else if (questionArrayList.get(i).getType().toLowerCase().contains("Q".toLowerCase())
                        && /*questionArrayList.get(i).getMajor().equals("1") || */questionArrayList.get(i).getMinor().equals("1") /*|| questionArrayList.get(i).getCleaning().equals("1")*/
                        && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                    sectionIDlist2.add(questionArrayList.get(i));
                } else if (questionArrayList.get(i).getType().toLowerCase().contains("Q".toLowerCase())
                        && questionArrayList.get(i).getCleaning().equals("1")
                        && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                    sectionIDlist3.add(questionArrayList.get(i));
                } else if (questionArrayList.get(i).getType().toLowerCase().equalsIgnoreCase("D".toLowerCase())
                        && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")
                        ) {
                    sectionListDataReading.add(questionArrayList.get(i));
                }
        }

    }

    private void filterQuestList() {
        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getType().toLowerCase().equalsIgnoreCase("Q")) {
                if (questionArrayList.get(i).getMajor().equals("1") || questionArrayList.get(i).getMinor().equals("1") || questionArrayList.get(i).getCleaning().equals("1"))
                    allMaintainenceQuestion.add(questionArrayList.get(i));
            } else if (questionArrayList.get(i).getType().toLowerCase().equalsIgnoreCase("D") && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                sectionListDataReading.add(questionArrayList.get(i));
            }
        }
    }

    private void filterQuestBySectionID() {

        for (int i = 0; i < allMaintainenceQuestion.size(); i++) {

            if (allMaintainenceQuestion.get(i).getSectionId().equals("1") && allMaintainenceQuestion.get(i).getSectionId().equals("7")
                    && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                sectionIDlist1.add(allMaintainenceQuestion.get(i));
            } else if (allMaintainenceQuestion.get(i).getSectionId().equals("3")
                    && getEquipmentType(currentJob.getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                sectionIDlist2.add(allMaintainenceQuestion.get(i));
            } else {
                sectionIDlist3.add(allMaintainenceQuestion.get(i));
            }
        }

    }


/*    private void filterQuestall() {
        for (int i = 0; i < questionArrayList.size(); i++) {
            if (questionArrayList.get(i).getType().toLowerCase().contains("Q".toLowerCase())) {
                questionsEquipmentTypes = questionArrayList.get(i).getEquipmentTypes();
                for (int j = 0; j < questionsEquipmentTypes.size(); j++) {
                    if (currentJob.getEquipmentType().getId().equals(questionsEquipmentTypes.get(j).getEquipmentId())) {
                        if (questionsEquipmentTypes.get(j).getValue().equals("1")) {
                            sectionIDlist.add(questionArrayList.get(i));
                            break;
                        }
                    }
                }
            } else if (questionArrayList.get(i).getType().toLowerCase().contains("D".toLowerCase())) {
                sectionListDataReading.add(questionArrayList.get(i));
            }
        }

    }*/

    public static String getEquipmentType(String job_eq_id, ArrayList<QuestionsEquipmentType> equipmentTypes) {
        for (int i = 0; i < equipmentTypes.size(); i++) {
            if (equipmentTypes.get(i).getEquipmentId().equals(job_eq_id))
                return equipmentTypes.get(i).getValue();
        }
        return "0";
    }


    private void initview() {

        tvCondensor = (TextView) findViewById(R.id.tvCondensor);
        tvCompressor = (TextView) findViewById(R.id.tvCompressor);
        tvCooler = (TextView) findViewById(R.id.tvCooler);
        tvDataReading = (TextView) findViewById(R.id.tvDataReading);
        tvCustomerSignature = (TextView) findViewById(R.id.tvCustomerSignature);

    }

    public void setclick() {

        tvCondensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Dat.equalsIgnoreCase(getAllJob_jobses.get(0).getAll_jobs_date())) {
                    Toasty.success(getApplicationContext(), "Aleady Submittec For This Day", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MaintenanceActivity.this, SurveyActivity.class);
                    i.putExtra("sectionlist", (Serializable) sectionIDlist1);
                    i.putExtra("maintainancetype", "Condensor");
                    i.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                    i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                    i.putExtra(SELECTED_EQUIPMENT_TYPE, "" + equipmentType);
                    i.putExtra(SELECTED_SERVICE_TYPE, "" + serviceType);
                    i.putExtra("job", currentJob);
                    i.putExtra("Q_type", "Q");
                    if (sectionIDlist1 != null && sectionIDlist1.size() > 0)
                        startActivity(i);
                    else {
                        Toast.makeText(MaintenanceActivity.this, "No Questions Are Available For this Section", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        tvCompressor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaintenanceActivity.this, SurveyActivity.class);
                i.putExtra("sectionlist", (Serializable) sectionIDlist2);
                i.putExtra("maintainancetype", "Compressor");
                i.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                i.putExtra(SELECTED_EQUIPMENT_TYPE, "" + equipmentType);
                i.putExtra(SELECTED_SERVICE_TYPE, "" + serviceType);
                i.putExtra("job", currentJob);
                i.putExtra("Q_type", "Q");
                if (sectionIDlist2 != null && sectionIDlist2.size() > 0)
                    startActivity(i);
                else {
                    Toast.makeText(MaintenanceActivity.this, "No Questions Are Available For this Section", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvCooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaintenanceActivity.this, SurveyActivity.class);
                i.putExtra("sectionlist", (Serializable) sectionIDlist3);
                i.putExtra("maintainancetype", "Cooler");
                i.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                i.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                i.putExtra(SELECTED_EQUIPMENT_TYPE, "" + equipmentType);
                i.putExtra(SELECTED_SERVICE_TYPE, "" + serviceType);
                i.putExtra("job", currentJob);
                i.putExtra("Q_type", "Q");
                if (sectionIDlist3 != null && sectionIDlist3.size() > 0)
                    startActivity(i);
                else {
                    Toast.makeText(MaintenanceActivity.this, "No Questions Are Available For this Section", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvDataReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintenanceActivity.this, SurveyActivity.class);
                intent.putExtra("sectionlist", (Serializable) sectionListDataReading);
                intent.putExtra("maintainancetype", "DataReading");
                intent.putExtra(JOB_TICKET_CONSTANT, "" + jobTicketNumber);
                intent.putExtra(EQUIPMENT_NUMBER_CONSTANT, "" + EquipmentNumber);
                intent.putExtra(SELECTED_EQUIPMENT_TYPE, "" + equipmentType);
                intent.putExtra(SELECTED_SERVICE_TYPE, "" + serviceType);
                intent.putExtra("job", currentJob);
                intent.putExtra("Q_type", "D");
                if (sectionListDataReading != null && sectionListDataReading.size() > 0)
                    startActivity(intent);
                else {
                    Toast.makeText(MaintenanceActivity.this, "No Questions Are Available For this Section", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvCustomerSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(MaintenanceActivity.this, MaintenanceActivity.class);
                intent.putExtra("listMaintenance", (Serializable) listtm);
                startActivity(intent);*/
            }
        });

    }


}
