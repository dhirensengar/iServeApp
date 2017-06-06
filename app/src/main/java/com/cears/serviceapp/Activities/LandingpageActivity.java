package com.cears.serviceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cears.serviceapp.AppGlobal.AppConstant;
import com.cears.serviceapp.AppGlobal.AppGlobal;
import com.cears.serviceapp.Database.GeneralDatabase;
import com.cears.serviceapp.R;
import com.cears.serviceapp.Service.MyService;
import com.cears.serviceapp.models.GetAllJobResponse;
import com.cears.serviceapp.models.GetAllJob_Jobs;
import com.cears.serviceapp.models.PartsModel;
import com.cears.serviceapp.models.PartsResponse;
import com.cears.serviceapp.models.PreQuestionModel;
import com.cears.serviceapp.models.PreQuestionResponse;
import com.cears.serviceapp.models.QuestionSubResponse;
import com.cears.serviceapp.models.QuestionsEquipmentType;
import com.cears.serviceapp.models.SectionNameModel;
import com.cears.serviceapp.models.SectionNameResponse;
import com.cears.serviceapp.webservice.RestClient;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingpageActivity extends AppCompatActivity {

    TextView tvMaintenance, tvBreakdown, tvInspection, tvTM, tvParts, tvEmergency, tvTMCount, tvMaintainanceCount,
            tvInspectionCount, tvBreadownCount, tvPartsCount;
    GeneralDatabase db1 = new GeneralDatabase(LandingpageActivity.this);
    private List<GetAllJob_Jobs> listMaintenance;
    private List<GetAllJob_Jobs> listBreakdown;
    private List<GetAllJob_Jobs> listinspection;
    private List<GetAllJob_Jobs> listtm;

    private ArrayList<QuestionSubResponse> questionArrayList = new ArrayList<QuestionSubResponse>();
    private List<QuestionSubResponse> sectionIDlist5 = new ArrayList<>();
    private List<QuestionSubResponse> sectionIDlist6 = new ArrayList<>();
    private List<QuestionSubResponse> sectionIDlist7 = new ArrayList<>();

    List<GetAllJob_Jobs> alljobarrayList = new ArrayList<GetAllJob_Jobs>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        initview();

        preQuestionApi();
        sectionNameApi();
        partsApi();
        getAllJobApi();
        setonclick();

        alljobarrayList = db1.getAllJob();

        Log.e("Job List Size", "alljobarrayList  " + alljobarrayList.size());
        separatejobTypeList();
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        questionArrayList = db1.getQuestions();
        separateQuestionList();

    }


    public void setonclick() {

        tvMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LandingpageActivity.this, MainActivity2.class);
                intent.putExtra("listMaintenance", (Serializable) listMaintenance);
                intent.putExtra("maintainance", "maintainance");
                startActivity(intent);
            }
        });
        tvBreakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingpageActivity.this, MainActivity2.class);
                intent.putExtra("listMaintenance", (Serializable) listBreakdown);
                intent.putExtra("sectionlist", (Serializable) sectionIDlist5);
                Log.e("sectionIDlist5 size", "" + sectionIDlist5.size());
                intent.putExtra("maintainance", "m");
                startActivity(intent);
            }
        });
        tvInspection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingpageActivity.this, MainActivity2.class);
                intent.putExtra("listMaintenance", (Serializable) listinspection);
                intent.putExtra("sectionlist", (Serializable) sectionIDlist6);
                Log.e("sectionIDlist6 size", "" + sectionIDlist6.size());
                intent.putExtra("maintainance", "m");
                startActivity(intent);
            }
        });
        tvTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingpageActivity.this, MainActivity2.class);
                intent.putExtra("listMaintenance", (Serializable) listtm);
                intent.putExtra("sectionlist", (Serializable) sectionIDlist7);
                Log.e("sectionIDlist7 size", "" + sectionIDlist7.size());
                intent.putExtra("maintainance", "m");
                startActivity(intent);
            }
        });
        tvParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(LandingpageActivity.this, MaintenanceActivity.class);
                intent.putExtra("allJobsBySection", (Serializable) listtm);
                startActivity(intent);*/
            }
        });

        tvEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingpageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initview() {

        tvMaintainanceCount = (TextView) findViewById(R.id.tvMaintainanceCount);
        tvBreadownCount = (TextView) findViewById(R.id.tvBreadownCount);
        tvInspectionCount = (TextView) findViewById(R.id.tvInspectionCount);
        tvTMCount = (TextView) findViewById(R.id.tvTMCount);
        tvPartsCount = (TextView) findViewById(R.id.tvPartsCount);


        tvMaintenance = (TextView) findViewById(R.id.tvMaintenance);
        tvBreakdown = (TextView) findViewById(R.id.tvBreakdown);
        tvInspection = (TextView) findViewById(R.id.tvInspection);
        tvTM = (TextView) findViewById(R.id.tvTM);
        tvParts = (TextView) findViewById(R.id.tvParts);
        tvEmergency = (TextView) findViewById(R.id.tvEmergency);
    }

    private void separateQuestionList() {

        for (int j = 0; j < alljobarrayList.size(); j++) {

            if (alljobarrayList.get(j).getEquipmentType() != null && alljobarrayList.get(j).getEquipmentType().getId() != null)

                for (int i = 0; i < questionArrayList.size(); i++) {
                    if (questionArrayList.get(i).getType().equalsIgnoreCase("Q") && questionArrayList.get(i).getMajor().equals("1")
                            && questionArrayList.get(i).getMinor().equals("1") && questionArrayList.get(i).getCleaning().equals("1")
                            && getEquipmentType(alljobarrayList.get(j).getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                        sectionIDlist5.add(questionArrayList.get(i));
                    } else if (questionArrayList.get(i).getType().equalsIgnoreCase("Q") && questionArrayList.get(i).getCleaning().equals("1") && questionArrayList.get(i).getCleaning().equals("1")
                            && getEquipmentType(alljobarrayList.get(j).getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                        sectionIDlist6.add(questionArrayList.get(i));
                    } else if (questionArrayList.get(i).getType().equalsIgnoreCase("Q") && questionArrayList.get(i).getCleaning().equals("1") && questionArrayList.get(i).getCleaning().equals("1")
                            && getEquipmentType(alljobarrayList.get(j).getEquipmentType().getId(), questionArrayList.get(i).getEquipmentTypes()).equals("1")) {
                        sectionIDlist7.add(questionArrayList.get(i));
                    }

                }
        }
    }


    public static String getEquipmentType(String job_eq_id, ArrayList<QuestionsEquipmentType> equipmentTypes) {

        for (int i = 0; i < equipmentTypes.size(); i++) {
            if (equipmentTypes.get(i).getEquipmentId().equals(job_eq_id))
                return equipmentTypes.get(i).getValue();
        }
        return "0";
    }


    private void separatejobTypeList() {

        listMaintenance = new ArrayList<>();
        listBreakdown = new ArrayList<>();
        listinspection = new ArrayList<>();
        listtm = new ArrayList<>();

        for (int i = 0; i < alljobarrayList.size(); i++) {
            if (alljobarrayList.get(i).getJobType() != null && alljobarrayList.get(i).getJobType().toLowerCase().contains("Maintenance".toLowerCase())) {
                listMaintenance.add(alljobarrayList.get(i));
                tvMaintainanceCount.setText("" + listMaintenance.size());
                tvMaintainanceCount.setVisibility(View.VISIBLE);
            } else if (alljobarrayList.get(i).getJobType() != null && alljobarrayList.get(i).getJobType().toLowerCase().contains("BreakDown".toLowerCase())) {
                listBreakdown.add(alljobarrayList.get(i));
                tvBreadownCount.setText("" + listBreakdown.size());
                tvBreadownCount.setVisibility(View.VISIBLE);
            } else if (alljobarrayList.get(i).getJobType() != null && alljobarrayList.get(i).getJobType().toLowerCase().contains("Inspection".toLowerCase())) {
                listinspection.add(alljobarrayList.get(i));
                tvInspectionCount.setText("" + listinspection.size());
                tvInspectionCount.setVisibility(View.VISIBLE);
            } else if (alljobarrayList.get(i).getJobType() != null && alljobarrayList.get(i).getJobType().toLowerCase().contains("t&m".toLowerCase())) {
                listtm.add(alljobarrayList.get(i));
                tvTMCount.setText("" + listtm.size());
                tvTMCount.setVisibility(View.VISIBLE);
            }

        }
    }

    public void preQuestionApi() {

        if (AppGlobal.isNetwork(getApplicationContext())) {

//            LoginJson model = new LoginJson("login", new LoginRequestModel(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_pre_question");
            hashMap.put("request", "");
            hashMap.put("auth", auth);


            new RestClient(LandingpageActivity.this).getInstance().get().prequestion(hashMap).enqueue(new Callback<PreQuestionResponse>() {
                @Override
                public void onResponse(Call<PreQuestionResponse> call, Response<PreQuestionResponse> response) {

                    Log.e("TAG", "iServ response: " + new Gson().toJson(hashMap));

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                Log.e("TAG", "iServ response: " + new Gson().toJson(response.body()));

                                db1.deletePreQuestion();

                                if (response.body().getData().size() > 0) {

                                /*    preQuestionModel.setPreQid(response.body().getData().get(0).getPreQid());
                                    preQuestionModel.setPreQuestion(response.body().getData().get(0).getPreQuestion());*/

                                    PreQuestionModel preQuestionMode = new PreQuestionModel();

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        preQuestionMode.setPreQid(response.body().getData().get(i).getPreQid());
                                        preQuestionMode.setPreQuestion(response.body().getData().get(i).getPreQuestion());
                                        db1.insertPreQuestion(preQuestionMode, (i + 1) + "");

                                     /*   Log.e("TAG", "iServ response: preQId" + preQuestionMode.getPreQid());
                                        Log.e("TAG", "iServ response: preQuestion" + preQuestionMode.getPreQuestion());*/
                                    }
                                    db1.close();

                                }

                            } else {
                                Toasty.error(getApplicationContext(), "preQuestionApi " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getApplicationContext(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PreQuestionResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

    public void sectionNameApi() {

        if (AppGlobal.isNetwork(getApplicationContext())) {

//            LoginJson model = new LoginJson("login", new LoginRequestModel(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_section_type");
            hashMap.put("request", "");
            hashMap.put("auth", auth);


            new RestClient(LandingpageActivity.this).getInstance().get().sectiontype(hashMap).enqueue(new Callback<SectionNameResponse>() {
                @Override
                public void onResponse(Call<SectionNameResponse> call, Response<SectionNameResponse> response) {

                    Log.e("TAG", "iServ response: " + new Gson().toJson(hashMap));

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                Log.e("TAG", "iServ Section Type response: " + new Gson().toJson(response.body()));

                                db1.deleteSectionName();

                                if (response.body().getData().size() > 0) {

                                /*    preQuestionModel.setPreQid(response.body().getData().get(0).getPreQid());
                                    preQuestionModel.setPreQuestion(response.body().getData().get(0).getPreQuestion());*/

                                    SectionNameModel sectionNameModel = new SectionNameModel();

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        db1.insertSectionName(sectionNameModel);

                                        Log.e("TAG", "iServ Section Type: Id" + sectionNameModel.getId());
                                        Log.e("TAG", "iServ Section Type: SectionName" + sectionNameModel.getSectionName());
                                    }
                                    db1.close();

                                }

                            } else {
                                Toasty.error(getApplicationContext(), "sectionNameApi " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getApplicationContext(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SectionNameResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

    public void partsApi() {

        if (AppGlobal.isNetwork(getApplicationContext())) {

//            LoginJson model = new LoginJson("login", new LoginRequestModel(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_parts");
            hashMap.put("request", "");
            hashMap.put("auth", auth);

            new RestClient(LandingpageActivity.this).getInstance().get().parts(hashMap).enqueue(new Callback<PartsResponse>() {
                @Override
                public void onResponse(Call<PartsResponse> call, Response<PartsResponse> response) {

                    Log.e("TAG", "iServ response: " + new Gson().toJson(hashMap));

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                                Log.e("TAG", "iServ Parts response: " + new Gson().toJson(response.body()));

                                db1.deleteParts();

                                if (response.body().getData().size() > 0) {

                                /*    preQuestionModel.setPreQid(response.body().getData().get(0).getPreQid());
                                    preQuestionModel.setPreQuestion(response.body().getData().get(0).getPreQuestion());*/

                                    PartsModel partsModel = new PartsModel();

                                    for (int i = 0; i < response.body().getData().size(); i++) {
                                        db1.insertParts(partsModel);

                                        Log.e("TAG", "iServ Parts: Id" + partsModel.getId());
                                        Log.e("TAG", "iServ Parts: SectionName" + partsModel.getSectionName());
                                    }
                                    db1.close();

                                }

                            } else {
                                Toasty.error(getApplicationContext(), "partsApi " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(getApplicationContext(), "Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<PartsResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

    public void getAllJobApi() {

        if (AppGlobal.isNetwork(LandingpageActivity.this)) {

            HashMap<String, Object> auth = new HashMap<String, Object>();
            auth.put("id", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_ADMINID));
            auth.put("token", AppGlobal.getStringPreference(LandingpageActivity.this, AppConstant.PREF_TOKEN));

            final HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("service", "get_all_jobs");
            hashMap.put("request", "");
            hashMap.put("auth", auth);

            Log.e("Save Fragment AllJob=", hashMap + "");

            new RestClient(LandingpageActivity.this).getInstance().get().getalljob(hashMap).enqueue(new Callback<GetAllJobResponse>() {
                @Override
                public void onResponse(Call<GetAllJobResponse> call, Response<GetAllJobResponse> response) {

                    try {
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("1")) {

                             /*   db1.deleteAllJob();
                                db1.deleteAllJob_Site();*/

                                Toasty.success(LandingpageActivity.this, " " + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    db1.insertAllJob_Site(response.body().getData().get(i), response.body().getData().get(i).getSiteId());

                                    for (int j = 0; j < response.body().getData().get(i).getJobs().size(); j++) {
                                        db1.insertAllJob(response.body().getData().get(i).getJobs().get(j), response.body().getData().get(i).getJobs().get(j).getJobId());
                                    }
                                }

                                db1.close();

                            } else {
                                Toasty.error(LandingpageActivity.this, "getAllJobApi " + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toasty.error(LandingpageActivity.this, "getAllJobApi Null Body", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "iServ response: Null Body ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetAllJobResponse> call, Throwable t) {
                    Log.e("lookinside", "failed" + t.toString());
                }
            });

        } else {
            Toast.makeText(LandingpageActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            Log.e("TAG", "Connection : No Internet Connection ");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
